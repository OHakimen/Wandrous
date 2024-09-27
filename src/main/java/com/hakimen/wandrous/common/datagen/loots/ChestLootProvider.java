package com.hakimen.wandrous.common.datagen.loots;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.registers.ItemRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class ChestLootProvider implements LootTableSubProvider {

    ResourceKey<LootTable> DUNGEON = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "chests/dungeon_cell"));
    ResourceKey<LootTable> STUDY_QUARTERS = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "chests/dungeon_study_quarters"));
    ResourceKey<LootTable> BOOKCASE = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "chests/bookcase"));

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> registry) {
        registry.accept(DUNGEON, dungeonCell());
        registry.accept(STUDY_QUARTERS, studyQuarters());
        registry.accept(BOOKCASE, bookCase());
    }

    public LootTable.Builder dungeonCell() {
        return LootTable
                .lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(new UniformGenerator(ConstantValue.exactly(16), ConstantValue.exactly(24)))
                                .add(LootItem.lootTableItem(Items.COBWEB).setWeight(8))
                                .add(LootItem.lootTableItem(Items.BONE).setWeight(6)).setBonusRolls(UniformGenerator.between(1, 2))
                                .add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(5)).setBonusRolls(UniformGenerator.between(1, 8))
                                .add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(5)).setBonusRolls(UniformGenerator.between(1, 8))
                                .add(LootItem.lootTableItem(Items.POISONOUS_POTATO).setWeight(4))
                                .add(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(3)).setBonusRolls(UniformGenerator.between(1, 4))
                                .add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(3)).setBonusRolls(UniformGenerator.between(1, 4))
                                .add(LootItem.lootTableItem(Items.BAKED_POTATO).setWeight(2)).setBonusRolls(UniformGenerator.between(1, 8))
                                .add(LootItem.lootTableItem(Items.TORCH).setWeight(2)).setBonusRolls(UniformGenerator.between(1, 12))
                                .add(LootItem.lootTableItem(ItemRegister.TEALESTITE_SHARD.get()).setWeight(1))
                                .add(LootItem.lootTableItem(Items.AMETHYST_SHARD).setWeight(1))
                );
    }

    public LootTable.Builder studyQuarters() {
        return LootTable
                .lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(new UniformGenerator(ConstantValue.exactly(16), ConstantValue.exactly(24)))
                                .add(LootItem.lootTableItem(Items.BOOK).setWeight(4)).setBonusRolls(UniformGenerator.between(1, 4))
                                .add(LootItem.lootTableItem(Items.COPPER_INGOT).setWeight(4)).setBonusRolls(UniformGenerator.between(2, 8))
                                .add(LootItem.lootTableItem(Items.GLASS_BOTTLE).setWeight(2)).setBonusRolls(UniformGenerator.between(1, 4))
                                .add(LootItem.lootTableItem(Items.PAPER).setWeight(2)).setBonusRolls(UniformGenerator.between(1, 3))
                                .add(LootItem.lootTableItem(Items.COBWEB).setWeight(2)).setBonusRolls(UniformGenerator.between(1, 3))
                                .add(LootItem.lootTableItem(Items.FEATHER).setWeight(2)).setBonusRolls(UniformGenerator.between(1, 3))
                                .add(LootItem.lootTableItem(Items.CANDLE).setWeight(2))
                                .add(LootItem.lootTableItem(ItemRegister.TEALESTITE_SHARD.get()).setWeight(1)).setBonusRolls(UniformGenerator.between(4, 6))
                                .add(LootItem.lootTableItem(Items.AMETHYST_SHARD).setWeight(1)).setBonusRolls(UniformGenerator.between(4, 6))
                );
    }

    public LootTable.Builder bookCase() {
        return LootTable
                .lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(new UniformGenerator(ConstantValue.exactly(8), ConstantValue.exactly(16)))
                                .add(LootItem.lootTableItem(Items.COBWEB).setWeight(4)).setBonusRolls(UniformGenerator.between(1, 3))
                                .add(LootItem.lootTableItem(Items.BOOK).setWeight(4)).setBonusRolls(UniformGenerator.between(4, 8))
                                .add(LootItem.lootTableItem(Items.PAPER).setWeight(2)).setBonusRolls(UniformGenerator.between(4, 8))
                                .add(LootItem.lootTableItem(Items.INK_SAC).setWeight(1)).setBonusRolls(UniformGenerator.between(1, 4))
                );
    }

}
