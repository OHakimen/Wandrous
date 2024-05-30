package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.entity.projectiles.GlimmeringBoltProjectile;
import com.hakimen.wandrous.common.registers.ParticleRegister;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class GlimmeringBoltProjectileRenderer extends EntityRenderer<GlimmeringBoltProjectile> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Wandrous.MODID, "");

    public GlimmeringBoltProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(GlimmeringBoltProjectile timerEntity) {
        return TEXTURE;
    }

    @Override
    public void render(GlimmeringBoltProjectile pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
       if(pEntity.tickCount >=1) {
           for (int i = 0; i < 2; i++) {
               pEntity.level().addParticle(
                       ParticleRegister.GLIMMERING_BOLT.get(),
                       pEntity.getX(),
                       pEntity.getY(),
                       pEntity.getZ(),
                       0,0,0
               );
           }
       }


        //super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }
}
