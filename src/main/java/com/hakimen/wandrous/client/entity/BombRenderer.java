package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.entity.projectiles.BombProjectile;
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
import org.joml.Matrix4f;

public class BombRenderer extends EntityRenderer<BombProjectile> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "textures/item/spell/bomb.png");

    public BombRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(BombProjectile bomb) {
        return TEXTURE;
    }

    @Override
    public void render(BombProjectile pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.tickCount > 2){
            pPoseStack.pushPose();
            pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
            pPoseStack.scale(1.25f,1.25f,1.25f);
            PoseStack.Pose posestack$pose = pPoseStack.last();
            Matrix4f matrix4f = posestack$pose.pose();
            VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucent( TEXTURE ));
            vertex(vertexconsumer, matrix4f, pPackedLight, 0.0F, 0f, 0, 1);
            vertex(vertexconsumer, matrix4f, pPackedLight, 1.0F, 0f, 1, 1);
            vertex(vertexconsumer, matrix4f, pPackedLight, 1.0F, 1f, 1, 0);
            vertex(vertexconsumer, matrix4f, pPackedLight, 0.0F, 1f, 0, 0);
            pPoseStack.popPose();
        }
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected int getBlockLightLevel(BombProjectile pEntity, BlockPos pPos) {
        return super.getBlockLightLevel(pEntity, pPos);
    }



    private static void vertex(VertexConsumer pConsumer, Matrix4f pPose, int pLightmapUV, float pX, float pY, int pU, int pV) {
        pConsumer.addVertex(pPose, pX - 0.5F, pY - 0.25F, 0.0F).setColor(255, 255, 255, 255).setUv((float)pU, (float)pV).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(pLightmapUV,pLightmapUV).setNormal( 1.0F, 1.0F, 1.0F);
    }
}
