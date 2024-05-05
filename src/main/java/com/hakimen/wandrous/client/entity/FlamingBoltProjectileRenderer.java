package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.entity.projectiles.FlamingBoltProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;

import java.util.Random;

public class FlamingBoltProjectileRenderer extends EntityRenderer<FlamingBoltProjectile> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Wandrous.MODID, "");


    public FlamingBoltProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(FlamingBoltProjectile timerEntity) {
        return TEXTURE;
    }

    @Override
    public void render(FlamingBoltProjectile pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        Random r = new Random();

        if(pEntity.tickCount >= 2){
            if(pEntity.tickCount % 4 == 0) {
                pEntity.level().addParticle(
                        ParticleTypes.LAVA,
                        true,
                        pEntity.getX() + r.nextFloat(-0.25f, 0.25f),
                        pEntity.getY() + r.nextFloat(-0.25f, 0.25f),
                        pEntity.getZ() + r.nextFloat(-0.25f, 0.25f),
                        0,0,0
                );
            }

            for (int i = 0; i < 2; i++) {
                pEntity.level().addParticle(
                        ParticleTypes.FLAME,
                        true,
                        pEntity.getX() + r.nextFloat(-0.25f, 0.25f),
                        pEntity.getY() + r.nextFloat(-0.25f, 0.25f),
                        pEntity.getZ() + r.nextFloat(-0.25f, 0.25f),
                        pEntity.getDeltaMovement().x / 4, pEntity.getDeltaMovement().y / 4, pEntity.getDeltaMovement().z / 4
                );

                float color = r.nextFloat(0,0.65f);

                pEntity.level().addParticle(
                        new DustParticleOptions(new Vector3f(color,color,color), 2),
                        true,
                        pEntity.getX() + r.nextFloat(-0.25f, 0.25f),
                        pEntity.getY() + r.nextFloat(-0.25f, 0.25f),
                        pEntity.getZ() + r.nextFloat(-0.25f, 0.25f),
                        pEntity.getDeltaMovement().x / 4, pEntity.getDeltaMovement().y / 4, pEntity.getDeltaMovement().z / 4
                );
            }
        }

        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }
}
