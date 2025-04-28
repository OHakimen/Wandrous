package com.hakimen.wandrous.common.spell.mover;

import com.hakimen.wandrous.common.api.mover.ISpellMover;
import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.api.SpellContext;

public class GuideMover implements ISpellMover {
    @Override
    public void move(SpellContext context, SpellCastingProjectile projectile) {
        if(projectile.tickCount > 2){
            projectile.setNoGravity(true);
            projectile.setDeltaMovement(context.getOriginalCaster().getViewVector(0).normalize().scale(context.getStatus().getSpeed()));
        }
    }
}
