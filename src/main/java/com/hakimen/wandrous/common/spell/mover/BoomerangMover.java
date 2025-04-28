package com.hakimen.wandrous.common.spell.mover;

import com.hakimen.wandrous.common.api.mover.ISpellMover;
import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.api.SpellContext;
import net.minecraft.world.entity.Entity;

public class BoomerangMover implements ISpellMover {
    @Override
    public void move(SpellContext context, SpellCastingProjectile projectile) {
        if(projectile.tickCount >= projectile.getMaxTicks() / 3f){
            Entity original = context.getOriginalCaster();
            projectile.addDeltaMovement(original.getEyePosition().subtract(projectile.getEyePosition()).normalize().scale(projectile.isNoGravity() ? 0.125f : 0.35f));
        }
    }
}
