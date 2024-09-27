package com.hakimen.wandrous.client.tooltip;

import com.hakimen.wandrous.common.data.Glyph;
import com.hakimen.wandrous.common.utils.GlyphUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class GlyphTooltipRenderer implements ClientTooltipComponent {

    private final GlyphTooltipComponent comp;

    public GlyphTooltipRenderer(GlyphTooltipComponent comp) {
        this.comp = comp;
    }

    @Override
    public int getHeight() {
        return 20;
    }

    @Override
    public int getWidth(Font font) {
        return 16;
    }


    @Override
    public void renderImage(Font pFont, int pX, int pY, GuiGraphics gfx) {
        ResourceLocation location = GlyphUtils.getGlyphTextureFromResourceLocation(comp.glyph().getId());

        RenderSystem.setShaderTexture(0, location);
        RenderSystem.setShader(GameRenderer::getRendertypeEntityTranslucentShader);
        VertexConsumer consumer = gfx.bufferSource().getBuffer(RenderType.entityTranslucent(location));
        Matrix4f mat = gfx.pose().last().pose();

        quad(consumer, mat, gfx.pose().last(), new Vector3f(pX, pY, 0), new Vector3f(14, 16, 0), new Vector2f(0, 0), new Vector2f(1, 1f), 0xf000e0, comp.glyph.getColor());
    }

    private void quad(VertexConsumer consumer, Matrix4f mat, PoseStack.Pose norm, Vector3f pos, Vector3f size, Vector2f uvPos, Vector2f uvSize, int light, float[] color) {
        vertex(consumer, mat, norm, new Vector3f(pos), uvPos, light, color);
        vertex(consumer, mat, norm, new Vector3f(pos).add(0, size.y, 0), new Vector2f(uvPos).add(0, uvSize.y), light, color);
        vertex(consumer, mat, norm, new Vector3f(pos).add(size.x, size.y, 0), new Vector2f(uvPos).add(uvSize), light, color);
        vertex(consumer, mat, norm, new Vector3f(pos).add(size.x, 0, 0), new Vector2f(uvPos).add(uvSize.x, 0), light, color);
    }

    private void vertex(VertexConsumer consumer, Matrix4f mat, PoseStack.Pose norm, Vector3f pos, Vector2f uv, int light, float[] color) {
        consumer
                .addVertex(mat, pos.x, pos.y, pos.z)
                .setColor(color[0], color[1], color[2], color[3])
                .setUv(uv.x, uv.y)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv2(light, light)
                .setNormal(norm, -1, -1, -1);
    }

    @Override
    public void renderText(Font pFont, int pMouseX, int pMouseY, Matrix4f pMatrix, MultiBufferSource.BufferSource pBufferSource) {

    }

    public record GlyphTooltipComponent(Glyph glyph) implements TooltipComponent {

    }


}
