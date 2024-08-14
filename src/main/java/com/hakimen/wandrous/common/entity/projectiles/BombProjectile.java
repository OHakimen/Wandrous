package com.hakimen.wandrous.common.entity.projectiles;

import com.hakimen.wandrous.common.events.payloads.PositionalScreenShakePacket;
import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.mover.ISpellMover;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BombProjectile extends SpellCastingProjectile {
    SpellContext context;
    List<ISpellMover> movers;

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
    }

    public BombProjectile(double pX, double pY, double pZ, Level level, SpellContext context) {
        super(EntityRegister.BOMB.get(), pX, pY, pZ, level);
        this.context = context.clone();
        this.context.setCaster(this);
        this.maxTicks = this.context.getStatus().getLifeTime();
        this.damage = this.context.getStatus().getDamage();
        this.movers = new ArrayList<>();

    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        discard();
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
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (this.context != null) {
            SpellCastingProjectile.onHitEntity(this, pResult, this.context);
            consume();
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


        Level level = this.level();
        if (level.isClientSide) {
            Random r = new Random();
            Vec3 location = this.getPosition(0);
            for (int i = 0; i < 4; i++) {
                level.addParticle(ParticleTypes.SMOKE, location.x + r.nextFloat(-0.25f,0.25f), location.y + r.nextFloat(-0.25f,0.25f), location.z + r.nextFloat(-0.25f,0.25f), 0.0D, 0.0D, 0.0D);
            }
            level.addParticle(ParticleTypes.FLAME, location.x, location.y, location.z, 0.0D, 0.0D, 0.0D);
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
