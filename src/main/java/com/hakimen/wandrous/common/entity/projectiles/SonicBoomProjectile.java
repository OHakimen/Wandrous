package com.hakimen.wandrous.common.entity.projectiles;

import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.mover.ISpellMover;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SculkChargeParticleOptions;
import net.minecraft.core.particles.ShriekParticleOption;
import net.minecraft.core.particles.VibrationParticleOption;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public class SonicBoomProjectile extends SpellCastingProjectile {

    SpellContext context;
    List<ISpellMover> movers;

    public SonicBoomProjectile(EntityType<? extends ThrowableProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.movers = new ArrayList<>();
    }

    public SonicBoomProjectile(double pX, double pY, double pZ, Level level, SpellContext context, ISpellMover... movers) {
        super(EntityRegister.SONIC_BOOM_PROJECTILE.get(), pX, pY, pZ, level);
        this.context = context.clone();
        this.context.setCaster(this);
        this.setNoGravity(true);
        this.maxTicks = this.context.getStatus().getLifeTime();
        this.movers = new ArrayList<>();
        this.movers.addAll(Arrays.stream(movers).toList());
    }

    public SonicBoomProjectile(double pX, double pY, double pZ, Level level, SpellContext context) {
        super(EntityRegister.SONIC_BOOM_PROJECTILE.get(), pX, pY, pZ, level);
        this.context = context.clone();
        this.context.setCaster(this);
        this.setNoGravity(true);
        this.maxTicks = this.context.getStatus().getLifeTime();
        this.movers = new ArrayList<>();

    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (context != null) {
            SpellCastingProjectile.onHitBlock(this, pResult, context);
            this.level().broadcastEntityEvent(this, (byte) 3);
            discard();
        }
    }


    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (context != null) {
            SpellCastingProjectile.onHitEntity(this, pResult, context);
            this.level().broadcastEntityEvent(this, (byte) 3);
            discard();
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
        }

        if (!level().isClientSide) {
            if (this.tickCount > maxTicks) {
                if (this.context != null && this.context.getNode().getData().hasKind(SpellEffect.TIMER)) {
                    this.context.getStatus().setLifetimeMod(0);
                    SpellCastingProjectile.onTimeEnd(this, this.context);
                }
                this.level().broadcastEntityEvent(this, (byte) 3);
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
    protected void defineSynchedData() {

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
