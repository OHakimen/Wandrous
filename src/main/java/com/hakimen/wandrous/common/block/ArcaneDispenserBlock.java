package com.hakimen.wandrous.common.block;

import com.hakimen.wandrous.common.block_entity.ArcaneDispenserBlockEntity;
import com.hakimen.wandrous.common.registers.BlockEntityRegister;
import com.mojang.authlib.GameProfile;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.util.FakePlayer;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class ArcaneDispenserBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;

    public ArcaneDispenserBlock() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.DISPENSER));

        registerDefaultState(getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(TRIGGERED, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> properties) {
        properties.add(FACING).add(TRIGGERED);
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    @Deprecated
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @javax.annotation.Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext placement) {
        return defaultBlockState().setValue(FACING, placement.getNearestLookingDirection().getOpposite());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        BlockEntity entity = pLevel.getBlockEntity(pPos);
        if (entity instanceof ArcaneDispenserBlockEntity arcaneDispenserBlockEntity) {
            pPlayer.openMenu(arcaneDispenserBlockEntity, pPos);
            return InteractionResult.SUCCESS;
        }

        return super.useWithoutItem(pState, pLevel, pPos, pPlayer, pHitResult);
    }

    @Override
    protected void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        boolean flag = pLevel.hasNeighborSignal(pPos) || pLevel.hasNeighborSignal(pPos.above());
        boolean flag1 = pState.getValue(TRIGGERED);
        if (flag && !flag1) {
            pLevel.scheduleTick(pPos, this, 4);
            pLevel.setBlock(pPos, pState.setValue(TRIGGERED, Boolean.valueOf(true)), 2);
        } else if (!flag && flag1) {
            pLevel.setBlock(pPos, pState.setValue(TRIGGERED, Boolean.valueOf(false)), 2);
        }
    }

    @Override
    protected void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        pLevel.getBlockEntity(pPos, BlockEntityRegister.ARCANE_DISPENSER.get()).ifPresent(
                arcaneDispenserBlockEntity -> {
                    if(arcaneDispenserBlockEntity.getFakePlayer() == null){
                        arcaneDispenserBlockEntity.setFakePlayer(new FakePlayer((ServerLevel) pLevel, new GameProfile(UUID.randomUUID(), "Arcane Dispenser")));
                    }
                    FakePlayer player = arcaneDispenserBlockEntity.getFakePlayer();
                    Direction dir = pState.getValue(FACING);
                    Vec3 offset = pPos.getCenter().add(dir.getNormal().getX()-0.5f,dir.getNormal().getY()-0.5f,dir.getNormal().getZ()-0.5f);
                    player.setPos(offset.add(0.5, -0.5,0.5));
                    player.xo = player.getX();
                    player.yo = player.getY();
                    player.zo = player.getZ();
                    player.lookAt(EntityAnchorArgument.Anchor.EYES, offset.relative(dir,10));
                    ItemStack stack = arcaneDispenserBlockEntity.getItemHandler().getStackInSlot(0);
                    player.setItemInHand(InteractionHand.MAIN_HAND, stack);
                    if(!stack.isEmpty()){
                        stack.getItem().onUseTick(pLevel,player, stack, 100);
                    }
                }
        );
    }

    @Override
    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof ArcaneDispenserBlockEntity arcaneDispenser) {
                for (int i = 0; i < arcaneDispenser.getItemHandler().getSlots(); i++) {
                    popResource(pLevel, pPos, arcaneDispenser.getItemHandler().getStackInSlot(i));
                }
            }
            super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? null
                : (level, pos, state, blockEntity) -> ((ArcaneDispenserBlockEntity) blockEntity).tick(level, pos, state, (ArcaneDispenserBlockEntity) blockEntity);

    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ArcaneDispenserBlockEntity(pPos, pState);
    }
}
