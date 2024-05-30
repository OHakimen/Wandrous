package com.hakimen.wandrous.client.event;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.model.DynamicTextureModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = Wandrous.MODID)
public class AddResourceListener {

    @SubscribeEvent
    public static void addResourceListener(RegisterClientReloadListenersEvent listener){
        listener.registerReloadListener(DynamicTextureModel::reload);
    }
}
