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
        makeTriggerSpell(ItemRegister.TRIGGER_FIREBALL.get(), "minecraft:item/fire_charge");
        makeTriggerSpell(ItemRegister.TRIGGER_SNOWBALL.get(), "minecraft:item/snowball");

        makeVanillaBasedSpell(ItemRegister.FIREBALL.get(), "minecraft:item/fire_charge");
        makeVanillaBasedSpell(ItemRegister.SNOWBALL.get(), "minecraft:item/snowball");


        makeSpell(ItemRegister.DIVIDE_BY_2_SPELL.get());
        makeSpell(ItemRegister.DIVIDE_BY_3_SPELL.get());
        makeSpell(ItemRegister.DIVIDE_BY_4_SPELL.get());

        makeSpell(ItemRegister.DOUBLE_CAST_SPELL.get());
        makeSpell(ItemRegister.TRIPLE_CAST_SPELL.get());
        makeSpell(ItemRegister.HEXAGON_CAST_SPELL.get());
        makeSpell(ItemRegister.SPREAD_CAST_SPELL.get());

        makeSpell(ItemRegister.FREEZING_CHARGE_SPELL.get());
        makeSpell(ItemRegister.POISON_CHARGE_SPELL.get());
        makeSpell(ItemRegister.IGNEOUS_CHARGE_SPELL.get());

        makeSpell(ItemRegister.ACCELERATE_SPELL.get());
        makeSpell(ItemRegister.CRITICAL_PLUS_SPELL.get());
        makeSpell(ItemRegister.ADD_MANA_SPELL.get());
        makeSpell(ItemRegister.INCREASE_LIFETIME.get());

        makeSpell(ItemRegister.LIGHTING_BOLT.get());

        makeSpell(ItemRegister.TELEPORT.get());

        makeSpell(ItemRegister.SMALL_DELAY_CAST_SPELL.get());
        makeSpell(ItemRegister.MEDIUM_DELAY_CAST_SPELL.get());
        makeSpell(ItemRegister.BIG_DELAY_CAST_SPELL.get());

        makeSpell(ItemRegister.EXPLOSION_SPELL.get());
        makeSpell(ItemRegister.MAJOR_EXPLOSION_SPELL.get());

        makeSpell(ItemRegister.CONJURE_LIGHT_SPELL.get());
        makeSpell(ItemRegister.CONJURE_BLOCK_SPELL.get());

    }

    public void makeTriggerSpell(SpellEffectItem item, String texture){
        getBuilder(item.toString())
                .parent(new ModelFile.ExistingModelFile(new ResourceLocation("minecraft:item/generated"), existingFileHelper))
                .texture("layer0", "wandrous:item/spell/bases/spell_base")
                .texture("layer1", texture)
                .texture("layer2", "wandrous:item/spell/trigger");

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
