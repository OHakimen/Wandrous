package com.hakimen.wandrous.common.datagen;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.item.SpellEffectItem;
import com.hakimen.wandrous.common.registers.ItemRegister;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LangProvider extends LanguageProvider {
    public LangProvider(PackOutput output) {
        super(output, Wandrous.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.wandrous", "Wandrous");

        addSpell(ItemRegister.NUKE_SPELL.get(), "Nuke", "How'd you get your hands in this...");

        addSpell(ItemRegister.BOMB_SPELL.get(), "Bomb", "Throws a bomb that damages foes");

        addSpell(ItemRegister.GIGA_DRILL_SPELL.get(), "Giga Drill", "Bigger and therefore better (ROW ROW FIGHT THE POWER !!)");
        addSpell(ItemRegister.DRILL_SPELL.get(), "Drill", "It drills!");
        addSpell(ItemRegister.CHAINSAW_SPELL.get(), "Chainsaw", "A good way to get rid of trees");

        addSpell(ItemRegister.EXPLOSION_SPELL.get(), "Explosion", "A small explosion");
        addSpell(ItemRegister.MAJOR_EXPLOSION_SPELL.get(), "Major Explosion", "A large explosion");

        addSpell(ItemRegister.BLACK_HOLE_SPELL.get(), "Black Hole", "A slow orb of void that consumes blocks");
        addSpell(ItemRegister.TIMER_BLACK_HOLE_SPELL.get(), "Black Hole with Timer", "A slow orb of void that consumes blocks and casts a spell once it expires");

        addSpell(ItemRegister.SONIC_BOOM_SPELL.get(), "Sonic Boom", "A sonically charged shriek");
        addSpell(ItemRegister.TIMER_SONIC_BOOM_SPELL.get(), "Sonic Boom with Timer", "A sonically charged shriek that casts a spell once it expires");
        addSpell(ItemRegister.TRIGGER_SONIC_BOOM_SPELL.get(), "Sonic Boom with Trigger", "A sonically charged shriek that casts a spell upon collision");


        addSpell(ItemRegister.GLIMMERING_BOLT_SPELL.get(), "Glimmering Bolt", "A shiny arcane projectile");
        addSpell(ItemRegister.TIMER_GLIMMERING_BOLT_SPELL.get(), "Glimmering Bolt with Timer", "A shiny arcane projectile that casts a spell once it expires");
        addSpell(ItemRegister.TRIGGER_GLIMMERING_BOLT_SPELL.get(), "Glimmering Bolt with Trigger", "A shiny arcane projectile that casts a spell upon collision");

        addSpell(ItemRegister.FIREBALL_SPELL.get(), "Fireball", "A fiery orb");
        addSpell(ItemRegister.TIMER_FIREBALL_SPELL.get(), "Fireball with Timer", "A fiery orb that casts a spell once it expires");
        addSpell(ItemRegister.TRIGGER_FIREBALL_SPELL.get(), "Fireball with Trigger", "A fiery orb that casts a spell upon collision");

        addSpell(ItemRegister.FIREBALL_SPELL.get(), "Chain Shot", "A projectile that bounces around targets");
        addSpell(ItemRegister.TIMER_FIREBALL_SPELL.get(), "Chain Shot with Timer", "projectile that bounces around targets and casts a spell once it expires");
        addSpell(ItemRegister.TRIGGER_FIREBALL_SPELL.get(), "Chain Shot with Trigger", "projectile that bounces around targets and casts a spell upon collision");


        addSpell(ItemRegister.FREEZING_CHARGE_SPELL.get(), "Freezing Charge", "It is cold to the touch");
        addSpell(ItemRegister.IGNEOUS_CHARGE_SPELL.get(), "Igneous Charge", "It glows red hot");
        addSpell(ItemRegister.POISON_CHARGE_SPELL.get(), "Poison Charge", "Doesn't smell nice");
        addSpell(ItemRegister.CRUMBLING_CHARGE_SPELL.get(), "Crumbling Charge", "Caught in a landslide...");

        addSpell(ItemRegister.CRITICAL_PLUS_SPELL.get(), "Critical Plus", "Increases the chance to do critical damage");

        addSpell(ItemRegister.ADD_MANA_SPELL.get(), "Add Mana", "Gives a mana boost to the next cast");
        addSpell(ItemRegister.ACCELERATE_SPELL.get(), "Accelerate", "Projectile spells gain a speed boost");

        addSpell(ItemRegister.INCREASE_LIFETIME_SPELL.get(), "Increase Lifetime", "Makes spells last longer");
        addSpell(ItemRegister.DECREASE_LIFETIME_SPELL.get(), "Decrease Lifetime", "Makes spells last less");

        addSpell(ItemRegister.INCREASE_SPREAD_SPELL.get(), "Increase Spread", "Projectile spells have more spread");
        addSpell(ItemRegister.DECREASE_SPREAD_SPELL.get(), "Decrease Spread", "Projectile spells have less spread");

        addSpell(ItemRegister.INCREASE_RANGE_SPELL.get(), "Increase Range", "Makes AoE spells bigger");
        addSpell(ItemRegister.DECREASE_RANGE_SPELL.get(), "Decrease Range", "Makes AoE spells smaller");

        addSpell(ItemRegister.HOMING_SPELL.get(), "Homing", "Makes a projectile move towards enemies");
        addSpell(ItemRegister.BOOMERANG_SPELL.get(), "Boomerang", "Makes a projectile return towards the caster");

        addSpell(ItemRegister.SMALL_DELAY_CAST_SPELL.get(), "Small Delay Cast", "Casts another spell after a little time");
        addSpell(ItemRegister.MEDIUM_DELAY_CAST_SPELL.get(), "Medium Delay Cast", "Casts another spell after some time");
        addSpell(ItemRegister.BIG_DELAY_CAST_SPELL.get(), "Big Delay Cast", "Casts another spell after quite some time");

        addSpell(ItemRegister.DOUBLE_CAST_SPELL.get(), "Double Cast", "Casts 2 spells at the same time");
        addSpell(ItemRegister.TRIPLE_CAST_SPELL.get(), "Triple Cast", "Casts 3 spells at the same time");

        addSpell(ItemRegister.DOUBLE_SPLIT_SPELL.get(), "Double Split Cast", "Casts 2 spells at the same time with different directions");
        addSpell(ItemRegister.TRIPLE_SPLIT_SPELL.get(), "Triple Split Cast", "Casts 3 spells at the same time with different directions");
        addSpell(ItemRegister.QUAD_SPLIT_SPELL.get(), "Quad Split Cast", "Casts 4 spells at the same time with different directions");


        addSpell(ItemRegister.HEXAGON_CAST_SPELL.get(), "Hexagon Cast", "Casts 6 spells in a hexagon");
        addSpell(ItemRegister.SPREAD_CAST_SPELL.get(), "Spread Cast", "Casts a spell in a random location within range");

        addSpell(ItemRegister.TELEPORT_SPELL.get(), "Teleport", "Teleports the caster");
        addSpell(ItemRegister.SWAP_TELEPORT_SPELL.get(), "Swap", "Swaps the caster's position with another entity");
        addSpell(ItemRegister.HOME_BRINGER_TELEPORT_SPELL.get(), "Home Bringer Teleport", "Teleports a entity towards the caster");

        addSpell(ItemRegister.LONG_DISTANCE_CAST_SPELL.get(), "Long Distance Cast", "Casts a spell from further away from the caster");
        addSpell(ItemRegister.TELEPORT_CAST_SPELL.get(), "Teleport Cast", "Casts a spell from another entity position");

        addSpell(ItemRegister.DIVIDE_BY_2_SPELL.get() , "Divide by 2", "Casts 2 copies of a spell but its damage is reduced to a half");
        addSpell(ItemRegister.DIVIDE_BY_3_SPELL.get() , "Divide by 3", "Casts 3 copies of a spell but its damage is reduced to a third");
        addSpell(ItemRegister.DIVIDE_BY_4_SPELL.get() , "Divide by 4", "Casts 4 copies of a spell but its damage is reduced to a forth");

        addSpell(ItemRegister.LIGHTING_BOLT_SPELL.get(), "Summon Lightning", "An electrifying experience !");
        addSpell(ItemRegister.CONJURE_BLOCK_SPELL.get(), "Conjure Block", "Summons a temporary block");
        addSpell(ItemRegister.CONJURE_LIGHT_SPELL.get(), "Conjure Light", "Summons a temporary light source");
        addSpell(ItemRegister.CONJURE_WEBS_SPELL.get(), "Conjure Webs", "Summons a web cocoon");

        addSpell(ItemRegister.GUST_SPELL.get(), "Gust", "Conjures a burst of air that send entities flying");
        addSpell(ItemRegister.CHAIN_PRISON_SPELL.get(), "Chain Prison", "Locks an entity into place");

        addSpell(ItemRegister.BESTOW_BLESSING_HASTE_SPELL.get(), "Bestow Blessing: Haste", "Blesses the target with faster mining speed");
        addSpell(ItemRegister.BESTOW_BLESSING_SPEED_SPELL.get(), "Bestow Blessing: Speed", "Blesses the target with faster movement speed");
        addSpell(ItemRegister.BESTOW_BLESSING_BOOST_HEALTH_SPELL.get(), "Bestow Blessing: Boost Health", "Blesses the target with more maximum health");
        addSpell(ItemRegister.BESTOW_BLESSING_REGENERATION_SPELL.get(), "Bestow Blessing: Regeneration", "Blesses the target with health regeneration");
        addSpell(ItemRegister.BESTOW_BLESSING_RESISTANCE_SPELL.get(), "Bestow Blessing: Resistance", "Blesses the target with damage resistance");
        addSpell(ItemRegister.BESTOW_BLESSING_NIGHT_VISION_SPELL.get(), "Bestow Blessing: Night Vision", "Blesses the target with night vision");
        addSpell(ItemRegister.BESTOW_BLESSING_RESIST_FIRE_SPELL.get(), "Bestow Blessing: Fire Resistance", "Blesses the target with immunity against fire");
        addSpell(ItemRegister.BESTOW_BLESSING_SATURATION_SPELL.get(), "Bestow Blessing: Saturation", "Blesses the target with saturation");

        addSpell(ItemRegister.BESTOW_CURSE_DARKNESS_SPELL.get(), "Bestow Curse: Darkness", "Curses the target with limited vision");
        addSpell(ItemRegister.BESTOW_CURSE_GLOW_SPELL.get(), "Bestow Curse: Glow", "Curses the target making it have a shiny aura");
        addSpell(ItemRegister.BESTOW_CURSE_HUNGER_SPELL.get(), "Bestow Curse: Hunger", "Curses the target with hunger");
        addSpell(ItemRegister.BESTOW_CURSE_NAUSEA_SPELL.get(), "Bestow Curse: Nausea", "Curses the target with nausea");
        addSpell(ItemRegister.BESTOW_CURSE_LEVITATE_SPELL.get(), "Bestow Curse: Levitate", "Curses the target making it ascend to the heavens");
        addSpell(ItemRegister.BESTOW_CURSE_MINING_FATIGUE_SPELL.get(), "Bestow Curse: Mining Fatigue", "Curses the target with reduced mining speed");
        addSpell(ItemRegister.BESTOW_CURSE_SLOWNESS_SPELL.get(), "Bestow Curse: Slowness", "Curses the target with reduced movement speed");
        addSpell(ItemRegister.BESTOW_CURSE_WEAKNESS_SPELL.get(), "Bestow Curse: Weakness", "Curses the target with reduced damage");
    }

    private void addSpell(SpellEffectItem item, String name, String description){
        add(item,name);
        add(item.getDescriptionId() + ".desc", description);
    }
}
