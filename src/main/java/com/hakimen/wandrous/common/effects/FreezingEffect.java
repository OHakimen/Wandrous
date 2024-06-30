package com.hakimen.wandrous.common.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FreezingEffect extends MobEffect {


    public FreezingEffect() {
        super(MobEffectCategory.HARMFUL, 0x67FFFF);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.setTicksFrozen(pLivingEntity.isFullyFrozen() ? pLivingEntity.getTicksRequiredToFreeze() + 10 : pLivingEntity.getTicksFrozen() + 5);
        return true;
    }
}
