package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.entity.TimerEntity;
import com.hakimen.wandrous.common.entity.projectiles.BlackHoleProjectile;
import com.hakimen.wandrous.common.entity.projectiles.FlamingBoltProjectile;
import com.hakimen.wandrous.common.entity.projectiles.GlimmeringBoltProjectile;
import com.hakimen.wandrous.common.entity.projectiles.SonicBoomProjectile;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EntityRegister {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, Wandrous.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<TimerEntity>> TIMER_ENTITY = ENTITIES.register("timer", () -> EntityType.Builder.<TimerEntity>of(
            (entityType, level) -> new TimerEntity(entityType, level, 200), MobCategory.MISC).sized(0.5f,0.5f).build(new ResourceLocation(Wandrous.MODID, "timer").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<GlimmeringBoltProjectile>> GLIMMERING_BOLT_PROJECTILE = ENTITIES.register("glimmering_bolt", () -> EntityType.Builder.<GlimmeringBoltProjectile>of(
            GlimmeringBoltProjectile::new, MobCategory.MISC).sized(0.5f,0.5f).build(new ResourceLocation(Wandrous.MODID, "glimmering_bolt").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<FlamingBoltProjectile>> FLAMING_BOLT_PROJECTILE = ENTITIES.register("flaming_bolt", () -> EntityType.Builder.<FlamingBoltProjectile>of(
            FlamingBoltProjectile::new, MobCategory.MISC).sized(0.5f,0.5f).build(new ResourceLocation(Wandrous.MODID, "flaming_bolt").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<BlackHoleProjectile>> BLACK_HOLE_PROJECTILE = ENTITIES.register("black_hole", () -> EntityType.Builder.<BlackHoleProjectile>of(
            BlackHoleProjectile::new, MobCategory.MISC).sized(1f,1f).build(new ResourceLocation(Wandrous.MODID, "black_hole").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SonicBoomProjectile>> SONIC_BOOM_PROJECTILE = ENTITIES.register("sonic_boom", () -> EntityType.Builder.<SonicBoomProjectile>of(
            SonicBoomProjectile::new, MobCategory.MISC).sized(0.5f,0.5f).build(new ResourceLocation(Wandrous.MODID, "sonic_boom").toString()));


    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}

