package com.hakimen.wandrous.common.spell.effects.spells.static_projectiles;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ExplosionSpellEffect extends SpellEffect {
    public ExplosionSpellEffect(float strength) {
        this.setKind(SpellEffect.SPELL);
        this.setStatus(new SpellStatus()
                .setDamage(strength)
                .setManaDrain(15 * (int) strength)
                .setCritChance(0.00f)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        Level level = context.getLevel();
        Vec3 location = context.getLocation();

        level.explode(null, location.x, location.y, location.z, context.getStatus().getDamage(), Level.ExplosionInteraction.MOB);
    }
}
