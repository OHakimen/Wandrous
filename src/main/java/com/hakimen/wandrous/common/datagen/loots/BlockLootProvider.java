package com.hakimen.wandrous.common.datagen.loots;

import com.hakimen.wandrous.common.registers.BlockRegister;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;
import java.util.stream.Collectors;

public class BlockLootProvider extends BlockLootSubProvider {


    public BlockLootProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, provider);
    }

    @Override
    protected void generate() {
        BlockRegister.BLOCKS.getEntries().forEach(blockDeferredHolder -> {
            if (blockDeferredHolder.get() instanceof AmethystClusterBlock && !blockDeferredHolder.equals(BlockRegister.TEALESTITE_CLUSTER)) {
                dropWhenSilkTouch(blockDeferredHolder.get());
            } else if (!blockDeferredHolder.equals(BlockRegister.TEALESTITE_CLUSTER)) {
                dropSelf(blockDeferredHolder.get());
            }
        });
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockRegister.BLOCKS.getEntries().stream().map(DeferredHolder::get)
                .filter(block -> !block.equals(BlockRegister.TEALESTITE_CLUSTER.get()))
                .collect(Collectors.toSet());
    }

}
