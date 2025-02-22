package com.hakimen.wandrous.client.mover;

import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;

public interface IMoverRenderer {
    <T extends SpellCastingProjectile> void render(T projectile, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight);
}
