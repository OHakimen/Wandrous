package com.hakimen.wandrous.common.utils;

import com.hakimen.wandrous.common.item.component.InscribedLensDataComponent;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class GlyphUtils {
    public static boolean hasGlyph(ItemStack stack){
        return stack.has(DataComponentsRegister.GLYPH_COMPONENT.get());
    }

    public static boolean isDefault(ItemStack stack){
        return stack.get(DataComponentsRegister.GLYPH_COMPONENT.get()).equals(InscribedLensDataComponent.DEFAULT);
    }

    public static String getLangKey(ItemStack stack){
        return stack.get(DataComponentsRegister.GLYPH_COMPONENT.get()).getTextureName().toLanguageKey("glyph");
    }

    public static ResourceLocation getGlyphTextureFromStack(ItemStack stack){
        ResourceLocation location = stack.get(DataComponentsRegister.GLYPH_COMPONENT.get()).getTextureName();
        return ResourceLocation.fromNamespaceAndPath(location.getNamespace(), "textures/glyph/" + location.getPath() + ".png");
    }

    public static ResourceLocation getGlyphTextureFromResourceLocation(ResourceLocation location){
        return ResourceLocation.fromNamespaceAndPath(location.getNamespace(), "textures/glyph/" + location.getPath() + ".png");
    }


    public static float[] getColorFromGlyph(ItemStack stack){
        return stack.get(DataComponentsRegister.GLYPH_COMPONENT.get()).getColor();
    }
}
