package com.hakimen.wandrous.common.datagen;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.loot.AddItemsModifier;
import com.hakimen.wandrous.common.loot.AppendBestowSpellsModifier;
import com.hakimen.wandrous.common.loot.WandLootModifier;
import com.hakimen.wandrous.common.registers.ItemRegister;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class GlobalLootModProvider extends GlobalLootModifierProvider {
    public GlobalLootModProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Wandrous.MODID);
    }

    @Override
    protected void start() {
        add("wand_in_dungeon_cell_chest", new WandLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "chests/dungeon_cell")).build(),
                        LootItemRandomChanceCondition.randomChance(0.75f).build()
                }
        ));

        add("wand_in_dungeon_study_quarters_chest", new WandLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "chests/dungeon_study_quarters")).build(),
                        LootItemRandomChanceCondition.randomChance(0.75f).build()
                }
        ));

        add("bestow_spell_in_village_temple_chest", new AppendBestowSpellsModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/village/village_temple")).build(),
                },
                4
        ));

        add("bestow_spell_in_desert_temple_chest", new AppendBestowSpellsModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/desert_pyramid")).build(),
                },
                4
        ));

        add("add_sonic_boom", new AddItemsModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","chests/ancient_city")).build()
                },
                Map.of(
                        ItemRegister.SONIC_BOOM_SPELL.getId().toString(), 0.25f,
                        ItemRegister.TRIGGER_SONIC_BOOM_SPELL.getId().toString(), 0.20f,
                        ItemRegister.TIMER_SONIC_BOOM_SPELL.getId().toString(), 0.15f
                )
        ));

    }
}
