package com.hakimen.wandrous.client.tooltip;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.utils.RenderUtils;
import com.hakimen.wandrous.common.item.component.WandDataComponent;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.List;

public class SpellStatsTooltipRenderer implements ClientTooltipComponent {

    final SpellStatsComponent stats;

    public SpellStatsTooltipRenderer(SpellStatsComponent stats) {
        this.stats = stats;

    }

    @Override
    public int getHeight() {
        return 12 * 6 + 2;
    }

    @Override
    public int getWidth(Font pFont) {
        WandDataComponent.WandStat stat = stats.wand().get(DataComponentsRegister.WAND_COMPONENT);
        return pFont.width(I18n.get("tooltip.wandrous.wand.mana_charge_speed", stat.getManaChargeSpeed())) + 16;
    }

    @Override
    public void renderImage(Font pFont, int pX, int pY, GuiGraphics gfx) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "textures/gui/tooltip/status.png");


        RenderSystem.setShaderTexture(0, location);
        RenderSystem.setShader(GameRenderer::getRendertypeEntityTranslucentShader);
        VertexConsumer consumer = gfx.bufferSource().getBuffer(RenderType.entityTranslucent(location));
        Matrix4f mat = gfx.pose().last().pose();

        WandDataComponent.WandStat stat = stats.wand().get(DataComponentsRegister.WAND_COMPONENT);

        List<String> keys = List.of(
                I18n.get("tooltip.wandrous.wand.cast_delay", "%.2f".formatted(stat.getCastDelay())),
                I18n.get("tooltip.wandrous.wand.recharge_time", "%.2f".formatted(stat.getRechargeSpeed())),
                I18n.get("tooltip.wandrous.wand.mana_max", stat.getMaxMana()),
                I18n.get("tooltip.wandrous.wand.current_mana", stat.getMana()),
                I18n.get("tooltip.wandrous.wand.mana_charge_speed", stat.getManaChargeSpeed()),
                I18n.get("tooltip.wandrous.wand.capacatiy", stat.getCapacity())
        );

        for (int i = 0; i < 6; i++) {
            RenderUtils.guiQuad(consumer, mat, gfx.pose().last(), new Vector3f(pX, pY + (12 * i), 0), new Vector3f(12, 12, 0), new Vector2f((1 / 6f) * i, 0), new Vector2f(1f / 6f, 1f), 0xf000e0, new float[]{1f, 1f, 1f, 1f});
        }

        for (int i = 0; i < 6; i++) {
            gfx.drawString(pFont, keys.get(i), pX + 16, pY + (12 * i) + 3, 0xffffff);
        }
    }

    @Override
    public void renderText(Font pFont, int pMouseX, int pMouseY, Matrix4f pMatrix, MultiBufferSource.BufferSource pBufferSource) {
        ClientTooltipComponent.super.renderText(pFont, pMouseX, pMouseY, pMatrix, pBufferSource);
    }

    public record SpellStatsComponent(ItemStack wand) implements TooltipComponent {
    }
}
