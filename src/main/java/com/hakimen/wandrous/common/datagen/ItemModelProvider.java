package com.hakimen.wandrous.common.datagen;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.item.SpellEffectItem;
import com.hakimen.wandrous.common.registers.BlockRegister;
import com.hakimen.wandrous.common.registers.ItemRegister;
import com.hakimen.wandrous.common.spell.SpellEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Wandrous.MODID, existingFileHelper);
    }


    @Override
    protected void registerModels() {

        makeItems();
        makeSpells();
    }

    private void makeItems(){
        block(BlockRegister.CHERT.get());
        block(BlockRegister.CHERT_STAIRS.get());
        block(BlockRegister.CHERT_SLAB.get());
        getBuilder(BuiltInRegistries.BLOCK.getKey(BlockRegister.CHERT_WALL.get()).toString().replaceAll(Wandrous.MODID+":",""))
                .parent(new ModelFile.ExistingModelFile(ResourceLocation.fromNamespaceAndPath("minecraft", "block/wall_inventory"), existingFileHelper))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chert"));


        block(BlockRegister.POLISHED_CHERT.get());
        block(BlockRegister.POLISHED_CHERT_STAIRS.get());
        block(BlockRegister.POLISHED_CHERT_SLAB.get());


        block(BlockRegister.CHERT_BRICKS.get());
        block(BlockRegister.CHERT_BRICKS_STAIRS.get());
        block(BlockRegister.CHERT_BRICKS_SLAB.get());
        getBuilder(BuiltInRegistries.BLOCK.getKey(BlockRegister.CHERT_BRICKS_WALL.get()).toString().replaceAll(Wandrous.MODID+":",""))
                .parent(new ModelFile.ExistingModelFile(ResourceLocation.fromNamespaceAndPath("minecraft", "block/wall_inventory"), existingFileHelper))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chert_bricks"));

        block(BlockRegister.CRACKED_CHERT_BRICKS.get());
        blockWithAnotherModel(BlockRegister.CHISELED_CHERT_BRICKS.get(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/chiseled_chert_bricks_0"));
        block(BlockRegister.CHERT_PILLAR.get());

        block(BlockRegister.MOSSY_CHERT_BRICKS.get());
        block(BlockRegister.MOSSY_CHERT_BRICKS_STAIRS.get());
        block(BlockRegister.MOSSY_CHERT_BRICKS_SLAB.get());
        getBuilder(BuiltInRegistries.BLOCK.getKey(BlockRegister.MOSSY_CHERT_BRICKS_WALL.get()).toString().replaceAll(Wandrous.MODID+":",""))
                .parent(new ModelFile.ExistingModelFile(ResourceLocation.fromNamespaceAndPath("minecraft", "block/wall_inventory"), existingFileHelper))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/mossy_chert_bricks"));

        block(BlockRegister.CHERT_TILES.get());
        block(BlockRegister.CHERT_TILES_STAIRS.get());
        block(BlockRegister.CHERT_TILES_SLAB.get());

        block(BlockRegister.MOSSY_CHERT_TILES.get());
        block(BlockRegister.MOSSY_CHERT_TILES_STAIRS.get());
        block(BlockRegister.MOSSY_CHERT_TILES_SLAB.get());

        block(BlockRegister.TEALESTITE_BLOCK.get());
        block(BlockRegister.BUDDING_TEALESTITE.get());

        block(BlockRegister.GLYPH_PROJECTOR.get());
        block(BlockRegister.ARCANE_INSCRIBER.get());

        singleTexture("small_tealestite_bud", mcLoc("generated"),"layer0", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/small_tealestite_bud"));
        singleTexture("medium_tealestite_bud", mcLoc("generated"),"layer0", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/medium_tealestite_bud"));
        singleTexture("big_tealestite_bud", mcLoc("generated"),"layer0", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/big_tealestite_bud"));
        singleTexture("tealestite_cluster", mcLoc("generated"),"layer0", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/tealestite_cluster"));


        basicItem(ItemRegister.SCROLL.get());
        basicItem(ItemRegister.TEALESTITE_SHARD.get());
        basicItem(ItemRegister.TEALESTITE_RECHARGE_CRYSTAL.get());
        basicItem(ItemRegister.TEALESTITE_GREATER_RECHARGE_CRYSTAL.get());
        basicItem(ItemRegister.INSCRIBED_LENS.get());
    }

    private void makeSpells(){

        makeTriggerSpell(ItemRegister.TRIGGER_FIREBALL_SPELL.get(), "minecraft:item/fire_charge");
        makeTimerSpell(ItemRegister.TIMER_FIREBALL_SPELL.get(), "minecraft:item/fire_charge");
        makeVanillaBasedSpell(ItemRegister.FIREBALL_SPELL.get(), "minecraft:item/fire_charge");


        makeTriggerSpell(ItemRegister.TRIGGER_GLIMMERING_BOLT_SPELL.get(), "wandrous:item/spell/glimmering_bolt");
        makeTimerSpell(ItemRegister.TIMER_GLIMMERING_BOLT_SPELL.get(), "wandrous:item/spell/glimmering_bolt");
        makeSpell(ItemRegister.GLIMMERING_BOLT_SPELL.get());

        makeTriggerSpell(ItemRegister.TRIGGER_SONIC_BOOM_SPELL.get(), "wandrous:item/spell/sonic_boom");
        makeTimerSpell(ItemRegister.TIMER_SONIC_BOOM_SPELL.get(), "wandrous:item/spell/sonic_boom");
        makeSpell(ItemRegister.SONIC_BOOM_SPELL.get());

        makeSpell(ItemRegister.BLACK_HOLE_SPELL.get());
        makeTimerSpell(ItemRegister.TIMER_BLACK_HOLE_SPELL.get(), "wandrous:item/spell/black_hole");

        makeTriggerSpell(ItemRegister.TRIGGER_CHAIN_SHOT_SPELL.get(), "wandrous:item/spell/chain_shot");
        makeTimerSpell(ItemRegister.TIMER_CHAIN_SHOT_SPELL.get(), "wandrous:item/spell/chain_shot");
        makeSpell(ItemRegister.CHAIN_SHOT_SPELL.get());

        makeSpell(ItemRegister.BOMB_SPELL.get());

        makeSpell(ItemRegister.DIVIDE_BY_2_SPELL.get());
        makeSpell(ItemRegister.DIVIDE_BY_3_SPELL.get());
        makeSpell(ItemRegister.DIVIDE_BY_4_SPELL.get());

        makeSpell(ItemRegister.DOUBLE_CAST_SPELL.get());
        makeSpell(ItemRegister.TRIPLE_CAST_SPELL.get());

        makeSpell(ItemRegister.TELEPORT_CAST_SPELL.get());
        makeSpell(ItemRegister.LONG_DISTANCE_CAST_SPELL.get());
        makeSpell(ItemRegister.LINE_CAST_SPELL.get());

        makeSpell(ItemRegister.DOUBLE_SPLIT_SPELL.get());
        makeSpell(ItemRegister.TRIPLE_SPLIT_SPELL.get());
        makeSpell(ItemRegister.QUAD_SPLIT_SPELL.get());

        makeSpell(ItemRegister.HEXAGON_CAST_SPELL.get());
        makeSpell(ItemRegister.SPREAD_CAST_SPELL.get());

        makeSpell(ItemRegister.FREEZING_CHARGE_SPELL.get());
        makeSpell(ItemRegister.POISON_CHARGE_SPELL.get());
        makeSpell(ItemRegister.IGNEOUS_CHARGE_SPELL.get());
        makeSpell(ItemRegister.CRUMBLING_CHARGE_SPELL.get());

        makeSpell(ItemRegister.ACCELERATE_SPELL.get());
        makeSpell(ItemRegister.CRITICAL_PLUS_SPELL.get());
        makeSpell(ItemRegister.ADD_MANA_SPELL.get());
        makeSpell(ItemRegister.DAMAGE_PLUS_SPELL.get());
        makeSpell(ItemRegister.HEAVY_SHOT_SPELL.get());
        makeSpell(ItemRegister.LIGHT_SHOT_SPELL.get());
        makeSpell(ItemRegister.INCREASE_LIFETIME_SPELL.get());
        makeSpell(ItemRegister.DECREASE_LIFETIME_SPELL.get());
        makeSpell(ItemRegister.INCREASE_RANGE_SPELL.get());
        makeSpell(ItemRegister.DECREASE_RANGE_SPELL.get());
        makeSpell(ItemRegister.INCREASE_SPREAD_SPELL.get());
        makeSpell(ItemRegister.DECREASE_SPREAD_SPELL.get());
        makeSpell(ItemRegister.HEAVY_SPREAD_SPELL.get());
        makeSpell(ItemRegister.DECREASE_RECHARGE_TIME_SPELL.get());
        makeSpell(ItemRegister.PIERCING_SPELL.get());
        makeSpell(ItemRegister.BLOODLUST_SPELL.get());
        makeSpell(ItemRegister.FALTERING_SPELL.get());

        makeSpell(ItemRegister.HEALTH_TO_POWER_SPELL.get());
        makeSpell(ItemRegister.FRIENDS_TO_POWER_SPELL.get());

        makeSpell(ItemRegister.SUMMON_LIGHTING_BOLT_SPELL.get());
        makeVanillaBasedSpell(ItemRegister.SUMMON_TNT_MINECART_SPELL.get(),"minecraft:item/tnt_minecart");
        makeSpell(ItemRegister.SUMMON_BEE_SWARM_SPELL.get());
        makeSpell(ItemRegister.SUMMON_WOLF_PACK_SPELL.get());
        makeTriggerSpell(ItemRegister.GLYPH_OF_TRIGGERING.get(), "wandrous:item/spell/glyph_of_triggering");

        makeSpell(ItemRegister.CHAINSAW_SPELL.get());
        makeSpell(ItemRegister.DRILL_SPELL.get());
        makeSpell(ItemRegister.GIGA_DRILL_SPELL.get());

        makeSpell(ItemRegister.TELEPORT_SPELL.get());
        makeSpell(ItemRegister.SWAP_TELEPORT_SPELL.get());
        makeSpell(ItemRegister.HOME_BRINGER_TELEPORT_SPELL.get());

        makeSpell(ItemRegister.COLLECT_SPELL.get());
        makeSpell(ItemRegister.SMELT_SPELL.get());

        makeSpell(ItemRegister.SMALL_DELAY_CAST_SPELL.get());
        makeSpell(ItemRegister.MEDIUM_DELAY_CAST_SPELL.get());
        makeSpell(ItemRegister.BIG_DELAY_CAST_SPELL.get());

        makeSpell(ItemRegister.EXPLOSION_SPELL.get());
        makeSpell(ItemRegister.MAJOR_EXPLOSION_SPELL.get());
        makeSpell(ItemRegister.NUKE_SPELL.get());
        makeSpell(ItemRegister.PLASMA_BEAM.get());

        makeSpell(ItemRegister.HOMING_SPELL.get());
        makeSpell(ItemRegister.BOOMERANG_SPELL.get());
        makeSpell(ItemRegister.GUIDE_SPELL.get());

        makeSpell(ItemRegister.CONJURE_LIGHT_SPELL.get());
        makeSpell(ItemRegister.CONJURE_BLOCK_SPELL.get());
        makeVanillaBasedSpell(ItemRegister.CONJURE_WEBS_SPELL.get(), "minecraft:block/cobweb");

        makeSpell(ItemRegister.GUST_SPELL.get());
        makeSpell(ItemRegister.CHAIN_PRISON_SPELL.get());

        makeSpell(ItemRegister.BESTOW_CURSE_SILENCE_SPELL.get());
        makeSpell(ItemRegister.BESTOW_CURSE_HUNGER_SPELL.get());
        makeSpell(ItemRegister.BESTOW_CURSE_MINING_FATIGUE_SPELL.get());
        makeSpell(ItemRegister.BESTOW_CURSE_LEVITATE_SPELL.get());
        makeSpell(ItemRegister.BESTOW_CURSE_GLOW_SPELL.get());
        makeSpell(ItemRegister.BESTOW_CURSE_DARKNESS_SPELL.get());
        makeSpell(ItemRegister.BESTOW_CURSE_WEAKNESS_SPELL.get());
        makeSpell(ItemRegister.BESTOW_CURSE_NAUSEA_SPELL.get());
        makeSpell(ItemRegister.BESTOW_CURSE_SLOWNESS_SPELL.get());
        makeSpell(ItemRegister.BESTOW_CURSE_OF_BIG_SPELL.get());
        makeSpell(ItemRegister.BESTOW_CURSE_OF_SMALL_SPELL.get());
        makeSpell(ItemRegister.BESTOW_CURSE_POISON_SPELL.get());

        makeSpell(ItemRegister.BESTOW_BLESSING_HASTE_SPELL.get());
        makeSpell(ItemRegister.BESTOW_BLESSING_BOOST_HEALTH_SPELL.get());
        makeSpell(ItemRegister.BESTOW_BLESSING_REGENERATION_SPELL.get());
        makeSpell(ItemRegister.BESTOW_BLESSING_RESISTANCE_SPELL.get());
        makeSpell(ItemRegister.BESTOW_BLESSING_SATURATION_SPELL.get());
        makeSpell(ItemRegister.BESTOW_BLESSING_NIGHT_VISION_SPELL.get());
        makeSpell(ItemRegister.BESTOW_BLESSING_RESIST_FIRE_SPELL.get());
        makeSpell(ItemRegister.BESTOW_BLESSING_SPEED_SPELL.get());
        makeSpell(ItemRegister.BESTOW_BLESSING_STRENGTH_SPELL.get());

        makeSpell(ItemRegister.IGNEOUS_GAZE_SPELL.get());
        makeSpell(ItemRegister.FREEZING_GAZE_SPELL.get());

        makeSpellWithSpellFrame(ItemRegister.GREEK_LETTER_DELTA_SPELL.get());
        makeSpellWithSpellFrame(ItemRegister.GREEK_LETTER_LAMBDA_SPELL.get());
        makeSpellWithSpellFrame(ItemRegister.GREEK_LETTER_KAPPA_SPELL.get());
    }


    private ItemModelBuilder block(Block block){
        return withExistingParent(BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(Wandrous.MODID+":",""),ResourceLocation.fromNamespaceAndPath(Wandrous.MODID,"block/"+BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(Wandrous.MODID+":","")));
    }

    private ItemModelBuilder blockWithAnotherModel(Block block, ResourceLocation location){
        return withExistingParent(BuiltInRegistries.BLOCK.getKey(block).toString().replaceAll(Wandrous.MODID+":",""),location);
    }


    public void makeTriggerSpell(SpellEffectItem item, String texture){
        getBuilder(item.toString())
                .parent(new ModelFile.ExistingModelFile(ResourceLocation.parse("minecraft:item/generated"), existingFileHelper))
                .texture("layer0", "wandrous:item/spell/bases/spell_base")
                .texture("layer1", texture)
                .texture("layer2", "wandrous:item/spell/layers/trigger");

    }

    public void makeTriggerSpell(SpellEffectItem item){
        getBuilder(item.toString())
                .parent(new ModelFile.ExistingModelFile(ResourceLocation.parse("minecraft:item/generated"), existingFileHelper))
                .texture("layer0", "wandrous:item/spell/bases/spell_base")
                .texture("layer1",  item.toString().replace(":", ":item/spell/"))
                .texture("layer2", "wandrous:item/spell/layers/trigger");

    }

    public void makeTimerSpell(SpellEffectItem item, String texture){
        getBuilder(item.toString())
                .parent(new ModelFile.ExistingModelFile(ResourceLocation.parse("minecraft:item/generated"), existingFileHelper))
                .texture("layer0", "wandrous:item/spell/bases/spell_base")
                .texture("layer1", texture)
                .texture("layer2", "wandrous:item/spell/layers/timer");
    }

    public void makeVanillaBasedSpell(SpellEffectItem item, String texture){
        getBuilder(item.toString())
                .parent(new ModelFile.ExistingModelFile(ResourceLocation.parse("minecraft:item/generated"), existingFileHelper))
                .texture("layer0", item.getSpellEffect().hasKind(SpellEffect.SPELL) ? "wandrous:item/spell/bases/spell_base" : "wandrous:item/spell/bases/modifier_base")
                .texture("layer1", texture);
    }

    public void makeSpell(SpellEffectItem item){
        getBuilder(item.toString())
                .parent(new ModelFile.ExistingModelFile(ResourceLocation.parse("minecraft:item/generated"), existingFileHelper))
                .texture("layer0", item.getSpellEffect().hasKind(SpellEffect.SPELL) ? "wandrous:item/spell/bases/spell_base" : "wandrous:item/spell/bases/modifier_base")
                .texture("layer1", item.toString().replace(":", ":item/spell/"));
    }

    public void makeSpellWithSpellFrame(SpellEffectItem item){
        getBuilder(item.toString())
                .parent(new ModelFile.ExistingModelFile(ResourceLocation.parse("minecraft:item/generated"), existingFileHelper))
                .texture("layer0", "wandrous:item/spell/bases/spell_base" )
                .texture("layer1", item.toString().replace(":", ":item/spell/"));
    }

    public void makeSpellWithModifierFrame(SpellEffectItem item){
        getBuilder(item.toString())
                .parent(new ModelFile.ExistingModelFile(ResourceLocation.parse("minecraft:item/generated"), existingFileHelper))
                .texture("layer0", "wandrous:item/spell/bases/modifier_base" )
                .texture("layer1", item.toString().replace(":", ":item/spell/"));
    }


}
