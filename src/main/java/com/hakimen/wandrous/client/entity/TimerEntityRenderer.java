package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.entity.TimerEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class TimerEntityRenderer extends EntityRenderer<TimerEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Wandrous.MODID, "textures/entity/timer/base.png");
    private static final ResourceLocation SMALL_ARM = new ResourceLocation(Wandrous.MODID, "textures/entity/timer/small.png");
    private static final ResourceLocation BIG_ARM = new ResourceLocation(Wandrous.MODID, "textures/entity/timer/large.png");

    public TimerEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(TimerEntity timerEntity) {
        return TEXTURE;
    }

    @Override
    public void render(TimerEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        int maxTickTime = pEntity.getEntityData().get(TimerEntity.MAX_TICK_TIME);

        renderBase(pPoseStack,pBuffer,pPackedLight);
        renderSmallArm(pPoseStack,pBuffer,pPackedLight);
        renderBigArm(pPoseStack,pBuffer,pPackedLight, pPartialTick, pEntity.tickCount, maxTickTime);

        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected int getBlockLightLevel(TimerEntity pEntity, BlockPos pPos) {
        return 15;
    }

    @Override
    protected int getSkyLightLevel(TimerEntity pEntity, BlockPos pPos) {
        return 15;
    }

    public static void renderBase(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight){
        blitTexture(pPoseStack, pBuffer, pPackedLight, TEXTURE, 0.01f);
    }

    public static void renderSmallArm(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight){
        blitTexture(pPoseStack, pBuffer, pPackedLight, SMALL_ARM, 0.02f);
    }

    @Override
    public boolean shouldRender(TimerEntity pLivingEntity, Frustum pCamera, double pCamX, double p_114494_, double pCamY) {
        return true;
    }

    private static void blitTexture(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, ResourceLocation extra, float offset) {
        float scale = 8f;
        pPoseStack.pushPose();
        PoseStack.Pose posestack$pose = pPoseStack.last();
        pPoseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
        pPoseStack.scale(scale,scale,scale);
        pPoseStack.translate(0,0,offset);
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucent(extra));
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 0.0F, -0.25f, 0, 1, 0,255,200);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 1.0F, -0.25f, 1, 1, 0,255,200);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 1.0F, 1f - 0.25f, 1, 0, 0,255,200);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 0.0F, 1f - 0.25f, 0, 0, 0,255,200);
        pPoseStack.popPose();
    }

    public static void renderBigArm(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, float partialTicks, float tickCount, int maxTickTime){
        float angle = Math.min(Math.max((tickCount + partialTicks)/maxTickTime, 0) * 360f, 360f);

        float scale = 8f;
        pPoseStack.pushPose();
        PoseStack.Pose posestack$pose = pPoseStack.last();
        pPoseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
        pPoseStack.mulPose(Axis.ZN.rotationDegrees(angle));
        pPoseStack.scale(scale,scale,scale);
        pPoseStack.translate(-0.005f,-0.01f,0.03f);
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucent(BIG_ARM));
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 0.0F, -0.25f, 0, 1, 0,255,200);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 1.0F, -0.25f, 1, 1, 0,255,200);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 1.0F, 1f - 0.25f, 1, 0, 0,255,200);
        vertex(vertexconsumer, matrix4f, matrix3f, pPackedLight, 0.0F, 1f - 0.25f, 0, 0, 0,255,200);
        pPoseStack.popPose();
    }

    private static void vertex(VertexConsumer pConsumer, Matrix4f pPose, Matrix3f pNormal, int pLightmapUV, float pX, float pY, int pU, int pV, int r, int g, int b) {
        pConsumer.vertex(pPose, pX - 0.5F, pY - 0.25F, 0.0F).color(r, g,b, 255).uv((float)pU, (float)pV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pLightmapUV).normal(pNormal, 0.0F, 0.0F, 1.0F).endVertex();
    }
}
