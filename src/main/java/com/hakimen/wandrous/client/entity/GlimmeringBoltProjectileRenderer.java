package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.mover.IMoverRendererRegister;
import com.hakimen.wandrous.common.entity.projectiles.GlimmeringBoltProjectile;
import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.registers.ParticleRegister;
import com.hakimen.wandrous.common.spell.mover.ISpellMover;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class GlimmeringBoltProjectileRenderer extends EntityRenderer<GlimmeringBoltProjectile> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "");

    public GlimmeringBoltProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(GlimmeringBoltProjectile timerEntity) {
        return TEXTURE;
    }

    @Override
    public void render(GlimmeringBoltProjectile pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        List<ISpellMover> movers = SpellCastingProjectile.NBTToMoverList(pEntity.getEntityData().get(SpellCastingProjectile.MOVER_DATA));
        IMoverRendererRegister.render(movers, pEntity,pEntityYaw,pPartialTick,pPoseStack,pBuffer,pPackedLight);
       if(pEntity.tickCount >=1) {
           for (int i = 0; i < 4; i++) {
               Vec3 pos = pEntity.getPosition(pPartialTick);
               pEntity.level().addParticle(
                       ParticleRegister.GLIMMERING_BOLT.get(),
                       pos.x(),
                       pos.y(),
                       pos.z(),
                       0,0,0
               );
           }
       }


        //super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public boolean shouldRender(GlimmeringBoltProjectile pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return true;
    }
}
