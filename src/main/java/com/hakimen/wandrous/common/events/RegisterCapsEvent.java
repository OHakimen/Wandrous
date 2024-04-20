package com.hakimen.wandrous.common.events;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.item.SpellEffectItem;
import com.hakimen.wandrous.common.item.WandItem;
import com.hakimen.wandrous.common.registers.ItemRegister;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.ItemStackHandler;

@Mod.EventBusSubscriber(modid = Wandrous.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterCapsEvent {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(
                Capabilities.ItemHandler.ITEM,
                    (itemStack, voided) -> {
                        ItemStackHandler handler = new ItemStackHandler(itemStack.getOrCreateTag().getInt(WandItem.CAPACITY)){
                            @Override
                            protected void onContentsChanged(int slot) {
                                itemStack.getOrCreateTag().put("Inventory", this.serializeNBT());
                                super.onContentsChanged(slot);
                            }

                            @Override
                            public boolean isItemValid(int slot, ItemStack stack) {
                                return stack.getItem() instanceof SpellEffectItem;
                            }

                            @Override
                            public int getSlotLimit(int slot) {
                                return 1;
                            }
                        };

                        handler.deserializeNBT(itemStack.getOrCreateTag().getCompound("Inventory"));

                        return handler;
                    },
                ItemRegister.WAND.get()
        );
    }
}
