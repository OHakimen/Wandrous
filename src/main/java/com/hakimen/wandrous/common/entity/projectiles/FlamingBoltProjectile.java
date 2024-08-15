package com.hakimen.wandrous.common.entity.projectiles;

import com.hakimen.wandrous.common.events.payloads.PositionalScreenShakePacket;
import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.mover.ISpellMover;
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
import java.util.List;

public class FlamingBoltProjectile extends SpellCastingProjectile {

    SpellContext context;
    List<ISpellMover> movers;

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
    }

    public FlamingBoltProjectile(double pX, double pY, double pZ, Level level, SpellContext context) {
        super(EntityRegister.FLAMING_BOLT_PROJECTILE.get(), pX, pY, pZ, level);
        this.context = context.clone();
        this.context.setCaster(this);
        this.maxTicks = this.context.getStatus().getLifeTime();
        this.movers = new ArrayList<>();

    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            level().explode(this, getX(), getY(), getZ(), context != null ? context.getStatus().getDamage() : 4, true, Level.ExplosionInteraction.MOB);
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
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
            SpellCastingProjectile.onHitEntity(this, pResult, this.context);
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
