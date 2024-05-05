package com.hakimen.wandrous.common.entity.projectiles;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.spell.effects.modifiers.MoverCastEffect;
import com.hakimen.wandrous.common.spell.effects.modifiers.ProjectileHitEffect;
import com.hakimen.wandrous.common.spell.mover.ISpellMover;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

import java.util.ArrayList;
import java.util.List;

public class SpellCastingProjectile extends ThrowableProjectile {

    int maxTicks;

    protected SpellCastingProjectile(EntityType<? extends ThrowableProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected SpellCastingProjectile(EntityType<? extends ThrowableProjectile> pEntityType, double pX, double p_37458_, double pY, Level p_37460_) {
        super(pEntityType, pX, p_37458_, pY, p_37460_);
    }

    protected SpellCastingProjectile(EntityType<? extends ThrowableProjectile> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }


    protected static void shootProjectile(Projectile self, SpellContext context){
        Entity caster = context.getCaster();
        SpellStatus status = context.getStatus();

        if (caster instanceof LivingEntity livingEntity) {
            float yRot = livingEntity.getYRot() + Math.round(context.getSplit() / 2.0) * 10 * (context.getSplit() % 2 == 1 ? -1 : 1);
            float xRot = livingEntity.getXRot();
            self.shootFromRotation(livingEntity,xRot, yRot, 0, status.getSpeed(), status.getSpread() * 10);
            status.setSpeedMod(0);
        } else {
            self.setDeltaMovement(caster.getDeltaMovement().yRot( Math.round(context.getSplit() / 2.0) * 10 * (context.getSplit() % 2 == 1 ? -1 : 1)).multiply(context.getSplit() % 2 == 1 ? -1 : 1,1,context.getSplit() % 2 == 1 ? -1 : 1).scale(1 + status.getSpeedMod()));
        }
    }

    protected static void onHitBlock(Projectile self, BlockHitResult pResult, SpellContext context){
        SpellContext nextContext = context.clone();

        List<ProjectileHitEffect> effects = addProjectileEffects(context.getNode());

        effects.forEach(projectileHitEffect -> {
            projectileHitEffect.onHitBlock(nextContext, self.level(), pResult.getBlockPos(), self.level().getBlockState(pResult.getBlockPos()));
        });

        if (nextContext.getNode().getData().hasKind(SpellEffect.TRIGGER)) {
            self.setDeltaMovement(new Vec3(self.getDeltaMovement().toVector3f().reflect(Vec3.atLowerCornerOf(pResult.getDirection().getNormal()).toVector3f())));
            nextContext.getNode().getChildren().forEach(
                    (child) -> child.getData().cast(nextContext.setNode(child).setLocation(pResult.getLocation()))
            );
        }
    }

    protected static void onHitEntity(Projectile self, EntityHitResult pResult, SpellContext context) {
        SpellContext nextContext = context.clone();
        if (pResult.getEntity() instanceof LivingEntity entity) {
            nextContext.getHit().add(entity);
        }

        List<ProjectileHitEffect> effects = addProjectileEffects(context.getNode());

        effects.forEach(projectileHitEffect -> {
            projectileHitEffect.onHitBlock(nextContext, self.level(), pResult.getEntity().getOnPos(), self.level().getBlockState(pResult.getEntity().getOnPos()));
            projectileHitEffect.onHitEntity(nextContext, pResult.getEntity());
        });

        if (nextContext.getNode().getData().hasKind(SpellEffect.TRIGGER)) {
            self.setDeltaMovement(self.getDeltaMovement().multiply(1, -1, 1));
            nextContext.getNode().getChildren().forEach(
                    (child) -> child.getData().cast(nextContext.setNode(child).setLocation(pResult.getLocation()))
            );
        }

        pResult.getEntity().hurt(self.damageSources().source(DamageTypes.MAGIC, context.getOriginalCaster()), context.getStatus().getDamage());
    }


    protected static void onTimeEnd(Projectile self, SpellContext context) {
        SpellContext nextContext = context.clone();
        if (nextContext.getNode().getData().hasKind(SpellEffect.TIMER)) {
            nextContext.getNode().getChildren().forEach(
                    (child) -> child.getData().cast(nextContext.setNode(child).setLocation(self.getPosition(0)))
            );
        }
    }

    private static List<ProjectileHitEffect> addProjectileEffects(Node<SpellEffect> node){
        List<ProjectileHitEffect> effects = new ArrayList<>();
        if(node.getParent() != null &&  node.getParent().getData().hasKind(SpellEffect.MODIFIER)){
            if( node.getParent().getData() instanceof ProjectileHitEffect effect){
                effects.add(effect);
            }
            effects.addAll(addProjectileEffects(node.getParent()));
        }
        return effects;
    }

    static List<ISpellMover> getMovers(Node<SpellEffect> node){
        List<ISpellMover> movers = new ArrayList<>();
        if(node.getParent() != null &&  node.getParent().getData().hasKind(SpellEffect.MODIFIER)){
            if( node.getParent().getData() instanceof MoverCastEffect effect){
                movers.add(effect.getMover());
            }
            movers.addAll(getMovers(node.getParent()));
        }
        return movers;
    }


    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putInt("MaxTicks", maxTicks);
        super.addAdditionalSaveData(pCompound);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        maxTicks = pCompound.getInt("MaxTicks");
        super.readAdditionalSaveData(pCompound);
    }

    public float getInertia(){
        return 0;
    }

    public float getFluidInertia(){
        return 0;
    }


    @Override
    protected void defineSynchedData() {

    }

    @Override
    public void tick() {
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        boolean flag = false;
        if (hitresult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockHitResult)hitresult).getBlockPos();
            BlockState blockstate = this.level().getBlockState(blockpos);
            if (blockstate.is(Blocks.NETHER_PORTAL)) {
                this.handleInsidePortal(blockpos);
                flag = true;
            } else if (blockstate.is(Blocks.END_GATEWAY)) {
                BlockEntity blockentity = this.level().getBlockEntity(blockpos);
                if (blockentity instanceof TheEndGatewayBlockEntity && TheEndGatewayBlockEntity.canEntityTeleport(this)) {
                    TheEndGatewayBlockEntity.teleportEntity(this.level(), blockpos, blockstate, this, (TheEndGatewayBlockEntity)blockentity);
                }

                flag = true;
            }
        }

        if (hitresult.getType() != HitResult.Type.MISS && !flag && !EventHooks.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }

        this.checkInsideBlocks();
        Vec3 vec3 = this.getDeltaMovement();
        double d2 = this.getX() + vec3.x;
        double d0 = this.getY() + vec3.y;
        double d1 = this.getZ() + vec3.z;
        this.updateRotation();
        float f;
        if (this.isInWater()) {
            f = getFluidInertia();
            for(int i = 0; i < 4; ++i) {
                this.level().addParticle(ParticleTypes.BUBBLE, d2 - vec3.x * f, d0 - vec3.y * f, d1 - vec3.z * f, vec3.x, vec3.y, vec3.z);
            }

        } else {
            f = getInertia();
        }

        this.setDeltaMovement(vec3.scale(f));

        if (!this.isNoGravity()) {
            Vec3 vec31 = this.getDeltaMovement();
            this.setDeltaMovement(vec31.x, vec31.y - (double)this.getGravity(), vec31.z);
        }

        this.setPos(d2, d0, d1);
    }

    public int getMaxTicks() {
        return maxTicks;
    }
}
