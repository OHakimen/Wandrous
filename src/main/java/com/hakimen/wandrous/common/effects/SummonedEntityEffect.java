package com.hakimen.wandrous.common.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class SummonedEntityEffect extends MobEffect {
    public SummonedEntityEffect() {
        super(MobEffectCategory.NEUTRAL, 0xffffff);
    }


    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.remove(Entity.RemovalReason.DISCARDED);
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return pDuration == 1;
    }
}
