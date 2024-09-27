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
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }
        ));

        add("wand_in_dungeon_study_quarters_chest", new WandLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "chests/dungeon_study_quarters")).build(),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }
        ));

        add("bestow_spell_in_village_temple_chest", new AppendBestowSpellsModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_temple")).build(),
                },
                4
        ));

        add("bestow_spell_in_desert_temple_chest", new AppendBestowSpellsModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/desert_pyramid")).build(),
                },
                4
        ));

        add("bestow_spell_in_bookcase", new AppendBestowSpellsModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "chests/bookcase")).build(),
                },
                6
        ));

        add("bestow_spell_in_dungeon_study_quarters", new AppendBestowSpellsModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "chests/dungeon_study_quarters")).build(),
                },
                4
        ));


        add("add_sonic_boom", new AddItemsModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/ancient_city")).build()
                },
                Map.of(
                        ItemRegister.SONIC_BOOM_SPELL.getId().toString(), 0.25f,
                        ItemRegister.TRIGGER_SONIC_BOOM_SPELL.getId().toString(), 0.20f,
                        ItemRegister.TIMER_SONIC_BOOM_SPELL.getId().toString(), 0.15f
                )
        ));

        add("add_divide_by_in_wither_kill", new AddItemsModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft", "entities/wither")).build()
                },
                Map.of(
                        ItemRegister.DIVIDE_BY_2_SPELL.getId().toString(), 0.25f,
                        ItemRegister.DIVIDE_BY_3_SPELL.getId().toString(), 0.25f,
                        ItemRegister.DIVIDE_BY_4_SPELL.getId().toString(), 0.25f
                )
        ));

        add("add_greek_letters_in_warden_kill", new AddItemsModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft", "entities/warden")).build()
                },
                Map.of(
                        ItemRegister.GREEK_LETTER_KAPPA_SPELL.getId().toString(), 0.25f,
                        ItemRegister.GREEK_LETTER_DELTA_SPELL.getId().toString(), 0.25f,
                        ItemRegister.GREEK_LETTER_LAMBDA_SPELL.getId().toString(), 0.25f
                )
        ));

        add("wand_in_ancient_city", new WandLootModifier(
                new LootItemCondition[]{
                        new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "chests/ancient_city")).build(),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }
        ));
    }
}
