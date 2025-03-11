package com.hakimen.wandrous.common.datagen;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.data.Glyph;
import com.hakimen.wandrous.common.item.SpellEffectItem;
import com.hakimen.wandrous.common.registers.EffectRegister;
import com.hakimen.wandrous.common.registers.GlyphRegister;
import com.hakimen.wandrous.common.registers.ItemRegister;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LangProvider extends LanguageProvider {
    public LangProvider(PackOutput output) {
        super(output, Wandrous.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.wandrous.main", "Wandrous");
        add("itemGroup.wandrous.spells", "Wandrous: Spells");

        add("wandrous.filled_map.dungeon", "Dungeon Explorer Map");
        add("wandrous.map.dungeon.lore", "A map to a ancient dungeon told to hold some arcane secrets");

        add("item.wandrous.wand.silenced", "Silenced");
        add("item.wandrous.wand.empty_wand", "No spells to cast");
        add("item.wandrous.wand.no_mana", "Not enough mana to cast spells");

        add("key.wandrous.perks", "See Perks");

        add("jei.wandrous.category.arcane_inscribing", "Arcane Inscribing");


        addEffects();
        addSpells();
        addGlyphs();
        addItems();

        addDamageTypes();
        addToolTips();
    }


    public void addToolTips(){
        add("tooltip.wandrous.wand.cast_delay", "Cast Delay %.2fs");
        add("tooltip.wandrous.wand.recharge_time", "Recharge Speed %.2fs");
        add("tooltip.wandrous.wand.mana_max", "Mana Max %s");
        add("tooltip.wandrous.wand.current_mana", "Mana %s");
        add("tooltip.wandrous.wand.mana_charge_speed", "Mana Charge Speed %s");
        add("tooltip.wandrous.wand.capacatiy", "Capacity %s");
    }

    public void addDamageTypes(){
        add("death.attack.wandrous.slice", "%s was sliced by %s");
        add("death.attack.wandrous.nuke", "%s was nuked by %s");
        add("death.attack.wandrous.beam", "%s was disintegrated by %s");
    }

    public void addEffects(){
        add(EffectRegister.FREEZING.get(), "Freeze");
        add(EffectRegister.IGNITE.get(), "Ignite");
        add(EffectRegister.SCALE_DOWN.get(), "Scale down");
        add(EffectRegister.SCALE_UP.get(), "Scale up");
        add(EffectRegister.SILENCE.get(), "Silence");
    }

    public void addGlyphs(){
        addGlyph(GlyphRegister.BIND.get(), "Bind");
        addGlyph(GlyphRegister.CONTROL.get(), "Control");
        addGlyph(GlyphRegister.DESTINY.get(), "Destiny");
        addGlyph(GlyphRegister.FOCUS.get(), "Focus");
        addGlyph(GlyphRegister.GUIDANCE.get(),"Guidance");
        addGlyph(GlyphRegister.KNOWLEDGE.get(), "Knowledge");
        addGlyph(GlyphRegister.MIND.get(), "Mind");
        addGlyph(GlyphRegister.NEW.get(), "New");
        addGlyph(GlyphRegister.POWER.get(), "Power");
        addGlyph(GlyphRegister.TINKER.get(), "Tinker");
        addGlyph(GlyphRegister.VITALITY.get(), "Vitality");
        addGlyph(GlyphRegister.WEAVE.get(), "Weave");
    }

    public void addItems(){
        add(ItemRegister.WAND.get(), "Wand");
        add(ItemRegister.SCROLL.get(), "Magic Scroll");
        add(ItemRegister.INSCRIBED_LENS.get(), "Inscribed Lens");

        add(ItemRegister.CHERT.get(), "Chert");
        add(ItemRegister.CHERT_SLAB.get(), "Chert Slab");
        add(ItemRegister.CHERT_STAIRS.get(), "Chert Stairs");
        add(ItemRegister.CHERT_WALL.get(), "Chert Wall");

        add(ItemRegister.POLISHED_CHERT.get(), "Polished Chert");
        add(ItemRegister.POLISHED_CHERT_SLAB.get(), "Polished Chert Slab");
        add(ItemRegister.POLISHED_CHERT_STAIRS.get(), "Polished Chert Stairs");

        add(ItemRegister.CHERT_BRICKS.get(), "Chert Bricks");
        add(ItemRegister.CHISELED_CHERT_BRICKS.get(), "Chiseled Chert Bricks");
        add(ItemRegister.CRACKED_CHERT_BRICKS.get(), "Cracked Chert Bricks");

        add(ItemRegister.CHERT_BRICKS_SLAB.get(), "Chert Brick Slab");
        add(ItemRegister.CHERT_BRICKS_STAIRS.get(), "Chert Brick Stairs");
        add(ItemRegister.CHERT_BRICKS_WALL.get(), "Chert Brick Wall");

        add(ItemRegister.MOSSY_CHERT_BRICKS.get(), "Mossy Chert Bricks");
        add(ItemRegister.MOSSY_CHERT_BRICKS_SLAB.get(), "Mossy Chert Brick Slab");
        add(ItemRegister.MOSSY_CHERT_BRICKS_STAIRS.get(), "Mossy Chert Brick Stairs");
        add(ItemRegister.MOSSY_CHERT_BRICKS_WALL.get(), "Mossy Chert Brick Wall");

        add(ItemRegister.CHERT_PILLAR.get(), "Chert Pillar");

        add(ItemRegister.CHERT_TILES.get(), "Chert Tiles");
        add(ItemRegister.CHERT_TILES_STAIRS.get(), "Chert Tile Stairs");
        add(ItemRegister.CHERT_TILES_SLAB.get(), "Chert Tile Slab");

        add(ItemRegister.MOSSY_CHERT_TILES.get(), "Mossy Chert Tiles");
        add(ItemRegister.MOSSY_CHERT_TILES_STAIRS.get(), "Mossy Chert Tiles Stairs");
        add(ItemRegister.MOSSY_CHERT_TILES_SLAB.get(), "Mossy Chert Tiles Slab");

        add(ItemRegister.TEALESTITE_BLOCK.get(), "Tealestite Block");
        add(ItemRegister.BUDDING_TEALESTITE.get(), "Budding Tealestite");
        add(ItemRegister.SMALL_TEALESTITE_BUD.get(), "Small Tealestite Bud");
        add(ItemRegister.MEDIUM_TEALESTITE_BUD.get(), "Medium Tealestite Bud");
        add(ItemRegister.LARGE_TEALESTITE_BUD.get(), "Large Tealestite Bud");
        add(ItemRegister.TEALESTITE_CLUSTER.get(), "Tealestite Cluster");
        add(ItemRegister.TEALESTITE_SHARD.get(), "Tealestite Shard");
        add(ItemRegister.TEALESTITE_RECHARGE_CRYSTAL.get(), "Tealestite Recharging Crystal");
        add(ItemRegister.TEALESTITE_GREATER_RECHARGE_CRYSTAL.get(), "Greater Tealestite Recharging Crystal");

        add(ItemRegister.ARCANE_INSCRIBER.get(), "Arcane Inscriber");
        add(ItemRegister.GLYPH_PROJECTOR.get(), "Glyph Projector");
    }

    public void addSpells(){
        addSpell(ItemRegister.NUKE_SPELL.get(), "Nuke", "How'd you get your hands in this...");
        addSpell(ItemRegister.PLASMA_BEAM.get(), "Plasma Beam", "An sudden and violent beam of radiant light");

        addSpell(ItemRegister.BOMB_SPELL.get(), "Bomb", "Throws a bomb that damages foes");

        addSpell(ItemRegister.GIGA_DRILL_SPELL.get(), "Giga Drill", "Bigger and therefore better (ROW ROW FIGHT THE POWER !!)");
        addSpell(ItemRegister.DRILL_SPELL.get(), "Drill", "It drills!");
        addSpell(ItemRegister.CHAINSAW_SPELL.get(), "Chainsaw", "A good way to get rid of trees");
        addSpell(ItemRegister.COLLECT_SPELL.get(), "Collect", "Collects items and teleports them to the caster");
        addSpell(ItemRegister.SMELT_SPELL.get(), "Smelt", "Smelts an item");

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

        addSpell(ItemRegister.CHAIN_SHOT_SPELL.get(), "Chain Shot", "A projectile that bounces around targets");
        addSpell(ItemRegister.TIMER_CHAIN_SHOT_SPELL.get(), "Chain Shot with Timer", "A Projectile that bounces around targets and casts a spell once it expires");
        addSpell(ItemRegister.TRIGGER_CHAIN_SHOT_SPELL.get(), "Chain Shot with Trigger", "A Projectile that bounces around targets and casts a spell upon collision");

        addSpell(ItemRegister.FREEZING_CHARGE_SPELL.get(), "Freezing Charge", "It is cold to the touch");
        addSpell(ItemRegister.IGNEOUS_CHARGE_SPELL.get(), "Igneous Charge", "It glows red hot");
        addSpell(ItemRegister.POISON_CHARGE_SPELL.get(), "Poison Charge",   "Doesn't smell nice");
        addSpell(ItemRegister.CRUMBLING_CHARGE_SPELL.get(), "Crumbling Charge", "Caught in a landslide...");

        addSpell(ItemRegister.CRITICAL_PLUS_SPELL.get(), "Critical Plus", "Increases the chance to do critical damage");
        addSpell(ItemRegister.DAMAGE_PLUS_SPELL.get(), "Damage Plus", "Increases the damage of the spells");

        addSpell(ItemRegister.LIGHT_SHOT_SPELL.get(), "Light Shot", "Increases the speed of the projectile at the cost of the damage");
        addSpell(ItemRegister.HEAVY_SHOT_SPELL.get(), "Heavy Shot", "Increases the damage of the projectile at the cost of the speed");

        addSpell(ItemRegister.ADD_MANA_SPELL.get(), "Add Mana", "Gives a mana boost to the next cast");
        addSpell(ItemRegister.ACCELERATE_SPELL.get(), "Accelerate", "Projectile spells gain a speed boost");

        addSpell(ItemRegister.INCREASE_LIFETIME_SPELL.get(), "Increase Lifetime", "Makes spells last longer");
        addSpell(ItemRegister.DECREASE_LIFETIME_SPELL.get(), "Decrease Lifetime", "Makes spells last less");

        addSpell(ItemRegister.INCREASE_SPREAD_SPELL.get(), "Increase Spread", "Projectile spells have more spread");
        addSpell(ItemRegister.DECREASE_SPREAD_SPELL.get(), "Decrease Spread", "Projectile spells have less spread");

        addSpell(ItemRegister.INCREASE_RANGE_SPELL.get(), "Increase Range", "Makes AoE spells bigger");
        addSpell(ItemRegister.DECREASE_RANGE_SPELL.get(), "Decrease Range", "Makes AoE spells smaller");

        addSpell(ItemRegister.FALTERING_SPELL.get(), "Faltering", "Reduces the invincibility frames (i-frames) given to the target after a hit");

        addSpell(ItemRegister.HOMING_SPELL.get(), "Homing", "Makes a projectile move towards enemies");
        addSpell(ItemRegister.BOOMERANG_SPELL.get(), "Boomerang", "Makes a projectile return towards the caster");
        addSpell(ItemRegister.GUIDE_SPELL.get(), "Guide", "Makes a projectile follow the caster look direction");

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

        addSpell(ItemRegister.PIERCING_SPELL.get(), "Piercing", "Makes a projectile go through other entities");
        addSpell(ItemRegister.BLOODLUST_SPELL.get(), "Bloodlust", "Spells gain a huge damage buff but can also hit you");

        addSpell(ItemRegister.TELEPORT_SPELL.get(), "Teleport", "Teleports the caster");
        addSpell(ItemRegister.SWAP_TELEPORT_SPELL.get(), "Swap", "Swaps the caster's position with another entity");
        addSpell(ItemRegister.HOME_BRINGER_TELEPORT_SPELL.get(), "Home Bringer Teleport", "Teleports a entity towards the caster");

        addSpell(ItemRegister.LONG_DISTANCE_CAST_SPELL.get(), "Long Distance Cast", "Casts a spell from further away from the caster");
        addSpell(ItemRegister.TELEPORT_CAST_SPELL.get(), "Teleport Cast", "Casts a spell from another entity position");
        addSpell(ItemRegister.LINE_CAST_SPELL.get(), "Line Cast", "Casts 5 spells in a line");

        addSpell(ItemRegister.DIVIDE_BY_2_SPELL.get() , "Divide by 2", "Casts 2 copies of a spell but its damage is reduced to a half");
        addSpell(ItemRegister.DIVIDE_BY_3_SPELL.get() , "Divide by 3", "Casts 3 copies of a spell but its damage is reduced to a third");
        addSpell(ItemRegister.DIVIDE_BY_4_SPELL.get() , "Divide by 4", "Casts 4 copies of a spell but its damage is reduced to a forth");

        addSpell(ItemRegister.SUMMON_LIGHTING_BOLT_SPELL.get(), "Summon Lightning", "An electrifying experience !");
        addSpell(ItemRegister.SUMMON_TNT_MINECART_SPELL.get(), "Summon TNT Minecart", "Summons a minecart that can unleash a explosion");
        addSpell(ItemRegister.CONJURE_BLOCK_SPELL.get(), "Conjure Block", "Summons a temporary block");
        addSpell(ItemRegister.CONJURE_LIGHT_SPELL.get(), "Conjure Light", "Summons a temporary light source");
        addSpell(ItemRegister.CONJURE_WEBS_SPELL.get(), "Conjure Webs", "Summons a web cocoon");

        addSpell(ItemRegister.GLYPH_OF_TRIGGERING.get(), "Glyph of Triggering", "Triggers a spell once an entity steps on it, barely visible to other players");
        addSpell(ItemRegister.SUMMON_BEE_SWARM_SPELL.get(), "Summon Bee Swarm", "Summons 4 bees that pursue the last hit target");
        addSpell(ItemRegister.SUMMON_WOLF_PACK_SPELL.get(), "Summon Wolf Pack", "Summons 4 wolves that pursue the last hit target");

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
        addSpell(ItemRegister.BESTOW_BLESSING_STRENGTH_SPELL.get(), "Bestow Blessing: Strength", "Blesses the target with strength");

        addSpell(ItemRegister.BESTOW_CURSE_SILENCE_SPELL.get(), "Bestow Curse: Silence", "Curses the target with silence, making it unable to cast magic");
        addSpell(ItemRegister.BESTOW_CURSE_DARKNESS_SPELL.get(), "Bestow Curse: Darkness", "Curses the target with limited vision");
        addSpell(ItemRegister.BESTOW_CURSE_GLOW_SPELL.get(), "Bestow Curse: Glow", "Curses the target making it have a shiny aura");
        addSpell(ItemRegister.BESTOW_CURSE_HUNGER_SPELL.get(), "Bestow Curse: Hunger", "Curses the target with hunger");
        addSpell(ItemRegister.BESTOW_CURSE_NAUSEA_SPELL.get(), "Bestow Curse: Nausea", "Curses the target with nausea");
        addSpell(ItemRegister.BESTOW_CURSE_LEVITATE_SPELL.get(), "Bestow Curse: Levitate", "Curses the target making it ascend to the heavens");
        addSpell(ItemRegister.BESTOW_CURSE_MINING_FATIGUE_SPELL.get(), "Bestow Curse: Mining Fatigue", "Curses the target with reduced mining speed");
        addSpell(ItemRegister.BESTOW_CURSE_SLOWNESS_SPELL.get(), "Bestow Curse: Slowness", "Curses the target with reduced movement speed");
        addSpell(ItemRegister.BESTOW_CURSE_WEAKNESS_SPELL.get(), "Bestow Curse: Weakness", "Curses the target with reduced damage");
        addSpell(ItemRegister.BESTOW_CURSE_POISON_SPELL.get(), "Bestow Curse: Poison", "Curses the target with poison");
        addSpell(ItemRegister.BESTOW_CURSE_OF_BIG_SPELL.get(), "Bestow Curse: Big", "Curses the target and makes them big");
        addSpell(ItemRegister.BESTOW_CURSE_OF_SMALL_SPELL.get(), "Bestow Curse: Small", "Curses the target and makes them small");

        addSpell(ItemRegister.GREEK_LETTER_LAMBDA_SPELL.get(), "Lambda", "Casts a copy of the last spell in the wand");
        addSpell(ItemRegister.GREEK_LETTER_DELTA_SPELL.get(), "Delta", "Casts a copy of the first spell in the wand");
        addSpell(ItemRegister.GREEK_LETTER_KAPPA_SPELL.get(), "Kappa", "Copies all the modifiers in the wand");

        addSpell(ItemRegister.HEALTH_TO_POWER_SPELL.get(), "Health to Power", "Consumes the caster's life force to make spells stronger");
        addSpell(ItemRegister.FRIENDS_TO_POWER_SPELL.get(), "Friends to Power", "Makes spells stronger the more friends you have");
        addSpell(ItemRegister.HEAVY_SPREAD_SPELL.get(), "Heavy Spread", "Increases the spread on spells, but reduces de cast and recharge times");
        addSpell(ItemRegister.DECREASE_RECHARGE_TIME_SPELL.get(), "Decrease Recharge Time", "Reduces the cast and recharge times");

        addSpell(ItemRegister.IGNEOUS_GAZE_SPELL.get(), "Igneous Gaze", "Spread flames and ignites entities in a cone");
        addSpell(ItemRegister.FREEZING_GAZE_SPELL.get(), "Freezing Gaze", "Creates ice walls and freeze entities in a cone");
    }

    private void addSpell(SpellEffectItem item, String name, String description){
        add(item,name);
        add(item.getDescriptionId() + ".desc", description);
    }

    private void addGlyph(Glyph glyph, String name){
        add(glyph.getTexture().toLanguageKey("glyph"), name);
    }
}
