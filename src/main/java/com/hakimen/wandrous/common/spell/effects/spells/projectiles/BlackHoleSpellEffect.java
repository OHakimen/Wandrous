package com.hakimen.wandrous.common.spell.effects.spells.projectiles;

import com.hakimen.wandrous.common.entity.projectiles.BlackHoleProjectile;
import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellStatus;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BlackHoleSpellEffect extends ProjectileSpellEffect {

    public BlackHoleSpellEffect(int kind) {
        this.setKind(kind);
        this.setStatus(new SpellStatus()
                .setDamage(0)
                .setRadius(2)
                .setManaDrain(80)
                .setSpeed(0.15f)
                .setSpread(0.01f)
                .setLifeTime(100)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        Level level = context.getLevel();
        Vec3 location = context.getLocation();
        BlackHoleProjectile blackHoleProjectile = new BlackHoleProjectile(location.x, location.y, location.z, level, context);
        shootProjectile(blackHoleProjectile, context);
        level.addFreshEntity(blackHoleProjectile);
    }
}
