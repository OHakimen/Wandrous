package com.hakimen.wandrous.common.events;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.item.SpellEffectItem;
import com.hakimen.wandrous.common.item.component.WandDataComponent;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import com.hakimen.wandrous.common.registers.ItemRegister;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.ItemStackHandler;

@EventBusSubscriber(modid = Wandrous.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegisterCapsEvent {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(
                Capabilities.ItemHandler.ITEM,
                    (itemStack, voided) -> {
                        WandDataComponent.WandStat stat = itemStack.get(DataComponentsRegister.WAND_COMPONENT.get());
                        ItemStackHandler handler = new ItemStackHandler(stat.getCapacity()){
                            @Override
                            public boolean isItemValid(int slot, ItemStack stack) {
                                return stack.getItem() instanceof SpellEffectItem;
                            }

                            @Override
                            public int getSlotLimit(int slot) {
                                return 1;
                            }
                        };
                        return handler;
                    },
                ItemRegister.WAND.get()
        );
    }
}
