package com.hakimen.wandrous.common.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SilenceEffect extends MobEffect {
    public SilenceEffect() {
        super(MobEffectCategory.HARMFUL, 0xff0000);
    }
}
