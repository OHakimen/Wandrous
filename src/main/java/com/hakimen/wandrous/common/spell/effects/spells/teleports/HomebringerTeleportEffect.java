package com.hakimen.wandrous.common.spell.effects.spells.teleports;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class HomebringerTeleportEffect extends SpellEffect {


    public HomebringerTeleportEffect() {
        this.setKind(SpellEffect.SPELL);
        this.setStatus(new SpellStatus()
                .setManaDrain(50)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());
        if(!context.getHit().isEmpty()){

            if(context.getOriginalCaster() == null) return;
            Entity hit = context.getHit().get(context.getHit().size() - 1);
            Vec3 location = hit.getPosition(0);
            Vec3 originalCasterLocation = context.getOriginalCaster().getPosition(0);

            Vec3 offset = originalCasterLocation.subtract(location).normalize();

            if(context.getLevel() instanceof ServerLevel level){
                level.sendParticles(ParticleTypes.PORTAL, location.x, location.y + 1f, location.z, 20, 1f,1f,1f, 0f);
            }

            hit.teleportTo(originalCasterLocation.x - offset.x, originalCasterLocation.y - offset.y, originalCasterLocation.z - offset.z);
        }
    }
}

