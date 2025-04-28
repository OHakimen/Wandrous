package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.mover.IMoverRendererRegister;
import com.hakimen.wandrous.common.entity.projectiles.ChainShotProjectile;
import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.registers.ParticleRegister;
import com.hakimen.wandrous.common.api.mover.ISpellMover;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ChainShotProjectileRenderer extends EntityRenderer<ChainShotProjectile> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "");


    public ChainShotProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(ChainShotProjectile timerEntity) {
        return TEXTURE;
    }

    @Override
    public void render(ChainShotProjectile pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        List<ISpellMover> movers = SpellCastingProjectile.NBTToMoverList(pEntity.getEntityData().get(SpellCastingProjectile.MOVER_DATA));
        IMoverRendererRegister.render(movers, pEntity,pEntityYaw,pPartialTick,pPoseStack,pBuffer,pPackedLight);
        for (int i = 0; i < 4; i++) {
            Vec3 pos = pEntity.getPosition(pPartialTick);
            pEntity.level().addParticle(ParticleRegister.CHAIN_SHOT.get(), true, pos.x(), pos.y() + 0.25f, pos.z(), 0, 0, 0);
        }
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected int getBlockLightLevel(ChainShotProjectile pEntity, BlockPos pPos) {
        return 15;
    }

    @Override
    public boolean shouldRender(ChainShotProjectile pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return true;
    }
}
