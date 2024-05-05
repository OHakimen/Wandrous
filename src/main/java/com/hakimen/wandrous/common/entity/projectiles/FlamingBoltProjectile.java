package com.hakimen.wandrous.common.entity.projectiles;

import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.mover.ISpellMover;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.*;

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
            level().explode(this,getX(),getY(),getZ(), context != null ? context.getStatus().getDamage() : 4, true, Level.ExplosionInteraction.MOB);
            this.level().broadcastEntityEvent(this, (byte)3);
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
        if(this.context != null){
            SpellCastingProjectile.onHitBlock(this, pResult, this.context);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if(this.context != null){
            SpellCastingProjectile.onHitEntity(this, pResult, this.context);
        }
    }


    @Override
    public void tick() {
        super.tick();

        if(this.context != null){
            List<ISpellMover> movers = getMovers(context.getNode());
            for (ISpellMover mover : movers) {
                mover.move(context,this);
            }
        }

        if(!level().isClientSide){
            if(this.tickCount > maxTicks){
                if(this.context != null && this.context.getNode().getData().hasKind(SpellEffect.TIMER)){
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
        return 0.3f;
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
