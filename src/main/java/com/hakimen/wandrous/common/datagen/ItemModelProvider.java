package com.hakimen.wandrous.common.datagen;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.item.SpellEffectItem;
import com.hakimen.wandrous.common.registers.ItemRegister;
import com.hakimen.wandrous.common.spell.SpellEffect;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Wandrous.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
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


        makeSpell(ItemRegister.DIVIDE_BY_2_SPELL.get());
        makeSpell(ItemRegister.DIVIDE_BY_3_SPELL.get());
        makeSpell(ItemRegister.DIVIDE_BY_4_SPELL.get());

        makeSpell(ItemRegister.DOUBLE_CAST_SPELL.get());
        makeSpell(ItemRegister.TRIPLE_CAST_SPELL.get());

        makeSpell(ItemRegister.TELEPORT_CAST_SPELL.get());
        makeSpell(ItemRegister.LONG_DISTANCE_CAST_SPELL.get());

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
        makeSpell(ItemRegister.INCREASE_LIFETIME_SPELL.get());
        makeSpell(ItemRegister.DECREASE_LIFETIME_SPELL.get());
        makeSpell(ItemRegister.INCREASE_RANGE_SPELL.get());
        makeSpell(ItemRegister.DECREASE_RANGE_SPELL.get());

        makeSpell(ItemRegister.LIGHTING_BOLT_SPELL.get());

        makeSpell(ItemRegister.CHAINSAW_SPELL.get());
        makeSpell(ItemRegister.DRILL_SPELL.get());
        makeSpell(ItemRegister.GIGA_DRILL_SPELL.get());

        makeSpell(ItemRegister.TELEPORT_SPELL.get());
        makeSpell(ItemRegister.SWAP_TELEPORT_SPELL.get());
        makeSpell(ItemRegister.HOME_BRINGER_TELEPORT_SPELL.get());

        makeSpell(ItemRegister.SMALL_DELAY_CAST_SPELL.get());
        makeSpell(ItemRegister.MEDIUM_DELAY_CAST_SPELL.get());
        makeSpell(ItemRegister.BIG_DELAY_CAST_SPELL.get());

        makeSpell(ItemRegister.EXPLOSION_SPELL.get());
        makeSpell(ItemRegister.MAJOR_EXPLOSION_SPELL.get());

        makeSpell(ItemRegister.HOMING_SPELL.get());
        makeSpell(ItemRegister.BOOMERANG_SPELL.get());

        makeSpell(ItemRegister.CONJURE_LIGHT_SPELL.get());
        makeSpell(ItemRegister.CONJURE_BLOCK_SPELL.get());

    }

    public void makeTriggerSpell(SpellEffectItem item, String texture){
        getBuilder(item.toString())
                .parent(new ModelFile.ExistingModelFile(new ResourceLocation("minecraft:item/generated"), existingFileHelper))
                .texture("layer0", "wandrous:item/spell/bases/spell_base")
                .texture("layer1", texture)
                .texture("layer2", "wandrous:item/spell/layers/trigger");

    }

    public void makeTriggerSpell(SpellEffectItem item){
        getBuilder(item.toString())
                .parent(new ModelFile.ExistingModelFile(new ResourceLocation("minecraft:item/generated"), existingFileHelper))
                .texture("layer0", "wandrous:item/spell/bases/spell_base")
                .texture("layer1",  item.toString().replace(":", ":item/spell/"))
                .texture("layer2", "wandrous:item/spell/layers/trigger");

    }

    public void makeTimerSpell(SpellEffectItem item, String texture){
        getBuilder(item.toString())
                .parent(new ModelFile.ExistingModelFile(new ResourceLocation("minecraft:item/generated"), existingFileHelper))
                .texture("layer0", "wandrous:item/spell/bases/spell_base")
                .texture("layer1", texture)
                .texture("layer2", "wandrous:item/spell/layers/timer");
    }

    public void makeVanillaBasedSpell(SpellEffectItem item, String texture){
        getBuilder(item.toString())
                .parent(new ModelFile.ExistingModelFile(new ResourceLocation("minecraft:item/generated"), existingFileHelper))
                .texture("layer0", item.getSpellEffect().hasKind(SpellEffect.SPELL) ? "wandrous:item/spell/bases/spell_base" : "wandrous:item/spell/bases/modifier_base")
                .texture("layer1", texture);
    }

    public void makeSpell(SpellEffectItem item){
        getBuilder(item.toString())
                .parent(new ModelFile.ExistingModelFile(new ResourceLocation("minecraft:item/generated"), existingFileHelper))
                .texture("layer0", item.getSpellEffect().hasKind(SpellEffect.SPELL) ? "wandrous:item/spell/bases/spell_base" : "wandrous:item/spell/bases/modifier_base")
                .texture("layer1", item.toString().replace(":", ":item/spell/"));
    }


}
