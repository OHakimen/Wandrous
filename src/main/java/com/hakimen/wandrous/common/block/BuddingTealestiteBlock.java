package com.hakimen.wandrous.common.block;

import com.hakimen.wandrous.common.registers.BlockRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class BuddingTealestiteBlock extends BuddingAmethystBlock {

    private static final Direction[] DIRECTIONS = Direction.values();


    public BuddingTealestiteBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Override
    protected void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[pRandom.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pPos.relative(direction);
            BlockState blockstate = pLevel.getBlockState(blockpos);
            Block block = null;

            if (canClusterGrowAtState(blockstate)) {
                block = BlockRegister.SMALL_TEALESTITE_BUD.get();
            } else if (blockstate.is(BlockRegister.SMALL_TEALESTITE_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = BlockRegister.MEDIUM_TEALESTITE_BUD.get();
            } else if (blockstate.is(BlockRegister.MEDIUM_TEALESTITE_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = BlockRegister.BIG_TEALESTITE_BUD.get();
            } else if (blockstate.is(BlockRegister.BIG_TEALESTITE_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = BlockRegister.TEALESTITE_CLUSTER.get();
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState()
                        .setValue(AmethystClusterBlock.FACING, direction)
                        .setValue(AmethystClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
                pLevel.setBlockAndUpdate(blockpos, blockstate1);
            }
        }
    }

    public static boolean canClusterGrowAtState(BlockState pState) {
        return pState.isAir() || pState.is(Blocks.WATER) && pState.getFluidState().getAmount() == 8;
    }
}
