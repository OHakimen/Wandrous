package com.hakimen.wandrous.common.spell.effects.spells.projectiles;

import com.hakimen.wandrous.common.entity.projectiles.SonicBoomProjectile;
import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellStatus;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SonicBoomSpellEffect extends ProjectileSpellEffect {

    public SonicBoomSpellEffect(int kind) {
        this.setKind(kind);
        this.setStatus(new SpellStatus()
                .setDamage(8)
                .setManaDrain(60)
                .setSpeed(1f)
                .setDamage(8f)
                .setSpread(0.01f)
                .setLifeTime(20)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        Level level = context.getLevel();
        Vec3 location = context.getLocation();
        SonicBoomProjectile sonicBoomProjectile = new SonicBoomProjectile(location.x, location.y, location.z, level, context);
        level.playSound(null, context.getCaster().getOnPos(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS, 1f,1f);
        shootProjectile(sonicBoomProjectile, context);
        level.addFreshEntity(sonicBoomProjectile);
    }
}
