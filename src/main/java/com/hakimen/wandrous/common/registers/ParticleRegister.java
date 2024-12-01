package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.particle.ArcaneKnowledgeParticle.ArcaneKnowledgeParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ParticleRegister {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Wandrous.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GLIMMERING_BOLT =
            PARTICLE_TYPES.register("glimmering_bolt", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CHAIN_SHOT =
            PARTICLE_TYPES.register("chain_shot", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GLIMMERING_BOLT_HIT =
            PARTICLE_TYPES.register("glimmering_bolt_hit", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, ArcaneKnowledgeParticleType> KNOWLEDGE =
            PARTICLE_TYPES.register("arcane_knowledge", ArcaneKnowledgeParticleType::new);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SHOCKWAVE =
            PARTICLE_TYPES.register("shockwave", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FIERY_PARTICLES =
            PARTICLE_TYPES.register("fiery", () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FREEZING_GAZE =
            PARTICLE_TYPES.register("freezing_gaze", () -> new SimpleParticleType(true));


    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
