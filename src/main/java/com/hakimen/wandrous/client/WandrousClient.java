package com.hakimen.wandrous.client;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.ber.ArcaneInscriberRenderer;
import com.hakimen.wandrous.client.ber.GlyphProjectorRenderer;
import com.hakimen.wandrous.client.entity.*;
import com.hakimen.wandrous.client.screens.WandTinkerScreen;
import com.hakimen.wandrous.common.particle.ArcaneKnowledgeParticle;
import com.hakimen.wandrous.common.particle.GlimmeringBoltParticle;
import com.hakimen.wandrous.common.particle.ShockwaveParticle;
import com.hakimen.wandrous.common.registers.BlockEntityRegister;
import com.hakimen.wandrous.common.registers.ContainerRegister;
import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.registers.ParticleRegister;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(value = Dist.CLIENT, modid = Wandrous.MODID, bus = EventBusSubscriber.Bus.MOD)
public class WandrousClient {


    @SubscribeEvent
    public static void clientInit(RegisterMenuScreensEvent event){
        event.register(ContainerRegister.WAND_TINKER_MENU.get(), WandTinkerScreen::new);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(BlockEntityRegister.GLYPH_PROJECTOR_ENTITY.get(), GlyphProjectorRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegister.ARCANE_INSCRIBER.get(), ArcaneInscriberRenderer::new);

        event.registerEntityRenderer(EntityRegister.TIMER_ENTITY.get(), TimerEntityRenderer::new);
        event.registerEntityRenderer(EntityRegister.NUKE_ENTITY.get(), NukeRenderer::new);
        event.registerEntityRenderer(EntityRegister.BOMB.get(), BombRenderer::new);
        event.registerEntityRenderer(EntityRegister.TRIGGER_GLYPH.get(), TriggeringGlyphRenderer::new);

        event.registerEntityRenderer(EntityRegister.GLIMMERING_BOLT_PROJECTILE.get(), GlimmeringBoltProjectileRenderer::new);
        event.registerEntityRenderer(EntityRegister.FLAMING_BOLT_PROJECTILE.get(), FlamingBoltProjectileRenderer::new);
        event.registerEntityRenderer(EntityRegister.BLACK_HOLE_PROJECTILE.get(), BlackHoleProjectileRenderer::new);
        event.registerEntityRenderer(EntityRegister.SONIC_BOOM_PROJECTILE.get(), SonicBoomProjectileRenderer::new);
        event.registerEntityRenderer(EntityRegister.CHAIN_SHOT.get(), ChainShotProjectileRenderer::new);
        event.registerEntityRenderer(EntityRegister.CHAIN_PRISON.get(), ChainPrisonRenderer::new);

        Minecraft.getInstance().particleEngine.register(ParticleRegister.GLIMMERING_BOLT.get(),
                GlimmeringBoltParticle.GlimmeringProvider::new);

        Minecraft.getInstance().particleEngine.register(ParticleRegister.GLIMMERING_BOLT_HIT.get(),
                GlimmeringBoltParticle.GlimmeringHitProvider::new);

        Minecraft.getInstance().particleEngine.register(ParticleRegister.KNOWLEDGE.get(),
                ArcaneKnowledgeParticle.ArcaneKnowledgeParticleProvider::new);

        Minecraft.getInstance().particleEngine.register(ParticleRegister.SHOCKWAVE.get(),
                ShockwaveParticle.ShockwaveProvider::new);
    }

}
