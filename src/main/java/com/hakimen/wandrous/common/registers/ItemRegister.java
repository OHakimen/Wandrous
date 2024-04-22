package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.item.SpellEffectItem;
import com.hakimen.wandrous.common.item.WandItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
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


    public static final DeferredHolder<Item, SpellEffectItem> TELEPORT_SPELL = ITEMS.register("teleport", () -> new SpellEffectItem(SpellRegister.TELEPORT));
    public static final DeferredHolder<Item, SpellEffectItem> SWAP_TELEPORT_SPELL = ITEMS.register("swap_teleport", () -> new SpellEffectItem(SpellRegister.SWAP_TELEPORT));
    public static final DeferredHolder<Item, SpellEffectItem> HOME_BRINGER_TELEPORT_SPELL = ITEMS.register("home_bringer_teleport", () -> new SpellEffectItem(SpellRegister.HOME_BRINGER_TELEPORT));

    public static final DeferredHolder<Item, SpellEffectItem> LIGHTING_BOLT_SPELL = ITEMS.register("lighting_bolt", () -> new SpellEffectItem(SpellRegister.LIGHTNING_BOLT));

    public static final DeferredHolder<Item, SpellEffectItem> TRIGGER_SNOWBALL_SPELL = ITEMS.register("trigger_snowball", () -> new SpellEffectItem(SpellRegister.TRIGGER_SNOWBALL));
    public static final DeferredHolder<Item, SpellEffectItem> SNOWBALL_SPELL = ITEMS.register("snowball", () -> new SpellEffectItem(SpellRegister.SNOWBALL));

    public static final DeferredHolder<Item, SpellEffectItem> TRIGGER_FIREBALL_SPELL = ITEMS.register("trigger_fireball", () -> new SpellEffectItem(SpellRegister.TRIGGER_FIREBALL));
    public static final DeferredHolder<Item, SpellEffectItem> FIREBALL_SPELL = ITEMS.register("fireball", () -> new SpellEffectItem(SpellRegister.FIREBALL));

    public static final DeferredHolder<Item, SpellEffectItem> EXPLOSION_SPELL = ITEMS.register("explosion", () -> new SpellEffectItem(SpellRegister.EXPLOSION));
    public static final DeferredHolder<Item, SpellEffectItem> MAJOR_EXPLOSION_SPELL = ITEMS.register("major_explosion", () -> new SpellEffectItem(SpellRegister.MAJOR_EXPLOSION));


    public static final DeferredHolder<Item, SpellEffectItem> CONJURE_LIGHT_SPELL = ITEMS.register("conjure_light", () -> new SpellEffectItem(SpellRegister.CONJURE_LIGHT));
    public static final DeferredHolder<Item, SpellEffectItem> CONJURE_BLOCK_SPELL = ITEMS.register("conjure_block", () -> new SpellEffectItem(SpellRegister.CONJURE_BLOCK));


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
