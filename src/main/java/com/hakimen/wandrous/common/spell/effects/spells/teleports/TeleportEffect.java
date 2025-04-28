package com.hakimen.wandrous.common.spell.effects.spells.teleports;

import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellEffect;
import com.hakimen.wandrous.common.api.SpellStatus;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

public class TeleportEffect extends SpellEffect {


    public TeleportEffect() {
        this.setKind(SpellEffect.SPELL);
        this.setStatus(new SpellStatus()
                .setManaDrain(50)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());
        Vec3 location = context.getLocation();
        if(context.getLevel() instanceof ServerLevel level){
            Vec3 loc = context.getOriginalCaster().getPosition(0);
            level.sendParticles(ParticleTypes.PORTAL, loc.x, loc.y + 1f, loc.z, 20, 1f,1f,1f, 0f);
        }
        context.getOriginalCaster().teleportTo(location.x, location.y, location.z);

    }
}
