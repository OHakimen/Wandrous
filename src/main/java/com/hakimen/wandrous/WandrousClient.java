package com.hakimen.wandrous;

import com.hakimen.wandrous.client.entity.*;
import com.hakimen.wandrous.client.screens.WandTinkerScreen;
import com.hakimen.wandrous.common.particle.GlimmeringBoltParticle;
import com.hakimen.wandrous.common.registers.ContainerRegister;
import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.registers.ParticleRegister;
import net.minecraft.client.Minecraft;
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

        event.registerEntityRenderer(EntityRegister.GLIMMERING_BOLT_PROJECTILE.get(), GlimmeringBoltProjectileRenderer::new);
        event.registerEntityRenderer(EntityRegister.FLAMING_BOLT_PROJECTILE.get(), FlamingBoltProjectileRenderer::new);
        event.registerEntityRenderer(EntityRegister.BLACK_HOLE_PROJECTILE.get(), BlackHoleProjectileRenderer::new);
        event.registerEntityRenderer(EntityRegister.SONIC_BOOM_PROJECTILE.get(), SonicBoomProjectileRenderer::new);

        Minecraft.getInstance().particleEngine.register(ParticleRegister.GLIMMERING_BOLT.get(),
                GlimmeringBoltParticle.GlimmeringProvider::new);

        Minecraft.getInstance().particleEngine.register(ParticleRegister.GLIMMERING_BOLT_HIT.get(),
                GlimmeringBoltParticle.GlimmeringHitProvider::new);
    }
}
