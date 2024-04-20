package com.hakimen.wandrous.common.block;

import com.hakimen.wandrous.common.block_entity.ConjuredBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ConjuredBlock extends Block implements EntityBlock {
    public ConjuredBlock() {
        super(Properties.of()
                .destroyTime(0)
                .sound(SoundType.AMETHYST)
                .isViewBlocking((blockState, blockGetter, blockPos) -> false)
                .isValidSpawn((blockState, blockGetter, blockPos, entityType) -> false)
                .isSuffocating((blockState, blockGetter, blockPos) -> false)
                .lightLevel(value -> 7)
                .explosionResistance(0)
                .instabreak()
                .noOcclusion());
    }

    @Override
    protected void spawnDestroyParticles(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState) {
        pLevel.playLocalSound(pPos, this.soundType.getBreakSound(), SoundSource.BLOCKS, 1f,1f, false);
    }

    @Override
    public VoxelShape getVisualShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.empty();
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentState, Direction pDirection) {
        return pAdjacentState.is(this) ? true : super.skipRendering(pState, pAdjacentState, pDirection);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return true;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ConjuredBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return ((level, blockPos, blockState, t) -> {
            ((ConjuredBlockEntity)t).tick(level,blockPos,blockState,(ConjuredBlockEntity) t);
        });
    }
}
