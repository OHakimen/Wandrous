package com.hakimen.wandrous.client.event;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.item.InscribedLensItem;
import com.hakimen.wandrous.common.item.SpellEffectItem;
import com.hakimen.wandrous.common.item.component.WandDataComponent;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import com.hakimen.wandrous.common.registers.ItemRegister;
import com.hakimen.wandrous.common.utils.ChargesUtils;
import com.hakimen.wandrous.common.utils.GlyphUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD, modid = Wandrous.MODID)
public class AddItemDecorator {

    @SubscribeEvent
    public static void renderDecoration(RegisterItemDecorationsEvent event) {
            ItemRegister.ITEMS.getRegistry().get().stream().filter(item -> item instanceof SpellEffectItem sei && ChargesUtils.hasCharge(sei.getDefaultInstance())).toList().forEach(toRender ->
                event.register(toRender, (guiGraphics, font, stack, xOffset, yOffset) -> {
                    guiGraphics.pose().pushPose();
                    guiGraphics.pose().translate(0, 0, 200);
                    guiGraphics.pose().scale(0.5f, 0.5f, 0.5f);
                    guiGraphics.drawString(font,
                            "" + ChargesUtils.currentCharges(stack), xOffset * 2 + 30 - font.width("" + ChargesUtils.currentCharges(stack)), yOffset * 2 + 22, ChargesUtils.currentCharges(stack) == 0 ? 0xff5555 : 0xffffff);
                    guiGraphics.pose().popPose();
                    return true;
                })
        );

        event.register(ItemRegister.WAND.get(), (guiGraphics, font, stack, xOffset, yOffset) -> {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0, 0, 200);

            if(stack.get(DataComponentsRegister.WAND_COMPONENT.get()).getWandCooldown() > 0){
                WandDataComponent.WandStat stat = stack.get(DataComponentsRegister.WAND_COMPONENT.get());
                guiGraphics.fill(RenderType.guiOverlay(), xOffset, (int) (yOffset + 16 - ((float)stat.getWandCooldown() / stat.getMaxCooldown() * 16)), xOffset + 16, yOffset + 16, stat.isFromCastDelay() ? 0x64ffffff : 0x64ffc7c7);
            }

            guiGraphics.pose().popPose();
            return false;
        });

        ItemRegister.ITEMS.getRegistry().get().stream().filter(item -> item instanceof InscribedLensItem).forEach(item ->
                event.register(item, (gfx, font, stack, xOffset, yOffset) -> {
                    gfx.pose().pushPose();
                    gfx.pose().translate(0, 0, 200);
                    gfx.pose().scale(0.5f,0.5f,0.5f);
                    ResourceLocation location = GlyphUtils.getGlyphTextureFromStack(stack);

                    RenderSystem.setShaderTexture(0, location);
                    RenderSystem.setShader(GameRenderer::getRendertypeEntityTranslucentShader);
                    VertexConsumer consumer = gfx.bufferSource().getBuffer(RenderType.entityTranslucent(location));
                    Matrix4f mat = gfx.pose().last().pose();

                    quad(consumer, mat, gfx.pose().last(), new Vector3f(xOffset * 2 + 8.5f, yOffset * 2 + 8, 0), new Vector3f(14, 16, 0), new Vector2f(0, 0), new Vector2f(1, 1f), 0xf000e0, GlyphUtils.getColorFromGlyph(stack));
                    gfx.pose().popPose();
                    return false;
                })
        );
    }

    private static void quad(VertexConsumer consumer, Matrix4f mat, PoseStack.Pose norm, Vector3f pos, Vector3f size, Vector2f uvPos, Vector2f uvSize, int light, float[] color) {
        vertex(consumer, mat, norm, new Vector3f(pos), uvPos, light, color);
        vertex(consumer, mat, norm, new Vector3f(pos).add(0, size.y, 0), new Vector2f(uvPos).add(0, uvSize.y), light, color);
        vertex(consumer, mat, norm, new Vector3f(pos).add(size.x, size.y, 0), new Vector2f(uvPos).add(uvSize), light, color);
        vertex(consumer, mat, norm, new Vector3f(pos).add(size.x, 0, 0), new Vector2f(uvPos).add(uvSize.x, 0), light, color);
    }

    private static void vertex(VertexConsumer consumer, Matrix4f mat, PoseStack.Pose norm, Vector3f pos, Vector2f uv, int light, float[] color) {
        consumer
                .addVertex(mat, pos.x, pos.y, pos.z)
                .setColor(color[0], color[1], color[2], color[3])
                .setUv(uv.x, uv.y)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv2(light, light)
                .setNormal(norm, -1, -1, -1);
    }
}
