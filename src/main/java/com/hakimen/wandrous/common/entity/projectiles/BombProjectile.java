package com.hakimen.wandrous.common.entity.projectiles;

import com.hakimen.wandrous.common.payloads.PositionalScreenShakePacket;
import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.api.SpellContext;
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
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.Arrays;

public class BombProjectile extends SpellCastingProjectile {

    float damage;

    public BombProjectile(EntityType<? extends ThrowableProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.movers = new ArrayList<>();
        damage = 1;
    }

    public BombProjectile(double pX, double pY, double pZ, Level level, SpellContext context, ISpellMover... movers) {
        super(EntityRegister.BOMB.get(), pX, pY, pZ, level);
        this.context = context.clone();
        this.context.setCaster(this);
        this.maxTicks = this.context.getStatus().getLifeTime();
        this.movers = new ArrayList<>();
        this.damage = this.context.getStatus().getDamage();
        this.movers.addAll(Arrays.stream(movers).toList());
        this.entityData.set(MOVER_DATA, moverListToNBT());
    }

    public BombProjectile(double pX, double pY, double pZ, Level level, SpellContext context) {
        super(EntityRegister.BOMB.get(), pX, pY, pZ, level);
        this.context = context.clone();
        this.context.setCaster(this);
        this.maxTicks = this.context.getStatus().getLifeTime();
        this.damage = this.context.getStatus().getDamage();
        this.movers = getMovers(context.getNode());
        this.entityData.set(MOVER_DATA, moverListToNBT());
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
        if (this.context != null) {
            SpellCastingProjectile.onHitBlock(this, pResult, this.context);
            consume();
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (this.context != null) {
            if (!context.isPiercing()) {
                context.getLevel().broadcastEntityEvent(this, (byte) 3);
                if (SpellCastingProjectile.shouldCollide(this, pResult, this.context)) {
                    SpellCastingProjectile.onHitEntity(this, pResult, this.context);
                    consume();
                    this.discard();
                }
            } else {
                if (SpellCastingProjectile.shouldCollide(this, pResult, this.context)) {
                    SpellCastingProjectile.onHitEntity(this, pResult, this.context);
                    consume();
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
                consume();
                discard();
            }
        }
    }


    private void consume() {
        Level level = level();
        if (EventHooks.canEntityGrief(level, this)) {
            float radius = this.damage;
            PacketDistributor.sendToPlayersNear((ServerLevel) level, null, position().x, position().y, position().z, 50, new PositionalScreenShakePacket(
                    1.5f,
                    20,
                    40,
                    this.getPosition(0).toVector3f(),
                    50
            ));
            level.explode(this, getX(), getY(), getZ(), radius, Level.ExplosionInteraction.NONE);
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
        pCompound.putInt("maxTicks", maxTicks);
        pCompound.putFloat("Damage", damage);
        super.addAdditionalSaveData(pCompound);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.maxTicks = pCompound.getInt("maxTicks");
        this.damage = pCompound.getFloat("Damage");
    }
}
