package com.hakimen.wandrous.common.integration;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.recipe.ArcaneInscribingRecipe;
import com.hakimen.wandrous.common.registers.ItemRegister;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class WandrousJeiPlugin implements IModPlugin {

    public static final RecipeType<ArcaneInscribingRecipe> ARCANE_INSCRIBING = RecipeType.create("wandrous", "arcane_inscribing", ArcaneInscribingRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new ArcaneInscribingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<ArcaneInscribingRecipe> inscribingRecipes = recipeManager.getAllRecipesFor(ArcaneInscribingRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(ARCANE_INSCRIBING, inscribingRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ItemRegister.ARCANE_INSCRIBER.get().getDefaultInstance(), ARCANE_INSCRIBING);
        registration.addRecipeCatalyst(ItemRegister.GLYPH_PROJECTOR.get().getDefaultInstance(), ARCANE_INSCRIBING);
    }
}
