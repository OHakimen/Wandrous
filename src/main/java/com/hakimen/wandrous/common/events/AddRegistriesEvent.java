package com.hakimen.wandrous.common.events;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.custom.register.WandrousRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewRegistryEvent;

@EventBusSubscriber(modid = Wandrous.MODID, bus = EventBusSubscriber.Bus.MOD)
public class AddRegistriesEvent {
    @SubscribeEvent
    static void registerRegistries(NewRegistryEvent event) {
        event.register(WandrousRegistries.SPELLS_REGISTER);
        event.register(WandrousRegistries.IMOVER_REGISTER);
        event.register(WandrousRegistries.GLYPH_REGISTER);
    }
}
