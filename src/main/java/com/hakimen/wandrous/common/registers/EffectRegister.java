package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.effects.FreezingEffect;
import com.hakimen.wandrous.common.effects.IgniteEffect;
import com.hakimen.wandrous.common.effects.ScaleEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EffectRegister {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Wandrous.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> FREEZING = MOB_EFFECTS.register("freezing", FreezingEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> IGNITE = MOB_EFFECTS.register("ignite", IgniteEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> SCALE_DOWN = MOB_EFFECTS.register("scale_down", () -> new ScaleEffect(true));
    public static final DeferredHolder<MobEffect, MobEffect> SCALE_UP = MOB_EFFECTS.register("scale_up", () -> new ScaleEffect());

    public static void register(IEventBus bus) {
        MOB_EFFECTS.register(bus);
    }
}
