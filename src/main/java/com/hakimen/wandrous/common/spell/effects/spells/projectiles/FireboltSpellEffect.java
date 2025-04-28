package com.hakimen.wandrous.common.spell.effects.spells.projectiles;

import com.hakimen.wandrous.common.entity.projectiles.FlamingBoltProjectile;
import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellStatus;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FireboltSpellEffect extends ProjectileSpellEffect {

    public FireboltSpellEffect(int kind) {
        this.setKind(kind);
        this.setStatus(new SpellStatus()
                .setDamage(4f)
                .setManaDrain(20)
                .setSpeed(1.5f)
                .setSpread(0.5f)
                .setLifeTime(50)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        Level level = context.getLevel();
        Vec3 location = context.getLocation();
        FlamingBoltProjectile fireball = new FlamingBoltProjectile(location.x, location.y, location.z,level, context.clone());
        level.playSound(null, context.getCaster().getOnPos(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1f,1f);
        shootProjectile(fireball, context);
        level.addFreshEntity(fireball);
    }
}
