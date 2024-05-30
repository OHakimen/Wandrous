package com.hakimen.wandrous.common.entity.static_spell;

import com.hakimen.wandrous.common.registers.SoundRegister;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class TimerEntity extends Entity {

    public static final EntityDataAccessor<Integer> MAX_TICK_TIME = SynchedEntityData.defineId(TimerEntity.class, EntityDataSerializers.INT);
    public int maxTickTime;
    public TimerEntity(EntityType<?> pEntityType, Level pLevel, int maxTickTime) {
        super(pEntityType, pLevel);
        this.maxTickTime = maxTickTime;
        this.setDeltaMovement(new Vec3(0,0,0));
        this.entityData.set(MAX_TICK_TIME, maxTickTime);
    }


    @Override
    protected void defineSynchedData() {
        this.entityData.define(MAX_TICK_TIME, maxTickTime);
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

        if(tickCount < 2  || (tickCount % 40 == 0 && tickCount + 40 <= maxTickTime)){
            level().playSound(null,getOnPos(), SoundRegister.TIMER_SPELL.get(), SoundSource.NEUTRAL, 0.6f,1f);
        }

        if(tickedEnough()){
            level().playSound(null,getOnPos(), SoundEvents.BEACON_DEACTIVATE, SoundSource.NEUTRAL, 1f,1f);
            discard();
        }
    }


    public boolean tickedEnough(){
        return maxTickTime != -1 && tickCount > maxTickTime;
    }
}
