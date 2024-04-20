package com.hakimen.wandrous;

import com.hakimen.wandrous.common.client.entity.TimerEntityRenderer;
import com.hakimen.wandrous.common.client.screens.WandTinkerScreen;
import com.hakimen.wandrous.common.registers.ContainerRegister;
import com.hakimen.wandrous.common.registers.EntityRegister;
import net.minecraft.client.gui.screens.MenuScreens;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Wandrous.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WandrousClient {

    @SubscribeEvent
    public static void clientInit(FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            MenuScreens.register(ContainerRegister.WAND_TINKER_MENU.get(), WandTinkerScreen::new);
        });
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(EntityRegister.TIMER_ENTITY.get(), TimerEntityRenderer::new);
    }
}
