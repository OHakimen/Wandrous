package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SoundRegister {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Wandrous.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> TIMER_SPELL = SOUNDS.register("timer", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Wandrous.MODID, "timer")));

    public static void register(IEventBus bus){
        SOUNDS.register(bus);
    }
}
