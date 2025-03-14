package com.hakimen.wandrous.common.block_entity;

import com.hakimen.wandrous.client.menus.ArcaneDispenserMenu;
import com.hakimen.wandrous.common.item.WandItem;
import com.hakimen.wandrous.common.registers.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class ArcaneDispenserBlockEntity extends BlockEntity implements BlockEntityTicker<ArcaneDispenserBlockEntity>, MenuProvider {
    ItemStackHandler itemHandler = createHandler(1);
    FakePlayer player;


    public ArcaneDispenserBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegister.ARCANE_DISPENSER.get(), pPos, pBlockState);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("Wand"));
        super.loadAdditional(pTag, pRegistries);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {;
        super.saveAdditional(pTag, pRegistries);
        pTag.put("Wand",itemHandler.serializeNBT(pRegistries));
    }


    private ItemStackHandler createHandler(int size) {
        return new ItemStackHandler(size) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.getItem() instanceof WandItem;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 64;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return this.saveWithFullMetadata(pRegistries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        // Will get tag from #getUpdateTag
        return ClientboundBlockEntityDataPacket.create(this,BlockEntity::saveWithFullMetadata);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.wandrous.arcane_dispenser");
    }


    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
        return new ArcaneDispenserMenu(windowId, playerInventory, playerEntity, this);
    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState, ArcaneDispenserBlockEntity pBlockEntity) {
        ItemStack stack = itemHandler.getStackInSlot(0);
        if(!stack.isEmpty()){
            stack.getItem().inventoryTick(stack, pLevel, player, 0, false);
        }
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    public FakePlayer getFakePlayer() {
        return player;
    }

    public ArcaneDispenserBlockEntity setFakePlayer(FakePlayer player) {
        this.player = player;
        return this;
    }
}
