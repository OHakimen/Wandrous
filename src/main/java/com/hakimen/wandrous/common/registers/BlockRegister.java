package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.block.ConjuredBlock;
import com.hakimen.wandrous.common.block.ConjuredLightBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, Wandrous.MODID);

    public static final DeferredHolder<Block, ConjuredLightBlock> CONJURED_LIGHT_BLOCK = BLOCKS.register("conjured_light", ConjuredLightBlock::new);
    public static final DeferredHolder<Block, ConjuredBlock> CONJURED_BLOCK = BLOCKS.register("conjured_block", ConjuredBlock::new);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
