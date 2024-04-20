package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.client.menus.WandTinkerMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ContainerRegister {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Wandrous.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<WandTinkerMenu>> WAND_TINKER_MENU = MENUS.register("wand_tinkering", () -> IMenuTypeExtension.create((i, inventory, friendlyByteBuf) -> new WandTinkerMenu(i, inventory, inventory.player)));

    public static void register(IEventBus bus){
        MENUS.register(bus);
    }
}
