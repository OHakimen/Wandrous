package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.entity.TimerEntity;
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
            (entityType, level) -> new TimerEntity(entityType, level, 0), MobCategory.MISC).sized(0.5f,0.5f).build(new ResourceLocation(Wandrous.MODID, "timer").toString()));

    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}

