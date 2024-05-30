package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.entity.projectiles.ChainShotProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;

public class ChainShotProjectileRenderer extends EntityRenderer<ChainShotProjectile> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Wandrous.MODID, "");


    public ChainShotProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(ChainShotProjectile timerEntity) {
        return TEXTURE;
    }

    @Override
    public void render(ChainShotProjectile pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        for (int i = 0; i < 2; i++) {
            pEntity.level().addParticle(ParticleTypes.GLOW, pEntity.getX() - 0.5f, pEntity.getY() - 0.5f, pEntity.getZ() - 0.5f, 0, 0, 0);
        }
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected int getBlockLightLevel(ChainShotProjectile pEntity, BlockPos pPos) {
        return 15;
    }

}
