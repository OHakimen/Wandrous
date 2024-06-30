package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.item.component.WandDataComponent;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentsRegister {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Wandrous.MODID);

    public static final DeferredHolder<DataComponentType<?>,DataComponentType<WandDataComponent.WandStat>> WAND_COMPONENT = DATA_COMPONENTS.registerComponentType("wand_status", objectBuilder ->
            objectBuilder.persistent(WandDataComponent.CODEC).networkSynchronized(WandDataComponent.STREAM_CODEC));

    public static void register(IEventBus bus){
        DATA_COMPONENTS.register(bus);
    }
}
