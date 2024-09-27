package com.hakimen.wandrous.common.utils;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.neoforged.neoforge.common.CommonHooks;

public class EnchantmentHelper {
    public static ItemStack getEnchantedBookForEnchantmentHolder(ResourceKey<Enchantment> key, int level){
        Enchantment enchantment = CommonHooks.resolveLookup(Registries.ENCHANTMENT).get(key).get().value();
        return EnchantedBookItem.createForEnchantment(
                new EnchantmentInstance(
                        Holder.direct(enchantment),
                        level
                )
        );
    }
}
