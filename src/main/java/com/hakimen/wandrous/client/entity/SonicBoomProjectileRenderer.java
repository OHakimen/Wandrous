package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.mover.IMoverRendererRegister;
import com.hakimen.wandrous.common.entity.projectiles.SonicBoomProjectile;
import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.registers.ParticleRegister;
import com.hakimen.wandrous.common.api.mover.ISpellMover;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import java.util.List;

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
        List<ISpellMover> movers = SpellCastingProjectile.NBTToMoverList(pEntity.getEntityData().get(SpellCastingProjectile.MOVER_DATA));
        IMoverRendererRegister.render(movers, pEntity,pEntityYaw,pPartialTick,pPoseStack,pBuffer,pPackedLight);
        Vec3 pos = pEntity.getPosition(0);
        if(pEntity.tickCount % 2 == 0){
            pEntity.level().addParticle(ParticleTypes.SONIC_BOOM, pos.x(), pos.y(), pos.z(), 0.0D, 0.0D, 0.0D);
        }

        pos = pEntity.getPosition(pPartialTick);
        for (int i = 0; i < 4; i++) {
            pEntity.level().addParticle(ParticleRegister.CHAIN_SHOT.get(), pos.x(), pos.y(), pos.z(), 0.0D, 0.0D, 0.0D);
        }

        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public boolean shouldRender(SonicBoomProjectile pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return true;
    }
}
