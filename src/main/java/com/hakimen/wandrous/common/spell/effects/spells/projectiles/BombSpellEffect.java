package com.hakimen.wandrous.common.spell.effects.spells.projectiles;

import com.hakimen.wandrous.common.entity.projectiles.BlackHoleProjectile;
import com.hakimen.wandrous.common.entity.projectiles.BombProjectile;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BombSpellEffect extends ProjectileSpellEffect {

    public BombSpellEffect() {
        this.setKind(SPELL);
        this.setStatus(new SpellStatus()
                .setDamage(5)
                .setManaDrain(30)
                .setSpeed(0.5f)
                .setSpread(0)
                .setLifeTime(100)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        Level level = context.getLevel();
        Vec3 location = context.getLocation();
        BombProjectile bomb = new BombProjectile(location.x, location.y, location.z,level, context.clone());
        shootProjectile(bomb, context);
        level.addFreshEntity(bomb);
    }
}
