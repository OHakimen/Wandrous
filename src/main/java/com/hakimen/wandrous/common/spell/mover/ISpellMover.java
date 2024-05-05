package com.hakimen.wandrous.common.spell.mover;

import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.spell.SpellContext;
import net.minecraft.world.entity.projectile.Projectile;

public interface ISpellMover {
    void move(SpellContext context, SpellCastingProjectile projectile);
}
