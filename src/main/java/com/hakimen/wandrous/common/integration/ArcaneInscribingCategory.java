package com.hakimen.wandrous.common.integration;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.item.InscribedLensItem;
import com.hakimen.wandrous.common.recipe.ArcaneInscribingRecipe;
import com.hakimen.wandrous.common.registers.GlyphRegister;
import com.hakimen.wandrous.common.registers.ItemRegister;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ArcaneInscribingCategory implements IRecipeCategory<ArcaneInscribingRecipe> {

    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Wandrous.MODID,
            "textures/gui/jei/jei_arcane_inscribing.png");

    @Override
    public RecipeType<ArcaneInscribingRecipe> getRecipeType() {
        return WandrousJeiPlugin.ARCANE_INSCRIBING;
    }

    private IDrawableStatic background;
    private IDrawable icon;
    private IGuiHelper helper;

    public ArcaneInscribingCategory(IGuiHelper helper) {
        this.helper = helper;
        background = helper.drawableBuilder(TEXTURE,0,0,180,200).setTextureSize(180, 196).build();
        icon = helper.createDrawableItemStack(ItemRegister.ARCANE_INSCRIBER.get().getDefaultInstance());
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.wandrous.category.arcane_inscribing");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ArcaneInscribingRecipe recipe, IFocusGroup focuses) {
        List<IRecipeSlotBuilder> slots = new ArrayList<>();

        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 82,46));
        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 82,117));
        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 118,81));
        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 46,81));

        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 55,54));
        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 55,108));
        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 109,54));
        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 109,108));
        
        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 82,10));
        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 82,152));
        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 154,81));
        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 10,81));

        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 28,28));
        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 28,135));
        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 136,28));
        slots.add(builder.addSlot(RecipeIngredientRole.INPUT, 136,135));

        for (int i = 0; i < recipe.getRequiredGlyphs().size(); i++) {
            ItemStack stack = ItemRegister.INSCRIBED_LENS.get().getDefaultInstance();
            InscribedLensItem.makeGlyphStack(stack, GlyphRegister.GLYPHS.getRegistry().get().get(recipe.getRequiredGlyphs().get(i)));
            slots.get(i).addItemStack(stack);
        }

        builder.addSlot(RecipeIngredientRole.CATALYST, 82,81).addIngredients(recipe.getOnSlate());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 154,170).addItemStack(recipe.getOutput());
    }
    

    @Override
    public void draw(ArcaneInscribingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {

        guiGraphics.drawString(Minecraft.getInstance().font, "Tier " + recipe.getTier(), 2,2,0x888888);
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
    }
}
