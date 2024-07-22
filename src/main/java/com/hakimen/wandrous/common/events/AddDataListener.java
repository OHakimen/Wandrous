package com.hakimen.wandrous.common.events;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.data.ScrollDataListener;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = Wandrous.MODID)
public class AddDataListener {

    @SubscribeEvent
    public static void addDataListeners(AddReloadListenerEvent event){
        event.addListener(ScrollDataListener::reload);
    }
}
