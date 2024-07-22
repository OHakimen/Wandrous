package com.hakimen.wandrous.common.entity.projectiles;

import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.mover.ISpellMover;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChainShotProjectile extends SpellCastingProjectile {

    SpellContext context;
    List<ISpellMover> movers;
    int hitCount;
    List<LivingEntity> hit;

    public ChainShotProjectile(EntityType<? extends ThrowableProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.movers = new ArrayList<>();
        this.hit = new ArrayList<>();
    }

    public ChainShotProjectile(double pX, double pY, double pZ, Level level, SpellContext context, ISpellMover... movers) {
        super(EntityRegister.CHAIN_SHOT.get(), pX, pY, pZ, level);
        this.context = context.clone();
        this.context.setCaster(this);
        this.setNoGravity(true);
        this.maxTicks = this.context.getStatus().getLifeTime();
        this.movers = new ArrayList<>();
        this.movers.addAll(Arrays.stream(movers).toList());
        this.hit = new ArrayList<>();
    }

    public ChainShotProjectile(double pX, double pY, double pZ, Level level, SpellContext context) {
        super(EntityRegister.CHAIN_SHOT.get(), pX, pY, pZ, level);
        this.context = context.clone();
        this.context.setCaster(this);
        this.setNoGravity(true);
        this.maxTicks = this.context.getStatus().getLifeTime();
        this.movers = new ArrayList<>();
        this.hit = new ArrayList<>();
    }

    public int getHitCount() {
        return hitCount;
    }

    public ChainShotProjectile setHitCount(int hitCount) {
        this.hitCount = hitCount;
        return this;
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            Random r = new Random();
            for (int i = 0; i < 16; i++) {
                this.level().addParticle(ParticleTypes.SCULK_SOUL, this.getX() - 0.5f + r.nextFloat(-1,1), this.getY() - 0.5f + r.nextFloat(-1,1), this.getZ() - 0.5f + r.nextFloat(-1,1), 0, 0, 0);
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (context != null) {
            //SpellCastingProjectile.onHitBlock(this, pResult, context);
        }
    }


    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (context != null) {
            SpellCastingProjectile.onHitEntity(this, pResult, context);
            this.level().broadcastEntityEvent(this, (byte) 3);
            if (hitCount > 0 && pResult.getEntity() instanceof LivingEntity livingEntity && !hit.contains(livingEntity)) {
                hit.add(livingEntity);
                level().playSound(null, context.getCaster().getOnPos(), SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.PLAYERS, 1,1f + (hit.size() / 10f));
                context.setHomingTarget(null);
                hitCount--;
            }
        }
    }


    @Override
    public void tick() {
        super.tick();

        if (this.context != null) {
            List<ISpellMover> movers = getMovers(context.getNode());
            for (ISpellMover mover : movers) {
                mover.move(context, this);
            }

            if (context.getHomingTarget() == null) {
                Level level = context.getLevel();
                Vec3 pos = getPosition(0);
                float radius = context.getStatus().getRadius();
                List<Entity> entities = level.getEntities(context.getCaster(), AABB.ofSize(pos, radius, radius, radius), (entity) -> entity instanceof LivingEntity && !entity.equals(context.getOriginalCaster()) && !hit.contains(entity));

                LivingEntity closest = null;

                float closestDist = Float.MAX_VALUE;
                for (Entity entity : entities) {
                    if (closest == null) {
                        closest = (LivingEntity) entity;
                        closestDist = (float) closest.position().distanceTo(getPosition(0).subtract(0, closest.getEyeY()/2f, 0));
                    } else if(closestDist >= entity.position().distanceTo(getPosition(0).subtract(0, closest.getEyeY()/2f, 0))){
                        closest = (LivingEntity) entity;
                        closestDist = (float) closest.position().distanceTo(getPosition(0).subtract(0, closest.getEyeY()/2f, 0));
                    }
                }

                if (closest != null) {
                    context.setHomingTarget(closest);
                }

            }

            if (context.getHomingTarget() != null) {
                LivingEntity target = context.getHomingTarget();
                if (target.isAlive()) {
                    setDeltaMovement(target.getEyePosition().subtract(getPosition(0)).normalize().scale(context.getStatus().getSpeed()));
                }else{
                    context.setHomingTarget(null);
                }
            }
        }

        if (!level().isClientSide) {
            if (this.tickCount > maxTicks || hitCount == 0) {
                if (this.context != null && this.context.getNode().getData().getEffect().hasKind(SpellEffect.TIMER)) {
                    this.context.getStatus().setLifetimeMod(0);
                    this.context.getHit().addAll(hit);
                    SpellCastingProjectile.onTimeEnd(this, this.context);
                }
                level().playSound(null, context.getCaster().getOnPos(), SoundEvents.AMETHYST_CLUSTER_HIT, SoundSource.PLAYERS, 1,1f);
                discard();
            }
        }
    }


    @Override
    public float getInertia() {
        return 1f;
    }

    @Override
    public float getFluidInertia() {
        return 1f;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putInt("maxTicks", maxTicks);
        super.addAdditionalSaveData(pCompound);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.maxTicks = pCompound.getInt("maxTicks");
    }
}
