package com.hakimen.wandrous.common.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.awt.*;

public class IgniteEffect extends MobEffect {


    public IgniteEffect() {
        super(MobEffectCategory.HARMFUL, 0xFF1B00);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return pDuration % 20 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.setSecondsOnFire(2);
    }
}
