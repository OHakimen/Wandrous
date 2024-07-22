package com.hakimen.wandrous.common.spell.effects.spells.projectiles;

import com.hakimen.wandrous.common.entity.projectiles.GlimmeringBoltProjectile;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GlimmeringBoltSpellEffect extends ProjectileSpellEffect {

    public GlimmeringBoltSpellEffect(int kind) {
        this.setKind(kind);
        this.setStatus(new SpellStatus()
                .setDamage(2f)
                .setManaDrain(20)
                .setSpeed(1.5f)
                .setSpread(0.2f)
                .setLifeTime(100)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        Level level = context.getLevel();
        Vec3 location = context.getLocation();
        GlimmeringBoltProjectile glimmeringBoltProjectile = new GlimmeringBoltProjectile(location.x, location.y, location.z, level, context);
        level.playSound(null, context.getCaster().getOnPos(), SoundEvents.ALLAY_ITEM_TAKEN, SoundSource.PLAYERS, 1,1f);
        shootProjectile(glimmeringBoltProjectile, context);
        level.addFreshEntity(glimmeringBoltProjectile);
    }
}
