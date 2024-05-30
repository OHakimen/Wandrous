package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.entity.projectiles.BlackHoleProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class BlackHoleProjectileRenderer extends EntityRenderer<BlackHoleProjectile> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Wandrous.MODID, "textures/entity/black_hole_projectile.png");


    public BlackHoleProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(BlackHoleProjectile timerEntity) {
        return TEXTURE;
    }

    @Override
    public void render(BlackHoleProjectile pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        float scale = 4f;
        if(pEntity.tickCount > 2){
            pPoseStack.pushPose();
            pPoseStack.scale(scale,scale,scale);
            pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
            PoseStack.Pose posestack$pose = pPoseStack.last();
            Matrix4f matrix4f = posestack$pose.pose();
            Matrix3f matrix3f = posestack$pose.normal();
            VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucent(TEXTURE));
            vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 0.0F, -0.25f, 0, 1);
            vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 1.0F, -0.25f, 1, 1);
            vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 1.0F, 1f - 0.25f, 1, 0);
            vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 0.0F, 1f - 0.25f, 0, 0);
            pPoseStack.popPose();
        }
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected int getBlockLightLevel(BlackHoleProjectile pEntity, BlockPos pPos) {
        return 15;
    }

    private static void vertex(VertexConsumer pConsumer, Matrix4f pPose, Matrix3f pNormal, int pLightmapUV, float pX, float pY, int pU, int pV) {
        pConsumer.vertex(pPose, pX - 0.5F, pY - 0.25F, 0.0F).color(255, 255, 255, 255).uv((float)pU, (float)pV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pLightmapUV).normal(pNormal, 0.0F, 0.0F, 1.0F).endVertex();
    }
}
