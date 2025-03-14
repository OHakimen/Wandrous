package com.hakimen.wandrous.client.menus;

import com.hakimen.wandrous.common.block_entity.ArcaneDispenserBlockEntity;
import com.hakimen.wandrous.common.item.WandItem;
import com.hakimen.wandrous.common.registers.BlockEntityRegister;
import com.hakimen.wandrous.common.registers.ContainerRegister;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;

public class ArcaneDispenserMenu extends AbstractContainerMenu {

    ItemStack wand;
    IItemHandler playerInventory;

    public ArcaneDispenserMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player, inv.player.level().getBlockEntity(extraData.readBlockPos(), BlockEntityRegister.ARCANE_DISPENSER.get()).get());
    }

    public ArcaneDispenserMenu(int pContainerId, Inventory pInventory, Player pPlayer, ArcaneDispenserBlockEntity entity) {
        super(ContainerRegister.ARCANE_DISPENSER.get(), pContainerId);

        this.playerInventory = new InvWrapper(pInventory);

        ItemStack stack = entity.getItemHandler().getStackInSlot(0);

        this.wand = stack.getItem() instanceof WandItem ? stack : null;

        addSlot(new SlotItemHandler(entity.getItemHandler(), 0, 80,64));  //WandSlot

        layoutPlayerInventorySlots(8, 148, pPlayer);
    }

    public ItemStack getWand() {
        return wand;
    }


    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx, Player pPlayer) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy, Player player) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx, player);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow, Player player) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18, player);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18, player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 1) {
                if (!this.moveItemStackTo(itemstack1, 1, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }


    @Override
    public boolean stillValid(Player player) {
        return player.isAlive();
    }
}
