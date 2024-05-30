package com.hakimen.wandrous.common.datagen.loots;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.registers.ItemRegister;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class ChestLootProvider implements LootTableSubProvider {

    ResourceLocation DUNGEON = new ResourceLocation(Wandrous.MODID, "chests/dungeon_cell");
    ResourceLocation STUDY_QUARTERS = new ResourceLocation(Wandrous.MODID, "chests/dungeon_study_quarters");

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> registry) {
        registry.accept(DUNGEON, LootTable
                .lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(new UniformGenerator(ConstantValue.exactly(6), ConstantValue.exactly(16)))
                                .add(LootItem.lootTableItem(ItemRegister.WAND.get()).setWeight(2))
                                .add(LootItem.lootTableItem(ItemRegister.GLIMMERING_BOLT_SPELL.get()).setWeight(8))
                                .add(LootItem.lootTableItem(ItemRegister.TIMER_GLIMMERING_BOLT_SPELL.get()).setWeight(2))
                                .add(LootItem.lootTableItem(ItemRegister.TRIGGER_GLIMMERING_BOLT_SPELL.get()).setWeight(4))
                                .add(LootItem.lootTableItem(ItemRegister.CHAIN_SHOT_SPELL.get()).setWeight(2))
                                .add(LootItem.lootTableItem(ItemRegister.TIMER_CHAIN_SHOT_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.TRIGGER_CHAIN_SHOT_SPELL.get()).setWeight(2))
                                .add(LootItem.lootTableItem(ItemRegister.BOMB_SPELL.get()).setWeight(4))
                                .add(LootItem.lootTableItem(ItemRegister.ACCELERATE_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.DECREASE_SPREAD_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.HOMING_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.BESTOW_CURSE_GLOW_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.BESTOW_BLESSING_NIGHT_VISION_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.COLLECT_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.DRILL_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.TELEPORT_CAST_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.LONG_DISTANCE_CAST_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.POISON_CHARGE_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.FREEZING_CHARGE_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.IGNEOUS_CHARGE_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.SWAP_TELEPORT_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.TELEPORT_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.CONJURE_BLOCK_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.CONJURE_LIGHT_SPELL.get()).setWeight(1))
                ));


        registry.accept(STUDY_QUARTERS, LootTable
                .lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(new UniformGenerator(ConstantValue.exactly(6), ConstantValue.exactly(16)))
                                .add(LootItem.lootTableItem(ItemRegister.WAND.get()).setWeight(2))
                                .add(LootItem.lootTableItem(ItemRegister.FIREBALL_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.TRIGGER_FIREBALL_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.TIMER_FIREBALL_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.BLACK_HOLE_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.TIMER_BLACK_HOLE_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.HEXAGON_CAST_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.LINE_CAST_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.CONJURE_WEBS_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.ADD_MANA_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.SMALL_DELAY_CAST_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.MEDIUM_DELAY_CAST_SPELL.get()).setWeight(1))
                                .add(LootItem.lootTableItem(ItemRegister.BIG_DELAY_CAST_SPELL.get()).setWeight(1))
                ));
    }
}
