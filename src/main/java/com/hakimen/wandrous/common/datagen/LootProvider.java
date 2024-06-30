package com.hakimen.wandrous.common.datagen;

import com.hakimen.wandrous.common.datagen.loots.ChestLootProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class LootProvider {
    public static LootTableProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> holder) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(provider -> new ChestLootProvider(), LootContextParamSets.CHEST)
        ), holder);
    }

}
