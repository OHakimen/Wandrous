package com.hakimen.wandrous.client.ber;

import com.hakimen.wandrous.common.block_entity.GlyphProjectorBlockEntity;
import com.hakimen.wandrous.common.particle.ArcaneKnowledgeParticle;
import com.hakimen.wandrous.common.registers.BlockRegister;
import com.hakimen.wandrous.common.utils.GlyphUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;

public class GlyphProjectorRenderer implements BlockEntityRenderer<GlyphProjectorBlockEntity> {

    BlockEntityRendererProvider.Context context;

    public GlyphProjectorRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(GlyphProjectorBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ItemStack stack = pBlockEntity.getInventory().getStackInSlot(0);

        pPoseStack.pushPose();

        pPoseStack.scale(0.75f, 0.75f, 0.75f);
        pPoseStack.translate(0.18, 0, 0.18);
        context.getBlockRenderDispatcher().renderSingleBlock(
                BlockRegister.TEALESTITE_CLUSTER.get().defaultBlockState(),
                pPoseStack,
                pBuffer,
                stack.isEmpty() ? pPackedLight : 0xf000e0,
                pPackedOverlay
        );
        pPoseStack.popPose();

        pPoseStack.pushPose();

        pPoseStack.translate(0.5, 0.8, 0.5);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(90));

        context.getItemRenderer().renderStatic(stack,
                ItemDisplayContext.FIXED,
                0xff00e0,
                pPackedOverlay,
                pPoseStack,
                pBuffer,
                pBlockEntity.getLevel(),
                0);

        pPoseStack.popPose();

        if (GlyphUtils.hasGlyph(stack) && !GlyphUtils.isDefault(stack)) {
            float[] rgba = GlyphUtils.getColorFromGlyph(stack);
            pPoseStack.pushPose();
            pPoseStack.translate(0.5, 2 + Math.sin((pBlockEntity.hashCode() / 3.14159 + Minecraft.getInstance().level.getGameTime() + pPartialTick) / 16f) / 8f, 0.5);
            if (pBlockEntity.getLevel().getRandom().nextFloat() < 0.05f && !Minecraft.getInstance().isPaused()) {
                pBlockEntity.getLevel().addParticle(
                        new ArcaneKnowledgeParticle.ArcaneKnowledgeParticleOptions(rgba[0],rgba[1],rgba[2]),
                        pBlockEntity.getBlockPos().getX() + 0f + pBlockEntity.getLevel().getRandom().nextFloat(),
                        pBlockEntity.getBlockPos().getY() + 1.00f + pBlockEntity.getLevel().getRandom().nextFloat(),
                        pBlockEntity.getBlockPos().getZ() + 0f + pBlockEntity.getLevel().getRandom().nextFloat(),
                        0, 0, 0
                );
            }
            pPoseStack.rotateAround(new Quaternionf().rotateXYZ(0, (float) Math.toRadians(Minecraft.getInstance().cameraEntity.getYRot() * -1f), 0), 0, 0, 0);
            pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
            VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucent(GlyphUtils.getGlyphTextureFromStack(stack)));

            vertex(vertexconsumer, pPoseStack, 0xf000e0, 0, 0, 0, 1, rgba[0], rgba[1], rgba[2], rgba[3]);
            vertex(vertexconsumer, pPoseStack, 0xf000e0, 1, 0, 1, 1, rgba[0], rgba[1], rgba[2], rgba[3]);
            vertex(vertexconsumer, pPoseStack, 0xf000e0, 1, 1, 1, 0, rgba[0], rgba[1], rgba[2], rgba[3]);
            vertex(vertexconsumer, pPoseStack, 0xf000e0, 0, 1, 0, 0, rgba[0], rgba[1], rgba[2], rgba[3]);

            pPoseStack.popPose();
        }
    }

    @Override
    public boolean shouldRenderOffScreen(GlyphProjectorBlockEntity pBlockEntity) {
        return true;
    }

    @Override
    public boolean shouldRender(GlyphProjectorBlockEntity pBlockEntity, Vec3 pCameraPos) {
        return true;
    }

    @Override
    public AABB getRenderBoundingBox(GlyphProjectorBlockEntity blockEntity) {
        return BlockEntityRenderer.super.getRenderBoundingBox(blockEntity).inflate(0,2,0);
    }

    private static void vertex(VertexConsumer pConsumer, PoseStack poseStack, int pLightmapUV, float pX, float pY, float pU, float pV, float r, float g, float b, float alpha) {
        pConsumer.addVertex(poseStack.last().pose(), pX - 0.5F, pY - 0.25F, 0.0F).setColor(r, g, b, alpha).setUv((float) pU, (float) pV).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(pLightmapUV, pLightmapUV).setNormal(poseStack.last(), 0, 1, 0);
    }
}
