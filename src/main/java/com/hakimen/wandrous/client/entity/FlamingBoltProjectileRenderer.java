package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.entity.projectiles.FlamingBoltProjectile;
import com.hakimen.wandrous.common.registers.ParticleRegister;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class FlamingBoltProjectileRenderer extends EntityRenderer<FlamingBoltProjectile> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "textures/entity/nuke.png");


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
        Vec3 pos = pEntity.getPosition(pPartialTick);
        for (int i = 0; i < 16; i++) {
            float scale = (0.25f * ((1 + i))) / 6f;

            pEntity.level().addParticle(
                    ParticleRegister.FIERY_PARTICLES.get(),
                    true,
                    pos.x() + r.nextFloat(-scale,scale),
                    pos.y() + r.nextFloat(-scale,scale),
                    pos.z() + r.nextFloat(-scale,scale),
                    0,0,0
            );
        }

        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public boolean shouldRender(FlamingBoltProjectile pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return true;
    }
}
