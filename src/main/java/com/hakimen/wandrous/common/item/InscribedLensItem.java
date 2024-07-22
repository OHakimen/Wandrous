package com.hakimen.wandrous.common.item;

import com.hakimen.wandrous.common.data.Glyph;
import com.hakimen.wandrous.common.item.component.InscribedLensDataComponent;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import com.hakimen.wandrous.common.utils.GlyphUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class InscribedLensItem extends Item {
    public InscribedLensItem() {
        super(new Properties().durability(16).component(DataComponentsRegister.GLYPH_COMPONENT.get(), InscribedLensDataComponent.DEFAULT));
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {

        if(GlyphUtils.hasGlyph(pStack) && !GlyphUtils.isDefault(pStack)){
            pTooltipComponents.add(Component.translatable(GlyphUtils.getLangKey(pStack)).withStyle(ChatFormatting.DARK_GRAY));
        }

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

    public static void makeGlyphStack(ItemStack stack, Glyph data) {
        stack.update(DataComponentsRegister.GLYPH_COMPONENT.get(), InscribedLensDataComponent.DEFAULT, glyphData ->
            new InscribedLensDataComponent.GlyphDataBuilder(glyphData)
                    .setTextureName(data.getTexture())
                    .setColor(data.getColor())
                    .build()
        );
    }
}
