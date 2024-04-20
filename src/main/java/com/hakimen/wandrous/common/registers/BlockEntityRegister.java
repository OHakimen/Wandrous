package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.block_entity.ConjuredBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockEntityRegister {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Wandrous.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ConjuredBlockEntity>> CONJURED_BLOCK_ENTITY = BLOCK_ENTITIES.register("conjured_light",
            () -> BlockEntityType.Builder
                    .of(ConjuredBlockEntity::new, BlockRegister.CONJURED_LIGHT_BLOCK.get(), BlockRegister.CONJURED_BLOCK.get())
                    .build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
