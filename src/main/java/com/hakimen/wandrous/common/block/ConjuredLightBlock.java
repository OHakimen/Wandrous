package com.hakimen.wandrous.common.block;

import com.hakimen.wandrous.common.block_entity.ConjuredBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ConjuredLightBlock extends Block implements EntityBlock {
    public ConjuredLightBlock() {
        super(Properties.of()
                .destroyTime(0)
                .sound(SoundType.AMETHYST)
                .isViewBlocking((blockState, blockGetter, blockPos) -> false)
                .isValidSpawn((blockState, blockGetter, blockPos, entityType) -> false)
                .lightLevel(value -> 15)
                .explosionResistance(0)
                .instabreak()
                .noCollission()
                .noOcclusion());
    }

    @Override
    protected void spawnDestroyParticles(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState) {
        pLevel.playLocalSound(pPos, this.soundType.getBreakSound(), SoundSource.BLOCKS, 1f,1f, false);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ConjuredBlockEntity(blockPos, blockState);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Block.box(4,4,4,12,12,12);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return ((level, blockPos, blockState, t) -> {
            ((ConjuredBlockEntity)t).tick(level,blockPos,blockState,(ConjuredBlockEntity) t);
        });
    }
}
