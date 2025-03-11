package com.hakimen.wandrous.client.mover;

import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;

public class GuideMoverRenderer implements IMoverRenderer{
    @Override
    public <T extends SpellCastingProjectile> void render(T pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.tickCount > 2) {
            Vec3 pos = pEntity.getPosition(pPartialTick);
            pEntity.level().addParticle(
                    ParticleTypes.WITCH,
                    true,
                    pos.x(),
                    pos.y() + 0.25,
                    pos.z(),
                    0, 0, 0
            );
        }
    }
}
