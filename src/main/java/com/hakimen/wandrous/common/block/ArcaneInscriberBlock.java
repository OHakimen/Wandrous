package com.hakimen.wandrous.common.block;

import com.hakimen.wandrous.common.block_entity.ArcaneInscriberBlockEntity;
import com.hakimen.wandrous.common.registers.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ArcaneInscriberBlock extends Block implements EntityBlock {
    public ArcaneInscriberBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return box(0,0,0,16,6,16);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        Optional<ArcaneInscriberBlockEntity> blockEntity = pLevel.getBlockEntity(pPos, BlockEntityRegister.ARCANE_INSCRIBER.get());
        if (blockEntity.isPresent()) {
            ArcaneInscriberBlockEntity arcaneInscriberBlockEntity = blockEntity.get();

            ItemStack currentItem = arcaneInscriberBlockEntity.getInventory().getStackInSlot(0);

            if(arcaneInscriberBlockEntity.getInventory().isItemValid(0,pStack)){
                if(!currentItem.isEmpty() && pStack.isEmpty()) {
                    arcaneInscriberBlockEntity.getInventory().insertItem(0, pStack.copy(), false);
                    pStack.shrink(1);
                    if(!pPlayer.addItem(currentItem)){
                        pLevel.addFreshEntity(new ItemEntity(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), currentItem));
                    }
                    pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 1f, 0.8f + pLevel.getRandom().nextFloat() / 4f);
                    arcaneInscriberBlockEntity.setChanged();
                    return ItemInteractionResult.SUCCESS;
                } else if(!pStack.isEmpty()) {
                    ItemStack extracted = arcaneInscriberBlockEntity.getInventory().extractItem(0, 1, false);
                    arcaneInscriberBlockEntity.getInventory().insertItem(0, pStack.copyWithCount(1), false);
                    pStack.shrink(1);
                    if(!pPlayer.addItem(extracted)){
                        pLevel.addFreshEntity(new ItemEntity(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), extracted));
                    }
                    pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 1f,0.8f + pLevel.getRandom().nextFloat()/4f);
                    arcaneInscriberBlockEntity.setChanged();
                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        if(pStack.isEmpty()) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        return ItemInteractionResult.FAIL;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        Optional<ArcaneInscriberBlockEntity> blockEntity = pLevel.getBlockEntity(pPos, BlockEntityRegister.ARCANE_INSCRIBER.get());
        if (blockEntity.isPresent()) {
            ArcaneInscriberBlockEntity arcaneInscriberBlockEntity = blockEntity.get();

            ItemStack currentItem = arcaneInscriberBlockEntity.getInventory().getStackInSlot(0);
            if(!currentItem.isEmpty()){
                ItemStack extracted = arcaneInscriberBlockEntity.getInventory().extractItem(0, 1, false);
                if(!pPlayer.addItem(extracted)){
                    pLevel.addFreshEntity(new ItemEntity(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), extracted));
                }
                pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 1f,0.8f + pLevel.getRandom().nextFloat()/4f);
                arcaneInscriberBlockEntity.setChanged();
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public BlockState playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        Optional<ArcaneInscriberBlockEntity> blockEntity = pLevel.getBlockEntity(pPos, BlockEntityRegister.ARCANE_INSCRIBER.get());
        if (blockEntity.isPresent()) {
            ArcaneInscriberBlockEntity arcaneInscriberBlockEntity = blockEntity.get();
            popResource(pLevel, pPos, arcaneInscriberBlockEntity.getInventory().getStackInSlot(0));
        }
        return super.playerWillDestroy(pLevel, pPos, pState, pPlayer);

    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ArcaneInscriberBlockEntity(pPos,pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? (pLevel1, pPos, pState1, pBlockEntity) -> {
            BlockEntity be = pBlockEntityType.getBlockEntity(pLevel1,pPos);
            if(be instanceof ArcaneInscriberBlockEntity arcaneInscriberBlockEntity) {
                arcaneInscriberBlockEntity.clientTick(pLevel,pPos,pState,arcaneInscriberBlockEntity);
            }
        } : (pLevel1, pPos, pState1, pBlockEntity) -> {
            BlockEntity be = pBlockEntityType.getBlockEntity(pLevel1,pPos);
            if(be instanceof ArcaneInscriberBlockEntity arcaneInscriberBlockEntity) {
                arcaneInscriberBlockEntity.tick(pLevel,pPos,pState,arcaneInscriberBlockEntity);
            }
        };
    }
}
