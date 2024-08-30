package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.effects.*;
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
    public static final DeferredHolder<MobEffect, MobEffect> SILENCE = MOB_EFFECTS.register("silence", SilenceEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> SUMMONED_ENTITY_EFFECT = MOB_EFFECTS.register("summoned_entity_effect", SummonedEntityEffect::new);

    public static void register(IEventBus bus) {
        MOB_EFFECTS.register(bus);
    }
}
