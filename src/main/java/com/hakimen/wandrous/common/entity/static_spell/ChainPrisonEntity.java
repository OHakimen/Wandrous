package com.hakimen.wandrous.common.entity.static_spell;

import com.hakimen.wandrous.common.registers.EntityRegister;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ChainPrisonEntity extends Entity {

    LivingEntity target;
    public static final EntityDataAccessor<Integer> MAX_TICK_TIME = SynchedEntityData.defineId(ChainPrisonEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Float> TARGET_BB_HEIGHT = SynchedEntityData.defineId(ChainPrisonEntity.class, EntityDataSerializers.FLOAT);
    public int maxTickTime;

    public ChainPrisonEntity(EntityType<?> pEntityType, Level pLevel) {
        super(EntityRegister.CHAIN_PRISON.get(), pLevel);
        maxTickTime = 1000;
        target = null;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        pBuilder.define(MAX_TICK_TIME, maxTickTime);
        pBuilder.define(TARGET_BB_HEIGHT, 0f);
    }

    public ChainPrisonEntity( Level pLevel, int maxTickTime, LivingEntity target) {
        super(EntityRegister.CHAIN_PRISON.get(), pLevel);
        this.maxTickTime = maxTickTime;
        this.setDeltaMovement(new Vec3(0, 0, 0));
        this.setNoGravity(true);
        this.target = target;
        this.setPos(target.getX(), target.getY(), target.getZ());
        this.entityData.set(MAX_TICK_TIME, maxTickTime);
        this.entityData.set(TARGET_BB_HEIGHT, target.getBbHeight());
    }


    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        maxTickTime = compoundTag.getInt("MaxTickTime");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putInt("MaxTickTime", maxTickTime);
    }

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide) {
            if(target != null && target.isAlive()) {
                target.fallDistance = 0;
                target.teleportTo(this.getPosition(0).x(), this.getPosition(0).y(), this.getPosition(0).z());
            } else{
                discard();
            }

            if(tickedEnough()){
                discard();
            }
        }
    }

    public boolean tickedEnough() {
        return maxTickTime != -1 && tickCount > maxTickTime;
    }

}
