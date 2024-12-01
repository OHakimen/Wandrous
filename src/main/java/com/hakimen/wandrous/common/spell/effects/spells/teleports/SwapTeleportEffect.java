package com.hakimen.wandrous.common.spell.effects.spells.teleports;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class SwapTeleportEffect extends SpellEffect {


    public SwapTeleportEffect() {
        this.setKind(SpellEffect.SPELL);
        this.setStatus(new SpellStatus()
                .setManaDrain(50)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());
        if(!context.getHit().isEmpty()){
            Entity hit = context.getHit().get(context.getHit().size() - 1);
            Vec3 swapPos = context.getOriginalCaster().getPosition(0);
            Vec3 hitPos = hit.getPosition(0);
            context.getOriginalCaster().teleportTo(hitPos.x, hitPos.y, hitPos.z);

            if(context.getLevel() instanceof ServerLevel level){
                level.sendParticles(ParticleTypes.PORTAL, hitPos.x, hitPos.y + 1f, hitPos.z, 20, 1f,1f,1f, 0f);
            }

            hit.teleportTo(swapPos.x, swapPos.y, swapPos.z);
        }
    }
}
