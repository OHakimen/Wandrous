package com.hakimen.wandrous.common.spell.effects.spells.static_projectiles;

import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.entity.static_spell.PlasmaBeamEntity;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.spell.effects.spells.projectiles.ProjectileSpellEffect;
import com.hakimen.wandrous.common.spell.mover.ISpellMover;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public class PlasmaBeamSpellEffect extends SpellEffect {

    public PlasmaBeamSpellEffect() {
        setKind(SPELL);
        setStatus(new SpellStatus()
                .setDamage(4)
                .setManaDrain(100)
                .setRadius(10)
                .setLifeTime(5 * 20)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        Vec3 location = context.getLocation();
        Level level = context.getLevel();

        ISpellMover[] movers = SpellCastingProjectile.getMovers(context.getNode()).toArray(ISpellMover[]::new);
        PlasmaBeamEntity beam = new PlasmaBeamEntity(location.subtract(0,+.25,0), level, context, movers);
        ProjectileSpellEffect.shootProjectile(beam, context);
        level.addFreshEntity(beam);
    }
}
