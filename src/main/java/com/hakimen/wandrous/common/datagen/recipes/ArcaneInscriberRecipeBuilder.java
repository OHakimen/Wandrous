package com.hakimen.wandrous.common.datagen.recipes;

import com.hakimen.wandrous.common.data.Glyph;
import com.hakimen.wandrous.common.recipe.ArcaneInscribingRecipe;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArcaneInscriberRecipeBuilder implements RecipeBuilder {

    List<Glyph> glyphs;
    Ingredient onSlate;
    ItemStack output;
    int ticks;
    int tier;

    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    public List<Glyph> getGlyphs() {
        return glyphs;
    }

    public ArcaneInscriberRecipeBuilder setGlyphs(List<Glyph> glyphs) {
        this.glyphs = glyphs;
        return this;
    }

    public Ingredient getOnSlate() {
        return onSlate;
    }

    public ArcaneInscriberRecipeBuilder setOnSlate(Ingredient onSlate) {
        this.onSlate = onSlate;
        return this;
    }

    public ItemStack getOutput() {
        return output;
    }

    public ArcaneInscriberRecipeBuilder setOutput(ItemStack output) {
        this.output = output;
        return this;
    }

    public int getTicks() {
        return ticks;
    }

    public ArcaneInscriberRecipeBuilder setTicks(int ticks) {
        this.ticks = ticks;
        return this;
    }

    public int getTier() {
        return tier;
    }

    public ArcaneInscriberRecipeBuilder setTier(int tier) {
        this.tier = tier;
        return this;
    }

    @Override
    public RecipeBuilder unlockedBy(String pName, Criterion<?> pCriterion) {
        this.advancement.addCriterion(pName, pCriterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return this.getOutput().getItem();
    }

    @Override
    public void save(RecipeOutput pRecipeOutput, ResourceLocation pId) {
        this.advancement.parent(new AdvancementHolder(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT, Advancement.Builder.recipeAdvancement().build(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT).value()))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pId))
                .rewards(AdvancementRewards.Builder.recipe(pId)).requirements(AdvancementRequirements.Strategy.OR);

        pRecipeOutput.accept(pId, new ArcaneInscribingRecipe(
                pId,
                NonNullList.copyOf(getGlyphs().stream().map(glyph -> glyph.id).toList()),
                onSlate,
                output,
                tier,
                ticks
        ), advancement.build(pId.withPrefix("recipes/")));
    }
}
