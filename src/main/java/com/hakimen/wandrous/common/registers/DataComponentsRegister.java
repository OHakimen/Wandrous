package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.item.component.InscribedLensDataComponent;
import com.hakimen.wandrous.common.item.component.ScrollDataComponent;
import com.hakimen.wandrous.common.item.component.SpellCastsDataComponent;
import com.hakimen.wandrous.common.item.component.WandDataComponent;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentsRegister {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Wandrous.MODID);

    public static final DeferredHolder<DataComponentType<?>,DataComponentType<WandDataComponent.WandStat>> WAND_COMPONENT = DATA_COMPONENTS.registerComponentType("wand_status", objectBuilder ->
            objectBuilder.persistent(WandDataComponent.CODEC).networkSynchronized(WandDataComponent.STREAM_CODEC));

    public static final DeferredHolder<DataComponentType<?>,DataComponentType<ScrollDataComponent.ScrollData>> SCROLL_COMPONENT = DATA_COMPONENTS.registerComponentType("scroll_data", objectBuilder ->
            objectBuilder.persistent(ScrollDataComponent.CODEC).networkSynchronized(ScrollDataComponent.STREAM_CODEC));

    public static final DeferredHolder<DataComponentType<?>,DataComponentType<SpellCastsDataComponent.SpellCastsData>> CHARGES_COMPONENT = DATA_COMPONENTS.registerComponentType("charges", objectBuilder ->
            objectBuilder.persistent(SpellCastsDataComponent.CODEC).networkSynchronized(SpellCastsDataComponent.STREAM_CODEC));

    public static final DeferredHolder<DataComponentType<?>,DataComponentType<InscribedLensDataComponent.GlyphData>> GLYPH_COMPONENT = DATA_COMPONENTS.registerComponentType("glyph", objectBuilder ->
            objectBuilder.persistent(InscribedLensDataComponent.CODEC).networkSynchronized(InscribedLensDataComponent.STREAM_CODEC));

    public static void register(IEventBus bus){
        DATA_COMPONENTS.register(bus);
    }
}
