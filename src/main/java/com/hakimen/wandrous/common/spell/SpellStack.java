package com.hakimen.wandrous.common.spell;

import com.hakimen.wandrous.common.item.SpellEffectItem;
import com.hakimen.wandrous.common.utils.ChargesUtils;
import net.minecraft.world.item.ItemStack;

public class SpellStack {
    boolean copy;
    SpellEffect effect;
    boolean hasCharges;
    int charges;
    ItemStack referenceStack;

    public SpellEffect getEffect() {
        return effect;
    }

    public SpellStack setEffect(SpellEffect effect) {
        this.effect = effect;
        return this;
    }

    public int getCharges() {
        return charges;
    }

    public SpellStack setCharges(int charges) {
        this.charges = charges;
        return this;
    }

    public SpellStack(SpellEffect effect, int charges) {
        this.effect = effect;
        this.charges = charges;
    }

    public SpellStack() {
    }

    public ItemStack getReferenceStack() {
        return referenceStack;
    }

    public SpellStack setReferenceStack(ItemStack referenceStack) {
        this.referenceStack = referenceStack;
        return this;
    }

    public boolean hasCharges() {
        return hasCharges;
    }

    public SpellStack setHasCharges(boolean hasCharges) {
        this.hasCharges = hasCharges;
        return this;
    }

    public boolean isCopy() {
        return copy;
    }

    public SpellStack setCopy(boolean copy) {
        this.copy = copy;
        return this;
    }

    public boolean isHasCharges() {
        return hasCharges;
    }

    public static SpellStack fromItemStack(ItemStack stack){
        SpellStack spellStack = new SpellStack();
        if(stack.getItem() instanceof SpellEffectItem sei){
            spellStack.setEffect(sei.getSpellEffect());
        }
        if(ChargesUtils.hasCharge(stack)){
            spellStack.setCharges(ChargesUtils.currentCharges(stack));
            spellStack.setHasCharges(true);
        }
        spellStack.setReferenceStack(stack);
        return spellStack;
    }

    @Override
    public String toString() {
        return charges + " -> " + effect + " "  +  "[" + referenceStack+ "]";
    }
}
