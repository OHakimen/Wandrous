package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.recipe.ArcaneInscribingRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RecipeRegister {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, Wandrous.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ArcaneInscribingRecipe>> ARCANE_INSCRIBING = RECIPES.register(ArcaneInscribingRecipe.Type.ID,() -> ArcaneInscribingRecipe.Serializer.INSTANCE);

    public static void register(IEventBus bus){
        RECIPES.register(bus);
    }
}
