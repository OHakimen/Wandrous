package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.entity.static_spell.NukeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class NukeRenderer extends EntityRenderer<NukeEntity> {


    private static final ResourceLocation TEXTURE = new ResourceLocation(Wandrous.MODID, "");

    public NukeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(NukeEntity timerEntity) {
        return TEXTURE;
    }

    @Override
    public void render(NukeEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
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
