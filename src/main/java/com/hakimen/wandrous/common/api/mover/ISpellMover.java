package com.hakimen.wandrous.common.api.mover;

import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.api.SpellContext;
/**
 * <h1>Spell Mover</h1>
 * This interface defines behaviour for spell movers.
 * <br><br>
 * The move method is called every tick on a spell that has a mover attached to it, movers can only be attached to projectiles based spells.
 * Movers allow for custom movement behaviour, for example.
 * <br><br>
 * {@link com.hakimen.wandrous.common.spell.mover.HomingMover} is defined as a Spell Mover that looks for a entity close by and marks it as its target!
 */
public interface ISpellMover {
    void move(SpellContext context, SpellCastingProjectile projectile);
}
