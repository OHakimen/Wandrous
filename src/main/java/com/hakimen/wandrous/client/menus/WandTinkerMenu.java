package com.hakimen.wandrous.client.menus;

import com.hakimen.wandrous.common.item.WandItem;
import com.hakimen.wandrous.common.item.component.WandDataComponent;
import com.hakimen.wandrous.common.registers.ContainerRegister;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;

import java.util.Optional;

public class WandTinkerMenu extends AbstractContainerMenu {

    ItemStack wand;
    IItemHandler playerInventory;

    public ItemStack getWand() {
        return wand;
    }

    public WandTinkerMenu(int pContainerId, Inventory pInventory, Player pPlayer, ItemStack stack) {
        super(ContainerRegister.WAND_TINKER_MENU.get(), pContainerId);

        this.playerInventory = new InvWrapper(pInventory);

        this.wand = stack.getItem() instanceof WandItem ? stack : pPlayer.getOffhandItem();
        Optional<ItemStackHandler> caps = Optional.ofNullable((ItemStackHandler) wand.getCapability(Capabilities.ItemHandler.ITEM));


        WandDataComponent.WandStat stat = stack.get(DataComponentsRegister.WAND_COMPONENT.get());

        caps.ifPresent(itemStackHandler -> {
            if(!pPlayer.level().isClientSide){
                itemStackHandler.deserializeNBT(pPlayer.level().registryAccess(), stat.getInventory());
            }
        });

        caps.ifPresent(handler -> {
            for (int i = 0; i < handler.getSlots(); i++) {
                addSlot(new SlotItemHandler(handler, i, 8 + (18 * (i % 9)), 18 + 18 * (i / 9)) {
                    @Override
                    public void setChanged() {
                        ItemStack stack = wand;
                        WandDataComponent.WandStat stat = stack.get(DataComponentsRegister.WAND_COMPONENT.get());
                        WandDataComponent.WandStatBuilder builder = new WandDataComponent.WandStatBuilder(stat);

                        if(!pPlayer.isLocalPlayer() && !pPlayer.level().isClientSide){
                            builder.setInventory(handler.serializeNBT(pPlayer.registryAccess()));
                            stack.set(DataComponentsRegister.WAND_COMPONENT.get(), builder.build());
                        }

                    }
                });
            }
        });

        layoutPlayerInventorySlots(8, 148, caps, pPlayer);
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx, Optional<ItemStackHandler> caps, Player pPlayer) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y) {
                @Override
                public boolean mayPickup(Player playerIn) {
                    return !handler.getStackInSlot(this.getSlotIndex()).equals(playerIn.getItemInHand(playerIn.getUsedItemHand())) && super.mayPickup(playerIn);
                }

                @Override
                public void setChanged() {
                    caps.ifPresent(itemStackHandler -> {
                        ItemStack stack = wand;
                        WandDataComponent.WandStat stat = stack.get(DataComponentsRegister.WAND_COMPONENT.get());
                        WandDataComponent.WandStatBuilder builder = new WandDataComponent.WandStatBuilder(stat);

                        if(!pPlayer.level().isClientSide){
                            builder.setInventory(itemStackHandler.serializeNBT(pPlayer.level().registryAccess()));
                            stack.set(DataComponentsRegister.WAND_COMPONENT.get(), builder.build());
                            super.setChanged();
                        }
                    });
                }
            });
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy, Optional<ItemStackHandler> caps, Player player) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx, caps, player);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow, Optional<ItemStackHandler> caps, Player player) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18, caps, player);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18, caps, player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        WandDataComponent.WandStat stat = wand.get(DataComponentsRegister.WAND_COMPONENT.get());
        int capacity = stat.getCapacity();
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < capacity) {
                if (!this.moveItemStackTo(itemstack1, capacity, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, capacity, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            }
        }
        return itemstack;
    }


    @Override
    public boolean stillValid(Player player) {
        return player.isAlive();
    }

}
