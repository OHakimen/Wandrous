package com.hakimen.wandrous.common.entity.static_spell;

import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.registers.DamageTypeRegister;
import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.registers.ParticleRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.mover.ISpellMover;
import com.hakimen.wandrous.common.utils.CastingUtils;
import com.hakimen.wandrous.common.utils.RaycastUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;

import java.util.ArrayList;
import java.util.Arrays;

public class PlasmaBeamEntity extends SpellCastingProjectile {

    BlockPos breaking;
    float blockDestroyProgress;

    public PlasmaBeamEntity(EntityType<? extends ThrowableProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.movers = new ArrayList<>();
        this.maxTicks = Integer.MAX_VALUE;
    }

    public PlasmaBeamEntity(Vec3 location, Level level, SpellContext context, ISpellMover... movers) {
        super(EntityRegister.PLASMA_BEAM.get(), level);
        this.context = context.clone();
        this.context.setCaster(this);
        this.setNoGravity(true);

        this.setXRot(context.getCaster().getXRot());
        this.setYRot(context.getCaster().getYRot());

        this.setPos(location);
        this.maxTicks = this.context.getStatus().getLifeTime();
        this.movers = new ArrayList<>();
        this.movers.addAll(Arrays.stream(movers).toList());
        this.entityData.set(MOVER_DATA, moverListToNBT());
    }

    public PlasmaBeamEntity(double pX, double pY, double pZ, Level level, SpellContext context) {
        super(EntityRegister.PLASMA_BEAM.get(), pX, pY, pZ, level);
        this.context = context.clone();
        this.context.setCaster(this);
        this.setNoGravity(true);
        this.maxTicks = this.context.getStatus().getLifeTime();
        this.movers = getMovers(this.context.getNode());
        this.entityData.set(MOVER_DATA, moverListToNBT());
    }

    @Override
    public void tick() {
        //super.tick();
        if (this.context != null) {
            for (ISpellMover mover : movers) {
                mover.move(context, this);
            }
            Vec3 where = context.isCastPositionModified() ? context.getLocation() : context.getCaster().getEyePosition();
            Entity caster = context.getCaster();
            if(tickCount % 20 == 0){
                context.getLevel().playSound(null, context.getCaster().getOnPos(), SoundEvents.BEACON_AMBIENT, SoundSource.PLAYERS, 1f, 1f);
            }
            for (int i = 0; i < context.getStatus().getRadius() * 2; i++) {
                HitResult result = RaycastUtils.raycastRotatedAtPos(caster, where, Vec3.ZERO, context.getStatus().getRadius(), 0);
                ServerLevel level = (ServerLevel) context.getLevel();

                if (result instanceof BlockHitResult bhr && result.getType() != HitResult.Type.MISS) {
                    //When block, we increment damage
                    BlockState state = level.getBlockState(bhr.getBlockPos());
                    float blockHardness = state.getDestroySpeed(level, bhr.getBlockPos()) * 100;
                    float breakSpeed = context.getStatus().getDamage() / 2f;
                    breaking = bhr.getBlockPos();

                    blockDestroyProgress = Math.clamp(blockDestroyProgress + (breakSpeed / blockHardness), 1, 10);
                    if (breaking == bhr.getBlockPos()) {
                        level.destroyBlockProgress(this.hashCode(), breaking, (int) blockDestroyProgress);
                    }
                    if (blockDestroyProgress >= 10) {
                        level.destroyBlock(breaking, false);
                        blockDestroyProgress = 0;
                        breaking = null;
                    }
                } else if (result instanceof EntityHitResult ehr) {
                    if(ehr.getEntity() instanceof LivingEntity target){
                        target.hurt(DamageTypeRegister.beam(context.getOriginalCaster()), context.getStatus().getDamage());
                        CastingUtils.iFrameApply(target, context);
                    }
                }
                if(tickCount % 5 == 0){
                    for (int j = 0; j < context.getStatus().getRadius(); j++) {
                        Vec3 off = caster.getViewVector(0).scale(j);
                        Vec3 loc = where.add(off);
                        if (AABB.ofSize(loc, 2, 2, 2).contains(result.getLocation())) {
                            break;
                        }
                        level.sendParticles(ParticleRegister.CHAIN_SHOT.get(), loc.x, loc.y, loc.z, 1,0.1,0.1,0.1,0.1f);
                    }
                }
            }
        }
        if (tickCount > this.maxTicks) {
            if(level() instanceof ServerLevel serverLevel && breaking != null){
                serverLevel.destroyBlockProgress(this.hashCode(), breaking, (int) -1);
            }
            discard();
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putInt("ticks", this.tickCount);
        pCompound.putInt("maxTicks", maxTicks);
        super.addAdditionalSaveData(pCompound);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.tickCount = pCompound.getInt("ticks");
        this.maxTicks = pCompound.getInt("maxTicks");
    }

    @Override
    public void shootFromRotation(Entity pShooter, float pX, float pY, float pZ, float pVelocity, float pInaccuracy) {
        // Dont do shit cuz cringe
    }
}
