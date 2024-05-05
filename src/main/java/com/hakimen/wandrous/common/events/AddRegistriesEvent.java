package com.hakimen.wandrous.common.events;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.custom.register.WandrousRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.NewRegistryEvent;

@Mod.EventBusSubscriber(modid = Wandrous.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AddRegistriesEvent {
    @SubscribeEvent
    static void registerRegistries(NewRegistryEvent event) {
        event.register(WandrousRegistries.SPELLS_REGISTER);
        event.register(WandrousRegistries.IMOVER_REGISTER);
    }
}
