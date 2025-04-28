package com.hakimen.wandrous.common.spell.effects.spells.projectiles;

import com.hakimen.wandrous.common.entity.projectiles.ChainShotProjectile;
import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellEffect;
import com.hakimen.wandrous.common.api.SpellStatus;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import static com.hakimen.wandrous.common.spell.effects.spells.projectiles.ProjectileSpellEffect.shootProjectile;

public class ChainShotSpellEffect extends SpellEffect {

    public ChainShotSpellEffect(int kind) {
        setKind(kind);
        setStatus(new SpellStatus()
                .setDamage(4)
                .setSpeed(0.2f)
                .setLifeTime(100)
                .setSpread(0.3f)
                .setRadius(8));
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        Level level = context.getLevel();
        Vec3 location = context.getLocation();
        ChainShotProjectile chainShot = new ChainShotProjectile(location.x, location.y, location.z,level, context);
        chainShot.setHitCount(5);
        shootProjectile(chainShot, context);
        level.addFreshEntity(chainShot);
    }
}
