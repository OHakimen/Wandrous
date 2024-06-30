package com.hakimen.wandrous.common.datagen;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.registers.BlockRegister;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockStateProvider extends net.neoforged.neoforge.client.model.generators.BlockStateProvider {

    ExistingFileHelper existingFileHelper;
    public BlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Wandrous.MODID, existingFileHelper);
        this.existingFileHelper = existingFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(BlockRegister.CHERT.get());
        stairsBlock(BlockRegister.CHERT_STAIRS.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chert"));
        slabBlock(BlockRegister.CHERT_SLAB.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chert"), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chert"));
        wallBlock(BlockRegister.CHERT_WALL.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chert"));

        simpleBlock(BlockRegister.POLISHED_CHERT.get());
        stairsBlock(BlockRegister.POLISHED_CHERT_STAIRS.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/polished_chert"));
        slabBlock(BlockRegister.POLISHED_CHERT_SLAB.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/polished_chert"), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/polished_chert"));

        simpleBlock(BlockRegister.CHERT_BRICKS.get());
        stairsBlock(BlockRegister.CHERT_BRICKS_STAIRS.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chert_bricks"));
        slabBlock(BlockRegister.CHERT_BRICKS_SLAB.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chert_bricks"), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chert_bricks"));
        wallBlock(BlockRegister.CHERT_BRICKS_WALL.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chert_bricks"));

        simpleBlock(BlockRegister.CRACKED_CHERT_BRICKS.get());
        simpleBlock(BlockRegister.CHISELED_CHERT_BRICKS.get(),
                ConfiguredModel.builder().modelFile(new ModelFile.ExistingModelFile(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chiseled_chert_bricks_0"), existingFileHelper)).buildLast(),
                ConfiguredModel.builder().modelFile(new ModelFile.ExistingModelFile(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chiseled_chert_bricks_1"), existingFileHelper)).buildLast(),
                ConfiguredModel.builder().modelFile(new ModelFile.ExistingModelFile(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chiseled_chert_bricks_2"), existingFileHelper)).buildLast(),
                ConfiguredModel.builder().modelFile(new ModelFile.ExistingModelFile(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chiseled_chert_bricks_3"), existingFileHelper)).buildLast());

        simpleBlock(BlockRegister.MOSSY_CHERT_BRICKS.get());
        stairsBlock(BlockRegister.MOSSY_CHERT_BRICKS_STAIRS.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/mossy_chert_bricks"));
        slabBlock(BlockRegister.MOSSY_CHERT_BRICKS_SLAB.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/mossy_chert_bricks"), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/mossy_chert_bricks"));
        wallBlock(BlockRegister.MOSSY_CHERT_BRICKS_WALL.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/mossy_chert_bricks"));


        axisBlock(BlockRegister.CHERT_PILLAR.get());

        simpleBlock(BlockRegister.CHERT_TILES.get());
        stairsBlock(BlockRegister.CHERT_TILES_STAIRS.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chert_tiles"));
        slabBlock(BlockRegister.CHERT_TILES_SLAB.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chert_tiles"), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chert_tiles"));

        simpleBlock(BlockRegister.MOSSY_CHERT_TILES.get());
        stairsBlock(BlockRegister.MOSSY_CHERT_TILES_STAIRS.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/mossy_chert_tiles"));
        slabBlock(BlockRegister.MOSSY_CHERT_TILES_SLAB.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/mossy_chert_tiles"), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/mossy_chert_tiles"));
    }
}