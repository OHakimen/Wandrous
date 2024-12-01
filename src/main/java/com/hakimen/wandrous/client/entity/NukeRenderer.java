package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.renderTypes.WandrousRenderTypes;
import com.hakimen.wandrous.client.utils.VertexProcessorBuilder;
import com.hakimen.wandrous.common.entity.static_spell.NukeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import org.joml.Quaternionf;

public class NukeRenderer extends EntityRenderer<NukeEntity> {


    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "textures/entity/nuke.png");

    public NukeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(NukeEntity timerEntity) {
        return TEXTURE;
    }

    @Override
    public void render(NukeEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        int maxTick = pEntity.getEntityData().get(NukeEntity.MAX_TICK_TIME);
        float tickPlusPartial = pPartialTick + pEntity.tickCount;

//        float x = (tickPlusPartial / (maxTick * 0.25f));
//        float scale = (float) ((tickPlusPartial / maxTick) > 0.25f ? (Math.sin(((tickPlusPartial - ( maxTick * 0.25f)) / maxTick * 1.30f) * Math.PI)) : (Math.sin(x * Math.PI)));

        float scale = Math.max((float) Math.sin((tickPlusPartial / maxTick) * Math.PI) * 2f,0);
        if(pEntity.tickCount >= maxTick){
           scale = 0;
        }

        pPoseStack.pushPose();
        pPoseStack.scale(scale,scale,scale);
        pPoseStack.translate(0.0D, 0.25f, 0.0D);

        pPoseStack.mulPose(new Quaternionf().rotateXYZ(0,-tickPlusPartial/64f,0));
        VertexConsumer vertexconsumer = pBuffer.getBuffer(WandrousRenderTypes.triangle(TEXTURE));
        VertexProcessorBuilder.drawSphere(vertexconsumer, pPoseStack, 10, 32, 32, 1f,1f,1f,1f,pPackedLight);
        pPoseStack.popPose();

        pPoseStack.pushPose();
        pPoseStack.scale(scale,scale,scale);
        pPoseStack.translate(0.0D, 0.25f, 0.0D);

        VertexConsumer outer = pBuffer.getBuffer(RenderType.debugFilledBox());
        VertexProcessorBuilder.drawSphere(outer, pPoseStack, 10.25f, 32, 32, 1f,0.25f,0,0.2f,pPackedLight);
        VertexProcessorBuilder.drawSphere(outer, pPoseStack, 10.5f, 32, 32, 1f,0.75f,0,0.4f,pPackedLight);

        pPoseStack.popPose();
    }


    @Override
    protected int getBlockLightLevel(NukeEntity pEntity, BlockPos pPos) {
        return 15;
    }

    @Override
    protected int getSkyLightLevel(NukeEntity pEntity, BlockPos pPos) {
        return 15;
    }

    @Override
    public boolean shouldRender(NukeEntity pLivingEntity, Frustum pCamera, double pCamX, double p_114494_, double pCamY) {
        return true;
    }
}
