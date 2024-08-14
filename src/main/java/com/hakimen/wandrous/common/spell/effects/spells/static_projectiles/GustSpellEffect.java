package com.hakimen.wandrous.common.spell.effects.spells.static_projectiles;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;

import java.util.Optional;
import java.util.function.Function;

public class GustSpellEffect extends SpellEffect {

    private static final ExplosionDamageCalculator EXPLOSION_DAMAGE_CALCULATOR = new SimpleExplosionDamageCalculator(
            true, false, Optional.of(1.22F), BuiltInRegistries.BLOCK.getTag(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())
    );

    public GustSpellEffect() {
        setKind(SPELL);
        setStatus(new SpellStatus()
                .setManaDrain(30)
                .setRadius(4));
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(getStatus());

        context.getLevel()
                .explode(context.getCaster(),
                        null,
                        EXPLOSION_DAMAGE_CALCULATOR,
                        context.getLocation().x(),
                        context.getLocation().y(),
                        context.getLocation().z(),
                        context.getStatus().getRadius(),
                        false,
                        Level.ExplosionInteraction.NONE,
                        ParticleTypes.GUST,
                        ParticleTypes.GUST_EMITTER_LARGE,
                        SoundEvents.WIND_CHARGE_BURST
                );

    }
}
