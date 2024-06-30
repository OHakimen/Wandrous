package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.entity.projectiles.SonicBoomProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;

public class SonicBoomProjectileRenderer extends EntityRenderer<SonicBoomProjectile> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "");


    public SonicBoomProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(SonicBoomProjectile timerEntity) {
        return TEXTURE;
    }

    @Override
    public void render(SonicBoomProjectile pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        if(pEntity.tickCount % 2 == 0){
            pEntity.level().addParticle(ParticleTypes.SONIC_BOOM, pEntity.getX(), pEntity.getY(), pEntity.getZ(), 0.0D, 0.0D, 0.0D);
        }

        for (int i = 0; i < 4; i++) {
            pEntity.level().addParticle(ParticleTypes.GLOW, pEntity.getX(), pEntity.getY(), pEntity.getZ(), 0.0D, 0.0D, 0.0D);
        }

        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }
}
