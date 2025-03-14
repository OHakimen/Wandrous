package com.hakimen.wandrous.common.datagen;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.block.ArcaneDispenserBlock;
import com.hakimen.wandrous.common.registers.BlockRegister;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
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


        simpleBlock(BlockRegister.TEALESTITE_BLOCK.get());
        simpleBlock(BlockRegister.BUDDING_TEALESTITE.get());

        simpleBlock(BlockRegister.GLYPH_PROJECTOR.get(), new ModelFile.ExistingModelFile(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/glyph_projector"), existingFileHelper));

        simpleBlock(BlockRegister.ARCANE_INSCRIBER.get(), new ModelFile.ExistingModelFile(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/arcane_inscriber"), existingFileHelper));

        directionalBlock(BlockRegister.SMALL_TEALESTITE_BUD.get(),
                new ModelFile.ExistingModelFile(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/small_tealestite_bud"), existingFileHelper));
        directionalBlock(BlockRegister.MEDIUM_TEALESTITE_BUD.get(),
                new ModelFile.ExistingModelFile(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/medium_tealestite_bud"), existingFileHelper));
        directionalBlock(BlockRegister.BIG_TEALESTITE_BUD.get(),
                new ModelFile.ExistingModelFile(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/big_tealestite_bud"), existingFileHelper));
        directionalBlock(BlockRegister.TEALESTITE_CLUSTER.get(),
                new ModelFile.ExistingModelFile(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/tealestite_cluster"), existingFileHelper));

        arcaneDispenser(BlockRegister.ARCANE_DISPENSER.get(),
                new ModelFile.ExistingModelFile(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/arcane_dispenser"), existingFileHelper),
                new ModelFile.ExistingModelFile(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/arcane_dispenser_vertical"), existingFileHelper));
    }

    public void arcaneDispenser(Block block, ModelFile horizontal, ModelFile vertical){
        getVariantBuilder(block)
                .partialState().with(ArcaneDispenserBlock.FACING, Direction.DOWN)
                .modelForState().modelFile(vertical).rotationX(180).addModel()
                .partialState().with(ArcaneDispenserBlock.FACING, Direction.EAST)
                .modelForState().modelFile(horizontal).rotationY(90).addModel()
                .partialState().with(ArcaneDispenserBlock.FACING, Direction.NORTH)
                .modelForState().modelFile(horizontal).addModel()
                .partialState().with(ArcaneDispenserBlock.FACING, Direction.SOUTH)
                .modelForState().modelFile(horizontal).rotationY(180).addModel()
                .partialState().with(ArcaneDispenserBlock.FACING, Direction.UP)
                .modelForState().modelFile(vertical).addModel()
                .partialState().with(ArcaneDispenserBlock.FACING, Direction.WEST)
                .modelForState().modelFile(horizontal).rotationY(270).addModel();

    }

}