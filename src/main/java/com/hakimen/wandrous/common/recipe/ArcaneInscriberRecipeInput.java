package com.hakimen.wandrous.common.recipe;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public class ArcaneInscriberRecipeInput implements RecipeInput {

    ItemStack onSlate;
    SimpleContainer container;
    int tier;

    public ArcaneInscriberRecipeInput(SimpleContainer container, int tier, ItemStack onSlate) {
        this.container = container;
        this.tier = tier;
        this.onSlate = onSlate;
    }

    public SimpleContainer getContainer() {
        return container;
    }

    public ArcaneInscriberRecipeInput setContainer(SimpleContainer container) {
        this.container = container;
        return this;
    }

    public int getTier() {
        return tier;
    }

    public ArcaneInscriberRecipeInput setTier(int tier) {
        this.tier = tier;
        return this;
    }

    public ItemStack getOnSlate() {
        return onSlate;
    }

    public ArcaneInscriberRecipeInput setOnSlate(ItemStack onSlate) {
        this.onSlate = onSlate;
        return this;
    }

    @Override
    public ItemStack getItem(int slot) {
        return container.getItem(slot);
    }

    @Override
    public int size() {
        return container.getContainerSize();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
