package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.utils.VertexProcessorBuilder;
import com.hakimen.wandrous.common.entity.static_spell.ChainPrisonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;

public class ChainPrisonRenderer extends EntityRenderer<ChainPrisonEntity> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "textures/entity/chain.png");

    public ChainPrisonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(ChainPrisonEntity pEntity) {
        return TEXTURE;
    }

    @Override
    public void render(ChainPrisonEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        float fade = 1f;

        if(pEntity.tickCount < pEntity.getEntityData().get(ChainPrisonEntity.MAX_TICK_TIME) * 0.1){
            fade = (float) Math.log((pEntity.tickCount+ pPartialTick)  / 2f);
        }else if(pEntity.tickCount > pEntity.getEntityData().get(ChainPrisonEntity.MAX_TICK_TIME) * 0.95) {
            fade = (float) -Math.log(((pEntity.tickCount + pPartialTick) - pEntity.getEntityData().get(ChainPrisonEntity.MAX_TICK_TIME) * 0.95f) / 2f);
        }

        fade = Mth.clamp(fade, 0,1f);

        for (int i = 0; i < 8; i++) {
            pPoseStack.pushPose();
            pPoseStack.translate(0.0D, (pEntity.getEntityData().get(ChainPrisonEntity.TARGET_BB_HEIGHT)/2f) - (pEntity.getEntityData().get(ChainPrisonEntity.TARGET_BB_HEIGHT)/4f), 0.0D);
            pPoseStack.mulPose(new Quaternionf().rotateXYZ(0, (float) (Math.PI/4f) * i, (float) (Math.PI/4f)));
            pPoseStack.translate(0.0D, 0.5D, 0.0D);
            VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucent(TEXTURE));
            VertexProcessorBuilder.drawQuadTinted(vertexconsumer, pPoseStack, pPackedLight,0,1,1f,1f,1f,fade);
            pPoseStack.popPose();
        }

        for (int i = 0; i < 8; i++) {
            pPoseStack.pushPose();
            pPoseStack.translate(0.0D, 0.0D, 0.0D);
            pPoseStack.mulPose(new Quaternionf().rotateXYZ(0, (float) (Math.PI/4f) * i, (float) (Math.PI/4f)));
            pPoseStack.translate(0.0D, -1.5f, 0.0D);

            VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityTranslucent(TEXTURE));
            VertexProcessorBuilder.drawQuadTinted(vertexconsumer, pPoseStack, pPackedLight,0,1,1f,1f,1f,fade);

            pPoseStack.popPose();
        }

        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected int getBlockLightLevel(ChainPrisonEntity pEntity, BlockPos pPos) {
        return 15;
    }

}
