package com.hakimen.wandrous.common.datagen;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.registers.BlockRegister;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class BlockTagProvider extends BlockTagsProvider {
    public BlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Wandrous.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.WALLS)
                .add(BlockRegister.CHERT_BRICKS_WALL.get())
                .add(BlockRegister.MOSSY_CHERT_BRICKS_WALL.get())
                .add(BlockRegister.CHERT_WALL.get());

        tag(BlockTags.STAIRS)
                .add(BlockRegister.CHERT_TILES_STAIRS.get())
                .add(BlockRegister.MOSSY_CHERT_TILES_STAIRS.get())
                .add(BlockRegister.POLISHED_CHERT_STAIRS.get())
                .add(BlockRegister.CHERT_BRICKS_STAIRS.get())
                .add(BlockRegister.MOSSY_CHERT_BRICKS_STAIRS.get())
                .add(BlockRegister.CHERT_STAIRS.get());

        tag(BlockTags.SLABS)
                .add(BlockRegister.CHERT_TILES_SLAB.get())
                .add(BlockRegister.MOSSY_CHERT_TILES_SLAB.get())
                .add(BlockRegister.POLISHED_CHERT_SLAB.get())
                .add(BlockRegister.CHERT_BRICKS_SLAB.get())
                .add(BlockRegister.MOSSY_CHERT_BRICKS_SLAB.get())
                .add(BlockRegister.CHERT_SLAB.get());

        tag(Tags.Blocks.STONES)
                .add(BlockRegister.CHERT.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(BlockRegister.ARCANE_INSCRIBER.get())
                .add(BlockRegister.GLYPH_PROJECTOR.get());

        for (Block b: BlockRegister.BLOCKS.getEntries().stream().map(DeferredHolder::get).collect(Collectors.toSet())) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(b);
        }
    }
}
