package com.hakimen.wandrous.common.utils;

import com.hakimen.wandrous.common.item.component.SpellCastsDataComponent;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import net.minecraft.world.item.ItemStack;

public class ChargesUtils {
    public static boolean hasCharge(ItemStack stack){
        return stack.has(DataComponentsRegister.CHARGES_COMPONENT.get());
    }


    public static int maxCharges(ItemStack stack){
        return stack.get(DataComponentsRegister.CHARGES_COMPONENT.get()).getMaxCasts();
    }

    public static int currentCharges(ItemStack stack){
        return stack.get(DataComponentsRegister.CHARGES_COMPONENT.get()).getRemaining();
    }

    public static void loseCharge(ItemStack stack){
        stack.update(DataComponentsRegister.CHARGES_COMPONENT.get(), SpellCastsDataComponent.DEFAULT, spellCastsData -> new SpellCastsDataComponent.SpellCastsDataBuilder(spellCastsData).setRemaining(Math.max(0, spellCastsData.getRemaining() - 1)).build());
    }

    public static boolean hasSpentCharges(ItemStack stack){
        return maxCharges(stack) != currentCharges(stack);
    }

    public static void regainCharges(ItemStack stack, int amount){
        stack.update(DataComponentsRegister.CHARGES_COMPONENT.get(), SpellCastsDataComponent.DEFAULT, spellCastsData -> new SpellCastsDataComponent.SpellCastsDataBuilder(spellCastsData)
                .setRemaining(Math.max(0, spellCastsData.getRemaining() + amount)).build());
    }

    public static void regainAllCharges(ItemStack stack){
        stack.update(DataComponentsRegister.CHARGES_COMPONENT.get(), SpellCastsDataComponent.DEFAULT, spellCastsData -> new SpellCastsDataComponent.SpellCastsDataBuilder(spellCastsData)
                .setRemaining(spellCastsData.getMaxCasts()).build());
    }
}
