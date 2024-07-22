package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.block.BuddingTealestiteBlock;
import com.hakimen.wandrous.common.block.ConjuredBlock;
import com.hakimen.wandrous.common.block.ConjuredLightBlock;
import com.hakimen.wandrous.common.block.GlyphProjectorBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.minecraft.world.level.block.Blocks.AMETHYST_CLUSTER;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, Wandrous.MODID);

    public static final DeferredHolder<Block, ConjuredLightBlock> CONJURED_LIGHT_BLOCK = BLOCKS.register("conjured_light", ConjuredLightBlock::new);
    public static final DeferredHolder<Block, ConjuredBlock> CONJURED_BLOCK = BLOCKS.register("conjured_block", ConjuredBlock::new);

    public static final DeferredHolder<Block, Block> CHERT = BLOCKS.register("chert", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, StairBlock> CHERT_STAIRS = BLOCKS.register("chert_stairs", () -> new StairBlock(BlockRegister.CHERT.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, SlabBlock> CHERT_SLAB = BLOCKS.register("chert_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, WallBlock> CHERT_WALL = BLOCKS.register("chert_wall", () -> new WallBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.STONE).forceSolidOn()));


    public static final DeferredHolder<Block, Block> POLISHED_CHERT = BLOCKS.register("polished_chert", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, StairBlock> POLISHED_CHERT_STAIRS = BLOCKS.register("polished_chert_stairs", () -> new StairBlock(BlockRegister.POLISHED_CHERT.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, SlabBlock> POLISHED_CHERT_SLAB = BLOCKS.register("polished_chert_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));


    public static final DeferredHolder<Block, Block> CHERT_BRICKS = BLOCKS.register("chert_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, StairBlock> CHERT_BRICKS_STAIRS = BLOCKS.register("chert_bricks_stairs", () -> new StairBlock(BlockRegister.CHERT_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, SlabBlock> CHERT_BRICKS_SLAB = BLOCKS.register("chert_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, WallBlock> CHERT_BRICKS_WALL = BLOCKS.register("chert_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.STONE).forceSolidOn()));

    public static final DeferredHolder<Block, Block> CRACKED_CHERT_BRICKS = BLOCKS.register("cracked_chert_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, Block> CHISELED_CHERT_BRICKS = BLOCKS.register("chiseled_chert_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> MOSSY_CHERT_BRICKS = BLOCKS.register("mossy_chert_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, StairBlock> MOSSY_CHERT_BRICKS_STAIRS = BLOCKS.register("mossy_chert_bricks_stairs", () -> new StairBlock(BlockRegister.MOSSY_CHERT_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, SlabBlock> MOSSY_CHERT_BRICKS_SLAB = BLOCKS.register("mossy_chert_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, WallBlock> MOSSY_CHERT_BRICKS_WALL = BLOCKS.register("mossy_chert_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.STONE).forceSolidOn()));


    public static final DeferredHolder<Block, RotatedPillarBlock> CHERT_PILLAR = BLOCKS.register("chert_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CHERT_TILES = BLOCKS.register("chert_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, StairBlock> CHERT_TILES_STAIRS = BLOCKS.register("chert_tiles_stairs", () -> new StairBlock(BlockRegister.CHERT_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, SlabBlock> CHERT_TILES_SLAB = BLOCKS.register("chert_tiles_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> MOSSY_CHERT_TILES = BLOCKS.register("mossy_chert_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, StairBlock>  MOSSY_CHERT_TILES_STAIRS = BLOCKS.register("mossy_chert_tiles_stairs", () -> new StairBlock(BlockRegister.MOSSY_CHERT_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredHolder<Block, SlabBlock>  MOSSY_CHERT_TILES_SLAB = BLOCKS.register("mossy_chert_tiles_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> TEALESTITE_BLOCK = BLOCKS.register("tealestite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)));
    public static final DeferredHolder<Block, BuddingTealestiteBlock> BUDDING_TEALESTITE = BLOCKS.register("budding_tealestite", () -> new BuddingTealestiteBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST)));

    public static final DeferredHolder<Block, AmethystClusterBlock> SMALL_TEALESTITE_BUD = BLOCKS.register("small_tealestite_bud", () -> new AmethystClusterBlock(
            3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy(AMETHYST_CLUSTER).sound(SoundType.SMALL_AMETHYST_BUD).lightLevel(state -> 1)
    ));

    public static final DeferredHolder<Block, AmethystClusterBlock> MEDIUM_TEALESTITE_BUD = BLOCKS.register("medium_tealestite_bud", () ->  new AmethystClusterBlock(
            4.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(AMETHYST_CLUSTER).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel(state -> 2)
    ));

    public static final DeferredHolder<Block, AmethystClusterBlock> BIG_TEALESTITE_BUD = BLOCKS.register("big_tealestite_bud", () ->  new AmethystClusterBlock(
            5.0F, 3.0F, BlockBehaviour.Properties.ofLegacyCopy(AMETHYST_CLUSTER).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel(p_152629_ -> 4)
    ));

    public static final DeferredHolder<Block, AmethystClusterBlock> TEALESTITE_CLUSTER = BLOCKS.register("tealestite_cluster", () ->   new AmethystClusterBlock(
            7.0F,
            3.0F,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .forceSolidOn()
                    .noOcclusion()
                    .sound(SoundType.AMETHYST_CLUSTER)
                    .strength(1.5F)
                    .lightLevel(state -> 5)
                    .pushReaction(PushReaction.DESTROY)
    ));

    public static final DeferredHolder<Block, GlyphProjectorBlock> GLYPH_PROJECTOR = BLOCKS.register("glyph_projector", () -> new GlyphProjectorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
            .noOcclusion()
            .lightLevel(state -> 5)
            .isValidSpawn((pState, pLevel, pPos, entityType) -> false)
            .isRedstoneConductor((pState, pLevel, pPos) -> false)
            .isSuffocating((pState, pLevel, pPos) -> false)
            .isViewBlocking((pState, pLevel, pPos) -> false)
    ));



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
