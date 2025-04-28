package com.hakimen.wandrous.client.mover;

import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.api.mover.ISpellMover;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class IMoverRendererRegister {
    private static final Map<ISpellMover, Supplier<IMoverRenderer>> RENDERERS = new HashMap<>();
    public static void register(ISpellMover spellMover, Supplier<IMoverRenderer> renderer) {
        RENDERERS.put(spellMover, renderer);
    }

    public static <T extends SpellCastingProjectile>void render(List<ISpellMover> movers, T projectile, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight){
        movers.forEach(mover -> {
            Supplier<IMoverRenderer> renderer = RENDERERS.getOrDefault(mover, null);
            if(renderer != null){
                renderer.get().render(projectile,pEntityYaw,pPartialTick,pPoseStack,pBuffer,pPackedLight);
            }
        });
    }
}
