package com.hakimen.wandrous.common.block;

import com.hakimen.wandrous.common.block_entity.GlyphProjectorBlockEntity;
import com.hakimen.wandrous.common.item.InscribedLensItem;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class GlyphProjectorBlock extends TransparentBlock implements EntityBlock {


    public GlyphProjectorBlock(Properties props) {
        super(props);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new GlyphProjectorBlockEntity(pPos, pState);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        Optional<GlyphProjectorBlockEntity> blockEntity = pLevel.getBlockEntity(pPos, BlockEntityRegister.GLYPH_PROJECTOR_ENTITY.get());
        if (blockEntity.isPresent()) {
            GlyphProjectorBlockEntity glyphProjectorBlockEntity = blockEntity.get();

            ItemStack currentItem = glyphProjectorBlockEntity.getInventory().getStackInSlot(0);

            if(glyphProjectorBlockEntity.getInventory().isItemValid(0,pStack)){
                if(currentItem.isEmpty()) {
                    glyphProjectorBlockEntity.getInventory().insertItem(0, pStack.copy(), false);
                    pStack.shrink(1);
                    if(!pPlayer.addItem(currentItem)){
                        pLevel.addFreshEntity(new ItemEntity(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), currentItem));
                    }
                    pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 1f,0.8f + pLevel.getRandom().nextFloat()/4f);
                    return ItemInteractionResult.SUCCESS;
                } else if(currentItem.getItem() instanceof InscribedLensItem) {
                    ItemStack extracted = glyphProjectorBlockEntity.getInventory().extractItem(0, 1, false);
                    glyphProjectorBlockEntity.getInventory().insertItem(0, pStack.copy(), false);
                    pStack.shrink(1);
                    if(!pPlayer.addItem(extracted)){
                        pLevel.addFreshEntity(new ItemEntity(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), extracted));
                    }
                    pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 1f,0.8f + pLevel.getRandom().nextFloat()/4f);
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
        Optional<GlyphProjectorBlockEntity> blockEntity = pLevel.getBlockEntity(pPos, BlockEntityRegister.GLYPH_PROJECTOR_ENTITY.get());
        if (blockEntity.isPresent()) {
            GlyphProjectorBlockEntity glyphProjectorBlockEntity = blockEntity.get();

            ItemStack currentItem = glyphProjectorBlockEntity.getInventory().getStackInSlot(0);
            if(!currentItem.isEmpty()){
                ItemStack extracted = glyphProjectorBlockEntity.getInventory().extractItem(0, 1, false);
                if(!pPlayer.addItem(extracted)){
                    pLevel.addFreshEntity(new ItemEntity(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), extracted));
                }
                pLevel.playSound(pPlayer, pPos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 1f,0.8f + pLevel.getRandom().nextFloat()/4f);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public BlockState playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        Optional<GlyphProjectorBlockEntity> blockEntity = pLevel.getBlockEntity(pPos, BlockEntityRegister.GLYPH_PROJECTOR_ENTITY.get());
        if (blockEntity.isPresent()) {
            GlyphProjectorBlockEntity glyphProjectorBlockEntity = blockEntity.get();
            popResource(pLevel, pPos, glyphProjectorBlockEntity.getInventory().getStackInSlot(0));
        }
        return super.playerWillDestroy(pLevel, pPos, pState, pPlayer);

    }

}
