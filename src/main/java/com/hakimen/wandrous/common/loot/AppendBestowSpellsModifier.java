package com.hakimen.wandrous.common.loot;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.hakimen.wandrous.common.item.SpellEffectItem;
import com.hakimen.wandrous.common.registers.ItemRegister;
import com.hakimen.wandrous.common.spell.effects.spells.BestowSpellEffect;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

public class AppendBestowSpellsModifier extends LootModifier {

    public static final Supplier<Codec<AppendBestowSpellsModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(
                    ExtraCodecs.POSITIVE_INT.fieldOf("quantity")
                            .forGetter(quant -> quant.maxQuantity)
            ).apply(inst, AppendBestowSpellsModifier::new)));


    int maxQuantity;


    public AppendBestowSpellsModifier(LootItemCondition[] conditionsIn, int maxQuantity)
    {
        super(conditionsIn);
        this.maxQuantity = maxQuantity;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for (LootItemCondition condition : conditions) {
            if (!condition.test(context)) {
                return generatedLoot;
            }
        }


        int qtn = 0;
        List<? extends Item> bestowSpells = ItemRegister.ITEMS.getEntries().stream().filter(itemDeferredHolder -> itemDeferredHolder.get() instanceof SpellEffectItem effect && effect.getSpellEffect() instanceof BestowSpellEffect).map(DeferredHolder::get).toList();

        for (Item bestowSpell : bestowSpells) {
            if(context.getRandom().nextFloat() < 0.25f){
                generatedLoot.add(bestowSpell.getDefaultInstance());
                 qtn++;
            }
            if(qtn >= maxQuantity){
                break;
            }
        }

        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec()
    {
        return MapCodec.assumeMapUnsafe(CODEC.get());
    }
}
