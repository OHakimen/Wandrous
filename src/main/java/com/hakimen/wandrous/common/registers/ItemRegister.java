package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.item.SpellEffectItem;
import com.hakimen.wandrous.common.item.WandItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Wandrous.MODID);

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Wandrous.MODID);

    public static final DeferredHolder<Item, WandItem> WAND = ITEMS.register("wand", WandItem::new);

    public static final DeferredHolder<Item, SpellEffectItem> DOUBLE_CAST_SPELL = ITEMS.register("double_cast", () -> new SpellEffectItem(SpellRegister.DOUBLE_CAST));
    public static final DeferredHolder<Item, SpellEffectItem> TRIPLE_CAST_SPELL = ITEMS.register("triple_cast", () -> new SpellEffectItem(SpellRegister.TRIPLE_CAST));

    public static final DeferredHolder<Item, SpellEffectItem> HOMING_SPELL = ITEMS.register("homing", () -> new SpellEffectItem(SpellRegister.HOMING));
    public static final DeferredHolder<Item, SpellEffectItem> BOOMERANG_SPELL = ITEMS.register("boomerang", () -> new SpellEffectItem(SpellRegister.BOOMERANG));

    public static final DeferredHolder<Item, SpellEffectItem> TELEPORT_CAST_SPELL = ITEMS.register("teleport_cast", () -> new SpellEffectItem(SpellRegister.TELEPORT_CAST));
    public static final DeferredHolder<Item, SpellEffectItem> LONG_DISTANCE_CAST_SPELL = ITEMS.register("long_distance_cast", () -> new SpellEffectItem(SpellRegister.LONG_DISTANCE_CAST));
    public static final DeferredHolder<Item, SpellEffectItem> LINE_CAST_SPELL = ITEMS.register("line_cast", () -> new SpellEffectItem(SpellRegister.LINE_CAST));

    public static final DeferredHolder<Item, SpellEffectItem> DOUBLE_SPLIT_SPELL = ITEMS.register("double_split_cast", () -> new SpellEffectItem(SpellRegister.DOUBLE_SPLIT));
    public static final DeferredHolder<Item, SpellEffectItem> TRIPLE_SPLIT_SPELL = ITEMS.register("triple_split_cast", () -> new SpellEffectItem(SpellRegister.TRIPLE_SPLIT));
    public static final DeferredHolder<Item, SpellEffectItem> QUAD_SPLIT_SPELL = ITEMS.register("quad_split_cast", () -> new SpellEffectItem(SpellRegister.QUAD_SPLIT));

    public static final DeferredHolder<Item, SpellEffectItem> DIVIDE_BY_2_SPELL = ITEMS.register("divide_by_2", () -> new SpellEffectItem(SpellRegister.DIVIDE_BY_2));
    public static final DeferredHolder<Item, SpellEffectItem> DIVIDE_BY_3_SPELL = ITEMS.register("divide_by_3", () -> new SpellEffectItem(SpellRegister.DIVIDE_BY_3));
    public static final DeferredHolder<Item, SpellEffectItem> DIVIDE_BY_4_SPELL = ITEMS.register("divide_by_4", () -> new SpellEffectItem(SpellRegister.DIVIDE_BY_4));

    public static final DeferredHolder<Item, SpellEffectItem> SPREAD_CAST_SPELL = ITEMS.register("spread_cast", () -> new SpellEffectItem(SpellRegister.SPREAD_CAST));

    public static final DeferredHolder<Item, SpellEffectItem> FREEZING_CHARGE_SPELL = ITEMS.register("freezing_charge", () -> new SpellEffectItem(SpellRegister.FREEZING_CHARGE));
    public static final DeferredHolder<Item, SpellEffectItem> IGNEOUS_CHARGE_SPELL = ITEMS.register("igneous_charge", () -> new SpellEffectItem(SpellRegister.IGNEOUS_CHARGE));
    public static final DeferredHolder<Item, SpellEffectItem> POISON_CHARGE_SPELL = ITEMS.register("poison_charge", () -> new SpellEffectItem(SpellRegister.POISON_CHARGE));
    public static final DeferredHolder<Item, SpellEffectItem> CRUMBLING_CHARGE_SPELL = ITEMS.register("crumbling_charge", () -> new SpellEffectItem(SpellRegister.CRUMBLING_CHARGE));

    public static final DeferredHolder<Item, SpellEffectItem> HEXAGON_CAST_SPELL = ITEMS.register("hexagon_cast", () -> new SpellEffectItem(SpellRegister.HEXAGON_CAST));

    public static final DeferredHolder<Item, SpellEffectItem> SMALL_DELAY_CAST_SPELL = ITEMS.register("small_delay_cast", () -> new SpellEffectItem(SpellRegister.SMALL_DELAY_CAST));
    public static final DeferredHolder<Item, SpellEffectItem> MEDIUM_DELAY_CAST_SPELL = ITEMS.register("medium_delay_cast", () -> new SpellEffectItem(SpellRegister.MEDIUM_DELAY_CAST));
    public static final DeferredHolder<Item, SpellEffectItem> BIG_DELAY_CAST_SPELL = ITEMS.register("big_delay_cast", () -> new SpellEffectItem(SpellRegister.BIG_DELAY_CAST));

    public static final DeferredHolder<Item, SpellEffectItem> ACCELERATE_SPELL = ITEMS.register("accelerate_cast", () -> new SpellEffectItem(SpellRegister.ACCELERATE_CAST));
    public static final DeferredHolder<Item, SpellEffectItem> ADD_MANA_SPELL = ITEMS.register("add_mana", () -> new SpellEffectItem(SpellRegister.ADD_MANA));
    public static final DeferredHolder<Item, SpellEffectItem> CRITICAL_PLUS_SPELL = ITEMS.register("critical_plus", () -> new SpellEffectItem(SpellRegister.CRITICAL_PLUS));
    public static final DeferredHolder<Item, SpellEffectItem> INCREASE_LIFETIME_SPELL = ITEMS.register("increase_lifetime", () -> new SpellEffectItem(SpellRegister.INCREASE_LIFETIME));
    public static final DeferredHolder<Item, SpellEffectItem> DECREASE_LIFETIME_SPELL = ITEMS.register("decrease_lifetime", () -> new SpellEffectItem(SpellRegister.DECREASE_LIFETIME));
    public static final DeferredHolder<Item, SpellEffectItem> INCREASE_RANGE_SPELL = ITEMS.register("increase_range", () -> new SpellEffectItem(SpellRegister.INCREASE_RANGE));
    public static final DeferredHolder<Item, SpellEffectItem> DECREASE_RANGE_SPELL = ITEMS.register("decrease_range", () -> new SpellEffectItem(SpellRegister.DECREASE_RANGE));
    public static final DeferredHolder<Item, SpellEffectItem> INCREASE_SPREAD_SPELL = ITEMS.register("increase_spread", () -> new SpellEffectItem(SpellRegister.INCREASE_SPREAD));
    public static final DeferredHolder<Item, SpellEffectItem> DECREASE_SPREAD_SPELL = ITEMS.register("decrease_spread", () -> new SpellEffectItem(SpellRegister.DECREASE_SPREAD));

    public static final DeferredHolder<Item, SpellEffectItem> TELEPORT_SPELL = ITEMS.register("teleport", () -> new SpellEffectItem(SpellRegister.TELEPORT));
    public static final DeferredHolder<Item, SpellEffectItem> SWAP_TELEPORT_SPELL = ITEMS.register("swap_teleport", () -> new SpellEffectItem(SpellRegister.SWAP_TELEPORT));
    public static final DeferredHolder<Item, SpellEffectItem> HOME_BRINGER_TELEPORT_SPELL = ITEMS.register("home_bringer_teleport", () -> new SpellEffectItem(SpellRegister.HOME_BRINGER_TELEPORT));

    public static final DeferredHolder<Item, SpellEffectItem> COLLECT_SPELL = ITEMS.register("collect", () -> new SpellEffectItem(SpellRegister.COLLECT));

    public static final DeferredHolder<Item, SpellEffectItem> DRILL_SPELL = ITEMS.register("drill", () -> new SpellEffectItem(SpellRegister.DRILL));
    public static final DeferredHolder<Item, SpellEffectItem> GIGA_DRILL_SPELL = ITEMS.register("giga_drill", () -> new SpellEffectItem(SpellRegister.GIGA_DRILL));

    public static final DeferredHolder<Item, SpellEffectItem> CHAINSAW_SPELL = ITEMS.register("chainsaw", () -> new SpellEffectItem(SpellRegister.CHAINSAW));

    public static final DeferredHolder<Item, SpellEffectItem> LIGHTING_BOLT_SPELL = ITEMS.register("lighting_bolt", () -> new SpellEffectItem(SpellRegister.LIGHTNING_BOLT));

    public static final DeferredHolder<Item, SpellEffectItem> GLIMMERING_BOLT_SPELL = ITEMS.register("glimmering_bolt", () -> new SpellEffectItem(SpellRegister.GLIMMERING_BOLT));
    public static final DeferredHolder<Item, SpellEffectItem> TRIGGER_GLIMMERING_BOLT_SPELL = ITEMS.register("trigger_glimmering_bolt", () -> new SpellEffectItem(SpellRegister.TRIGGER_GLIMMERING_BOLT));
    public static final DeferredHolder<Item, SpellEffectItem> TIMER_GLIMMERING_BOLT_SPELL = ITEMS.register("timer_glimmering_bolt", () -> new SpellEffectItem(SpellRegister.TIMER_GLIMMERING_BOLT));

    public static final DeferredHolder<Item, SpellEffectItem> FIREBALL_SPELL = ITEMS.register("fireball", () -> new SpellEffectItem(SpellRegister.FIREBALL));
    public static final DeferredHolder<Item, SpellEffectItem> TRIGGER_FIREBALL_SPELL = ITEMS.register("trigger_fireball", () -> new SpellEffectItem(SpellRegister.TRIGGER_FIREBALL));
    public static final DeferredHolder<Item, SpellEffectItem> TIMER_FIREBALL_SPELL = ITEMS.register("timer_fireball", () -> new SpellEffectItem(SpellRegister.TIMER_FIREBALL));

    public static final DeferredHolder<Item, SpellEffectItem> SONIC_BOOM_SPELL = ITEMS.register("sonic_boom", () -> new SpellEffectItem(SpellRegister.SONIC_BOOM));
    public static final DeferredHolder<Item, SpellEffectItem> TRIGGER_SONIC_BOOM_SPELL = ITEMS.register("trigger_sonic_boom", () -> new SpellEffectItem(SpellRegister.TRIGGER_SONIC_BOOM));
    public static final DeferredHolder<Item, SpellEffectItem> TIMER_SONIC_BOOM_SPELL = ITEMS.register("timer_sonic_boom", () -> new SpellEffectItem(SpellRegister.TIMER_SONIC_BOOM));

    public static final DeferredHolder<Item, SpellEffectItem> BLACK_HOLE_SPELL = ITEMS.register("black_hole", () -> new SpellEffectItem(SpellRegister.BLACK_HOLE));
    public static final DeferredHolder<Item, SpellEffectItem> TIMER_BLACK_HOLE_SPELL = ITEMS.register("timer_black_hole", () -> new SpellEffectItem(SpellRegister.TIMER_BLACK_HOLE));

    public static final DeferredHolder<Item, SpellEffectItem> CHAIN_SHOT_SPELL = ITEMS.register("chain_shot", () -> new SpellEffectItem(SpellRegister.CHAIN_SHOT));
    public static final DeferredHolder<Item, SpellEffectItem> TRIGGER_CHAIN_SHOT_SPELL = ITEMS.register("trigger_chain_shot", () -> new SpellEffectItem(SpellRegister.TRIGGER_CHAIN_SHOT));
    public static final DeferredHolder<Item, SpellEffectItem> TIMER_CHAIN_SHOT_SPELL = ITEMS.register("timer_chain_shot", () -> new SpellEffectItem(SpellRegister.TIMER_CHAIN_SHOT));


    public static final DeferredHolder<Item, SpellEffectItem> BOMB_SPELL = ITEMS.register("bomb", () -> new SpellEffectItem(SpellRegister.BOMB));

    public static final DeferredHolder<Item, SpellEffectItem> EXPLOSION_SPELL = ITEMS.register("explosion", () -> new SpellEffectItem(SpellRegister.EXPLOSION));
    public static final DeferredHolder<Item, SpellEffectItem> MAJOR_EXPLOSION_SPELL = ITEMS.register("major_explosion", () -> new SpellEffectItem(SpellRegister.MAJOR_EXPLOSION));
    public static final DeferredHolder<Item, SpellEffectItem> NUKE_SPELL = ITEMS.register("nuke", () -> new SpellEffectItem(SpellRegister.NUKE));

    public static final DeferredHolder<Item, SpellEffectItem> CONJURE_LIGHT_SPELL = ITEMS.register("conjure_light", () -> new SpellEffectItem(SpellRegister.CONJURE_LIGHT));
    public static final DeferredHolder<Item, SpellEffectItem> CONJURE_BLOCK_SPELL = ITEMS.register("conjure_block", () -> new SpellEffectItem(SpellRegister.CONJURE_BLOCK));
    public static final DeferredHolder<Item, SpellEffectItem> CONJURE_WEBS_SPELL = ITEMS.register("conjure_webs", () -> new SpellEffectItem(SpellRegister.CONJURE_WEBS));

    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_CURSE_HUNGER_SPELL = ITEMS.register("bestow_curse_hunger", () -> new SpellEffectItem(SpellRegister.BESTOW_CURSE_HUNGER));
    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_CURSE_DARKNESS_SPELL = ITEMS.register("bestow_curse_darkness", () -> new SpellEffectItem(SpellRegister.BESTOW_CURSE_DARKNESS));
    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_CURSE_WEAKNESS_SPELL = ITEMS.register("bestow_curse_weakness", () -> new SpellEffectItem(SpellRegister.BESTOW_CURSE_WEAKNESS));
    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_CURSE_NAUSEA_SPELL = ITEMS.register("bestow_curse_nausea", () -> new SpellEffectItem(SpellRegister.BESTOW_CURSE_NAUSEA));
    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_CURSE_GLOW_SPELL = ITEMS.register("bestow_curse_glow", () -> new SpellEffectItem(SpellRegister.BESTOW_CURSE_GLOW));
    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_CURSE_LEVITATE_SPELL = ITEMS.register("bestow_curse_levitate", () -> new SpellEffectItem(SpellRegister.BESTOW_CURSE_LEVITATE));
    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_CURSE_SLOWNESS_SPELL = ITEMS.register("bestow_curse_slowness", () -> new SpellEffectItem(SpellRegister.BESTOW_CURSE_SLOWNESS));
    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_CURSE_MINING_FATIGUE_SPELL = ITEMS.register("bestow_curse_mining_fatigue", () -> new SpellEffectItem(SpellRegister.BESTOW_CURSE_MINING_FATIGUE));

    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_BLESSING_HASTE_SPELL = ITEMS.register("bestow_blessing_haste", () -> new SpellEffectItem(SpellRegister.BESTOW_BLESSING_HASTE));
    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_BLESSING_REGENERATION_SPELL = ITEMS.register("bestow_blessing_regeneration", () -> new SpellEffectItem(SpellRegister.BESTOW_BLESSING_REGENERATION));
    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_BLESSING_SATURATION_SPELL = ITEMS.register("bestow_blessing_saturation", () -> new SpellEffectItem(SpellRegister.BESTOW_BLESSING_SATURATION));
    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_BLESSING_BOOST_HEALTH_SPELL = ITEMS.register("bestow_blessing_boost_health", () -> new SpellEffectItem(SpellRegister.BESTOW_BLESSING_BOOST_HEALTH));
    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_BLESSING_RESISTANCE_SPELL = ITEMS.register("bestow_blessing_resistance", () -> new SpellEffectItem(SpellRegister.BESTOW_BLESSING_RESISTANCE));
    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_BLESSING_NIGHT_VISION_SPELL = ITEMS.register("bestow_blessing_night_vision", () -> new SpellEffectItem(SpellRegister.BESTOW_BLESSING_NIGHT_VISION));
    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_BLESSING_RESIST_FIRE_SPELL = ITEMS.register("bestow_blessing_resist_fire", () -> new SpellEffectItem(SpellRegister.BESTOW_BLESSING_RESIST_FIRE));
    public static final DeferredHolder<Item, SpellEffectItem> BESTOW_BLESSING_SPEED_SPELL = ITEMS.register("bestow_blessing_speed", () -> new SpellEffectItem(SpellRegister.BESTOW_BLESSING_SPEED));

    private static final DeferredHolder<Item, BlockItem> CHERT = ITEMS.register("chert", () -> new BlockItem(BlockRegister.CHERT.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> CHERT_STAIRS = ITEMS.register("chert_stairs", () -> new BlockItem(BlockRegister.CHERT_STAIRS.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> CHERT_SLAB = ITEMS.register("chert_slab", () -> new BlockItem(BlockRegister.CHERT_SLAB.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> CHERT_WALL = ITEMS.register("chert_wall", () -> new BlockItem(BlockRegister.CHERT_WALL.get(), new Item.Properties()));

    private static final DeferredHolder<Item, BlockItem> POLISHED_CHERT = ITEMS.register("polished_chert", () -> new BlockItem(BlockRegister.POLISHED_CHERT.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> POLISHED_CHERT_STAIRS = ITEMS.register("polished_chert_stairs", () -> new BlockItem(BlockRegister.POLISHED_CHERT_STAIRS.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> POLISHED_CHERT_SLAB = ITEMS.register("polished_chert_slab", () -> new BlockItem(BlockRegister.POLISHED_CHERT_SLAB.get(), new Item.Properties()));

    private static final DeferredHolder<Item, BlockItem> CHERT_BRICKS = ITEMS.register("chert_bricks", () -> new BlockItem(BlockRegister.CHERT_BRICKS.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> CHERT_BRICKS_STAIRS = ITEMS.register("chert_bricks_stairs", () -> new BlockItem(BlockRegister.CHERT_BRICKS_STAIRS.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> CHERT_BRICKS_SLAB = ITEMS.register("chert_bricks_slab", () -> new BlockItem(BlockRegister.CHERT_BRICKS_SLAB.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> CHERT_BRICKS_WALL = ITEMS.register("chert_bricks_wall", () -> new BlockItem(BlockRegister.CHERT_BRICKS_WALL.get(), new Item.Properties()));

    private static final DeferredHolder<Item, BlockItem> CRACKED_CHERT_BRICKS = ITEMS.register("cracked_chert_bricks", () -> new BlockItem(BlockRegister.CRACKED_CHERT_BRICKS.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> CHISELED_CHERT_BRICKS = ITEMS.register("chiseled_chert_bricks", () -> new BlockItem(BlockRegister.CHISELED_CHERT_BRICKS.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> CHERT_PILLAR = ITEMS.register("chert_pillar", () -> new BlockItem(BlockRegister.CHERT_PILLAR.get(), new Item.Properties()));

    private static final DeferredHolder<Item, BlockItem> MOSSY_CHERT_BRICKS = ITEMS.register("mossy_chert_bricks", () -> new BlockItem(BlockRegister.MOSSY_CHERT_BRICKS.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> MOSSY_CHERT_BRICKS_STAIRS = ITEMS.register("mossy_chert_bricks_stairs", () -> new BlockItem(BlockRegister.MOSSY_CHERT_BRICKS_STAIRS.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> MOSSY_CHERT_BRICKS_SLAB = ITEMS.register("mossy_chert_bricks_slab", () -> new BlockItem(BlockRegister.MOSSY_CHERT_BRICKS_SLAB.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> MOSSY_CHERT_BRICKS_WALL = ITEMS.register("mossy_chert_bricks_wall", () -> new BlockItem(BlockRegister.MOSSY_CHERT_BRICKS_WALL.get(), new Item.Properties()));

    private static final DeferredHolder<Item, BlockItem> CHERT_TILES = ITEMS.register("chert_tiles", () -> new BlockItem(BlockRegister.CHERT_TILES.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> CHERT_TILES_STAIRS = ITEMS.register("chert_tiles_stairs", () -> new BlockItem(BlockRegister.CHERT_TILES_STAIRS.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> CHERT_TILES_SLAB = ITEMS.register("chert_tiles_slab", () -> new BlockItem(BlockRegister.CHERT_TILES_SLAB.get(), new Item.Properties()));

    private static final DeferredHolder<Item, BlockItem> MOSSY_CHERT_TILES = ITEMS.register("mossy_chert_tiles", () -> new BlockItem(BlockRegister.MOSSY_CHERT_TILES.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> MOSSY_CHERT_TILES_STAIRS = ITEMS.register("mossy_chert_tiles_stairs", () -> new BlockItem(BlockRegister.MOSSY_CHERT_TILES_STAIRS.get(), new Item.Properties()));
    private static final DeferredHolder<Item, BlockItem> MOSSY_CHERT_TILES_SLAB = ITEMS.register("mossy_chert_tiles_slab", () -> new BlockItem(BlockRegister.MOSSY_CHERT_TILES_SLAB.get(), new Item.Properties()));

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = ItemRegister.TABS.register("wandrous", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(WAND.get()))
            .title(Component.translatable("itemGroup.wandrous"))
            .displayItems((flags, out) -> {
                ItemRegister.ITEMS.getEntries().forEach(x -> out.accept(x.get()));
            }).build());

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
        TABS.register(bus);
    }
}
