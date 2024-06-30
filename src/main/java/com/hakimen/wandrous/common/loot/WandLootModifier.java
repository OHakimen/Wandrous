package com.hakimen.wandrous.common.loot;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.hakimen.wandrous.common.registers.ItemRegister;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class WandLootModifier extends LootModifier {

    public static final Supplier<Codec<WandLootModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, WandLootModifier::new)));

    public WandLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for (LootItemCondition condition : conditions) {
            if (!condition.test(context)) {
                return generatedLoot;
            }
        }

        generatedLoot.add(ItemRegister.WAND.get().getDefaultInstance());

        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return MapCodec.assumeMapUnsafe(CODEC.get());
    }
}
