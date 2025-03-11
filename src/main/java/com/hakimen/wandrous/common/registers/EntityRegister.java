package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.entity.projectiles.*;
import com.hakimen.wandrous.common.entity.static_spell.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EntityRegister {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, Wandrous.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<TimerEntity>> TIMER_ENTITY = ENTITIES.register("timer", () -> EntityType.Builder.<TimerEntity>of(
            (entityType, level) -> new TimerEntity(entityType, level, -1), MobCategory.MISC).sized(0.5f,0.5f).build(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "timer").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<NukeEntity>> NUKE_ENTITY = ENTITIES.register("nuke", () -> EntityType.Builder.<NukeEntity>of(
            (entityType, level) -> new NukeEntity(level, -1, new Vec3(0,0,0)), MobCategory.MISC).sized(0.5f,0.5f).build(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "nuke").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<ChainPrisonEntity>> CHAIN_PRISON = ENTITIES.register("chain_prison", () -> EntityType.Builder.<ChainPrisonEntity>of(
            ChainPrisonEntity::new, MobCategory.MISC).sized(0.5f,0.5f).build(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "chain_prison").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<TriggeringGlyphEntity>> TRIGGER_GLYPH = ENTITIES.register("trigger_glyph", () -> EntityType.Builder.<TriggeringGlyphEntity>of(
            TriggeringGlyphEntity::new, MobCategory.MISC).sized(0.5f,0.5f).build(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "trigger_glyph").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<PlasmaBeamEntity>> PLASMA_BEAM = ENTITIES.register("plasma_beam", () -> EntityType.Builder.<PlasmaBeamEntity>of(
            PlasmaBeamEntity::new, MobCategory.MISC).sized(0.5f,0.5f).build(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "plasma_beam").toString()));


    public static final DeferredHolder<EntityType<?>, EntityType<GlimmeringBoltProjectile>> GLIMMERING_BOLT_PROJECTILE = ENTITIES.register("glimmering_bolt", () -> EntityType.Builder.<GlimmeringBoltProjectile>of(
            GlimmeringBoltProjectile::new, MobCategory.MISC).sized(0.5f,0.5f).build(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "glimmering_bolt").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<FlamingBoltProjectile>> FLAMING_BOLT_PROJECTILE = ENTITIES.register("flaming_bolt", () -> EntityType.Builder.<FlamingBoltProjectile>of(
            FlamingBoltProjectile::new, MobCategory.MISC).sized(0.5f,0.5f).build(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "flaming_bolt").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BlackHoleProjectile>> BLACK_HOLE_PROJECTILE = ENTITIES.register("black_hole", () -> EntityType.Builder.<BlackHoleProjectile>of(
            BlackHoleProjectile::new, MobCategory.MISC).sized(1f,1f).build(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "black_hole").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SonicBoomProjectile>> SONIC_BOOM_PROJECTILE = ENTITIES.register("sonic_boom", () -> EntityType.Builder.<SonicBoomProjectile>of(
            SonicBoomProjectile::new, MobCategory.MISC).sized(0.5f,0.5f).build(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "sonic_boom").toString()));


    public static final DeferredHolder<EntityType<?>, EntityType<BombProjectile>> BOMB = ENTITIES.register("bomb", () -> EntityType.Builder.<BombProjectile>of(
            BombProjectile::new, MobCategory.MISC).sized(0.5f,0.5f).build(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "bomb").toString()));


    public static final DeferredHolder<EntityType<?>, EntityType<ChainShotProjectile>> CHAIN_SHOT = ENTITIES.register("chain_shot", () -> EntityType.Builder.<ChainShotProjectile>of(
            ChainShotProjectile::new, MobCategory.MISC).sized(0.5f,0.5f).build(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "chain_shot").toString()));





    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}

