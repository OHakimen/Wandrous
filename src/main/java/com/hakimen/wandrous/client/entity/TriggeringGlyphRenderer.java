package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.entity.static_spell.TriggeringGlyphEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.phys.AABB;
import org.joml.Matrix4f;

import java.util.UUID;

public class TriggeringGlyphRenderer extends EntityRenderer<TriggeringGlyphEntity> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "textures/entity/magic_circle.png");


    public TriggeringGlyphRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(TriggeringGlyphEntity timerEntity) {
        return TEXTURE;
    }

    @Override
    public void render(TriggeringGlyphEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        float radius = pEntity.getEntityData().get(TriggeringGlyphEntity.RADIUS) * 2f;
        UUID uuid = pEntity.getEntityData().get(TriggeringGlyphEntity.OWNER).get();

        blitTexture(pPoseStack,pBuffer, TEXTURE, (pPartialTick + pEntity.tickCount) / 30f, radius, uuid.equals(Minecraft.getInstance().player.getUUID()));

        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected int getBlockLightLevel(TriggeringGlyphEntity pEntity, BlockPos pPos) {
        return 15;
    }


    private static void blitTexture(PoseStack pPoseStack, MultiBufferSource pBuffer, ResourceLocation extra, float rotation, float radius, boolean renderFully) {
        pPoseStack.pushPose();
        PoseStack.Pose posestack$pose = pPoseStack.last();
        pPoseStack.scale(radius,radius,radius);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
        pPoseStack.mulPose(Axis.ZN.rotation(rotation));
        pPoseStack.translate(0,0, 0.01f);
        Matrix4f matrix4f = posestack$pose.pose();
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucent(extra));
        vertex(vertexconsumer, matrix4f, 0xff00e0, 0.0F, 0.0F, 0, 1, 255,255,255, renderFully ? 255 : 10);
        vertex(vertexconsumer, matrix4f, 0xff00e0, 1.0F, 0.0F, 1, 1, 255,255,255, renderFully ? 255 : 10);
        vertex(vertexconsumer, matrix4f, 0xff00e0, 1.0F, 1f , 1, 0, 255,255,255, renderFully ? 255 : 10);
        vertex(vertexconsumer, matrix4f, 0xff00e0, 0.0F, 1f , 0, 0, 255,255,255, renderFully ? 255 : 10);
        pPoseStack.popPose();
    }

    @Override
    public boolean shouldRender(TriggeringGlyphEntity pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        float radius = pLivingEntity.getEntityData().get(TriggeringGlyphEntity.RADIUS) * 2f;

        if (!pLivingEntity.shouldRender(pCamX, pCamY, pCamZ)) {
            return false;
        } else if (pLivingEntity.noCulling) {
            return true;
        } else {
            AABB aabb = AABB.ofSize(pLivingEntity.getPosition(0), 1, 1, 1).inflate(radius, 1, radius);

            if (pCamera.isVisible(aabb)) {
                return true;
            } else {
                if (pLivingEntity instanceof Leashable leashable) {
                    Entity entity = leashable.getLeashHolder();
                    if (entity != null) {
                        return pCamera.isVisible(entity.getBoundingBoxForCulling());
                    }
                }

                return false;
            }
        }
    }

    private static void vertex(VertexConsumer pConsumer, Matrix4f pPose, int pLightmapUV, float pX, float pY, int pU, int pV, int r, int g, int b, int a) {
        pConsumer.addVertex(pPose, pX - 0.5f, pY - 0.5f, 0.0F).setColor(r, g,b, a).setUv((float)pU, (float)pV).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(pLightmapUV,pLightmapUV).setNormal(1.0F, 1.0F, 1.0F);
    }
}
