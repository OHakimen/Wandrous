package com.hakimen.wandrous.common.entity.projectiles;

import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.registers.ParticleRegister;
import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellEffect;
import com.hakimen.wandrous.common.api.mover.ISpellMover;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GlimmeringBoltProjectile extends SpellCastingProjectile {

    public GlimmeringBoltProjectile(EntityType<? extends ThrowableProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.movers = new ArrayList<>();
    }

    public GlimmeringBoltProjectile(double pX, double pY, double pZ, Level level, SpellContext context, ISpellMover... movers) {
        super(EntityRegister.GLIMMERING_BOLT_PROJECTILE.get(), pX, pY, pZ, level);
        this.context = context.clone();
        this.context.setCaster(this);
        this.maxTicks = this.context.getStatus().getLifeTime();
        this.movers = new ArrayList<>();
        this.movers.addAll(Arrays.stream(movers).toList());
        this.entityData.set(MOVER_DATA, moverListToNBT());
    }

    public GlimmeringBoltProjectile(double pX, double pY, double pZ, Level level, SpellContext context) {
        super(EntityRegister.GLIMMERING_BOLT_PROJECTILE.get(), pX, pY, pZ, level);
        this.context = context.clone();
        this.context.setCaster(this);
        this.maxTicks = this.context.getStatus().getLifeTime();
        this.movers = getMovers(this.context.getNode());
        this.entityData.set(MOVER_DATA, moverListToNBT());
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            for (int i = 0; i < 32; i++) {
                Random r = new Random();
                level().addParticle(
                        ParticleRegister.GLIMMERING_BOLT_HIT.get(),
                        getX(),
                        getY(),
                        getZ(),
                        r.nextFloat(-1f, 1f), 1f, r.nextFloat(-1f, 1f)
                );
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (this.context != null) {
            context.getLevel().broadcastEntityEvent(this, (byte) 3);
            SpellCastingProjectile.onHitBlock(this, pResult, this.context);
            discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (this.context != null) {
            if (!context.isPiercing()) {
                if (SpellCastingProjectile.shouldCollide(this, pResult, this.context)) {
                    context.getLevel().broadcastEntityEvent(this, (byte) 3);
                    SpellCastingProjectile.onHitEntity(this, pResult, this.context);
                    discard();
                }
            } else {
                if (SpellCastingProjectile.shouldCollide(this, pResult, this.context)) {
                    SpellCastingProjectile.onHitEntity(this, pResult, this.context);
                }
            }
        }
    }


    @Override
    public void tick() {
        super.tick();

        if (this.context != null) {
            for (ISpellMover mover : movers) {
                mover.move(context, this);
            }
        }

        if (!level().isClientSide) {
            if (this.tickCount > maxTicks) {
                if (this.context != null && this.context.getNode().getData().getEffect().hasKind(SpellEffect.TIMER)) {
                    this.context.getStatus().setLifetimeMod(0);
                    SpellCastingProjectile.onTimeEnd(this, this.context);
                }
                discard();
            }
        }
    }


    @Override
    public float getInertia() {
        return 0.98f;
    }

    @Override
    public float getFluidInertia() {
        return 0.9f;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
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
}
