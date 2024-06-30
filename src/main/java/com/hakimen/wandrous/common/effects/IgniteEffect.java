package com.hakimen.wandrous.common.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class IgniteEffect extends MobEffect {


    public IgniteEffect() {
        super(MobEffectCategory.HARMFUL, 0xFF1B00);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return pDuration % 20 == 0;
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.setRemainingFireTicks(2);
        return true;
    }
}
