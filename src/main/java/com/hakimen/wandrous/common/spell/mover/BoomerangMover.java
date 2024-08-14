package com.hakimen.wandrous.common.spell.mover;

import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.spell.SpellContext;
import net.minecraft.world.entity.Entity;

public class BoomerangMover implements ISpellMover {
    @Override
    public void move(SpellContext context, SpellCastingProjectile projectile) {
        if(projectile.tickCount >= projectile.getMaxTicks() / 2f){
            Entity original = context.getOriginalCaster();
            projectile.setDeltaMovement(original.getEyePosition().subtract(projectile.getEyePosition()).normalize().scale(context.getStatus().getSpeed()));
        }
    }
}
