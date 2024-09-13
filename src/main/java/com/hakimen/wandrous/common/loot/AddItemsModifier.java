package com.hakimen.wandrous.common.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.Map;

public class AddItemsModifier extends LootModifier {

    public static final MapCodec<AddItemsModifier> CODEC = RecordCodecBuilder.mapCodec(inst -> codecStart(inst)
            .and(Codec.unboundedMap(Codec.STRING, ExtraCodecs.POSITIVE_FLOAT)
                    .fieldOf("items")
                    .forGetter(addItemModifier -> addItemModifier.addItems)
            ).apply(inst, AddItemsModifier::new));


    Map<String, Float> addItems;


    public AddItemsModifier(LootItemCondition[] conditionsIn, Map<String, Float> addItems) {
        super(conditionsIn);
        this.addItems = addItems;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for (LootItemCondition condition : conditions) {
            if (!condition.test(context)) {
                return generatedLoot;
            }
        }

        addItems.forEach(
                (resourceKey, weight) -> {
                    if(context.getRandom().nextFloat() < weight) {
                        ResourceLocation location = ResourceLocation.tryParse(resourceKey);
                        generatedLoot.add(new ItemStack(BuiltInRegistries.ITEM.get(location)));
                    }
                }
        );

        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
