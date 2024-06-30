package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.loot.AddItemsModifier;
import com.hakimen.wandrous.common.loot.AppendBestowSpellsModifier;
import com.hakimen.wandrous.common.loot.WandLootModifier;
import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class LootModRegistry {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Wandrous.MODID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<? extends IGlobalLootModifier>> GENERATE_WAND =
            LOOT_MODIFIER_SERIALIZERS.register("generate_wand", () -> MapCodec.assumeMapUnsafe(WandLootModifier.CODEC.get()));

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<? extends IGlobalLootModifier>> GENERATE_BESTOW_SPELL =
            LOOT_MODIFIER_SERIALIZERS.register("bestow_spell", () -> MapCodec.assumeMapUnsafe(AppendBestowSpellsModifier.CODEC.get()));

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<? extends IGlobalLootModifier>> ADD_ITEMS =
            LOOT_MODIFIER_SERIALIZERS.register("add_items", () -> MapCodec.assumeMapUnsafe(AddItemsModifier.CODEC.get()));



    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
