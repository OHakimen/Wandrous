package com.hakimen.wandrous.common.events;

import com.hakimen.wandrous.Wandrous;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Wandrous.MODID, bus = EventBusSubscriber.Bus.GAME)
public class RegisterCommandsEvent {

    @SubscribeEvent
    public static void registerCommands(net.neoforged.neoforge.event.RegisterCommandsEvent event){

    }
}
