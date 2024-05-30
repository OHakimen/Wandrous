package com.hakimen.wandrous.common.spell.effects.spells.teleports;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
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
        context.getOriginalCaster().teleportTo(location.x, location.y, location.z);
    }
}
