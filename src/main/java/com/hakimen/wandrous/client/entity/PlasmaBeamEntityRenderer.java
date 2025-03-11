package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.common.entity.static_spell.PlasmaBeamEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class PlasmaBeamEntityRenderer extends EntityRenderer<PlasmaBeamEntity> {
    public PlasmaBeamEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(PlasmaBeamEntity pEntity) {
        return ResourceLocation.withDefaultNamespace("empty");
    }

    @Override
    public void render(PlasmaBeamEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }
}
