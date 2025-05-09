package com.hakimen.wandrous.common.entity.projectiles;

import com.hakimen.wandrous.common.payloads.PositionalScreenShakePacket;
import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellEffect;
import com.hakimen.wandrous.common.api.mover.ISpellMover;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.Arrays;

public class FlamingBoltProjectile extends SpellCastingProjectile {

    public FlamingBoltProjectile(EntityType<? extends ThrowableProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.movers = new ArrayList<>();
    }

    public FlamingBoltProjectile(double pX, double pY, double pZ, Level level, SpellContext context, ISpellMover... movers) {
        super(EntityRegister.FLAMING_BOLT_PROJECTILE.get(), pX, pY, pZ, level);
        this.context = context.clone();
        this.context.setCaster(this);
        this.maxTicks = this.context.getStatus().getLifeTime();
        this.movers = new ArrayList<>();
        this.movers.addAll(Arrays.stream(movers).toList());
        this.entityData.set(MOVER_DATA, moverListToNBT());
    }

    public FlamingBoltProjectile(double pX, double pY, double pZ, Level level, SpellContext context) {
        super(EntityRegister.FLAMING_BOLT_PROJECTILE.get(), pX, pY, pZ, level);
        this.context = context.clone();
        this.context.setCaster(this);
        this.maxTicks = this.context.getStatus().getLifeTime();
        this.movers = getMovers(this.context.getNode());
        this.entityData.set(MOVER_DATA, moverListToNBT());
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        level().explode(this, getX(), getY(), getZ(), context != null ? context.getStatus().getDamage() : 4, true, Level.ExplosionInteraction.TNT);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (this.context != null) {
            PacketDistributor.sendToPlayersNear((ServerLevel) level(), null, getX(), getY(), getZ(), context.getStatus().getDamage(), new PositionalScreenShakePacket(
                    context.getStatus().getDamage() / 8f,
                    20,
                    40,
                    getPosition(0).toVector3f(),
                    context.getStatus().getDamage()
            ));
            SpellCastingProjectile.onHitBlock(this, pResult, this.context);
            context.getLevel().broadcastEntityEvent(this, (byte) 3);

            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (this.context != null) {
            PacketDistributor.sendToPlayersNear((ServerLevel) level(), null, getX(), getY(), getZ(), context.getStatus().getDamage() / 2f, new PositionalScreenShakePacket(
                    context.getStatus().getDamage() / 8f,
                    20,
                    40,
                    getPosition(0).toVector3f(),
                    context.getStatus().getDamage() / 2f
            ));
            if (!context.isPiercing()) {
                context.getLevel().broadcastEntityEvent(this, (byte) 3);
                if (SpellCastingProjectile.shouldCollide(this, pResult, this.context)) {
                    SpellCastingProjectile.onHitEntity(this, pResult, this.context);
                    this.discard();
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
