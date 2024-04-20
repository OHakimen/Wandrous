package com.hakimen.wandrous.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class TimerEntity extends Entity {

    public int tickTime;
    int maxTickTime;

    public TimerEntity(EntityType<?> pEntityType, Level pLevel, int maxTickTime) {
        super(pEntityType, pLevel);
        this.maxTickTime = maxTickTime;
        this.tickTime = 0;
    }


    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        tickTime = compoundTag.getInt("TickTime");
        maxTickTime = compoundTag.getInt("MaxTickTime");

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putInt("TickTime", tickTime);
        compoundTag.putInt("MaxTickTime", maxTickTime);
    }

    @Override
    public void tick() {
        this.tickTime++;

        if(tickedEnough()){
            discard();
        }
    }

    public boolean tickedEnough(){
        return tickTime > maxTickTime;
    }
}
