package com.hakimen.wandrous.common.api;

import com.hakimen.wandrous.common.item.SpellEffectItem;
import com.hakimen.wandrous.common.registers.SpellRegister;
import com.hakimen.wandrous.common.utils.ChargesUtils;
import net.minecraft.world.item.ItemStack;

/**<h1>Spell Stack</h1>
 * This class works similarly to a {@link ItemStack} defining a instanced version of a {@link SpellEffect}
 * <br><br>
 * The fields give context to from where did the spell came from, if its a copy, and if it can even cast at all (charges)
 * <br><br>
 * The {@code copy} attribute is specially important because it can define if a spell needs charges to be cast or not,
 * considering the fact that copied spells don't need any charges to actually cast
 */
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
        if(stack == null || stack.isEmpty()){
            spellStack.setEffect(SpellRegister.DUMMY.get());
            spellStack.setCharges(0);
            return spellStack;
        } else if(stack.getItem() instanceof SpellEffectItem sei){
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
