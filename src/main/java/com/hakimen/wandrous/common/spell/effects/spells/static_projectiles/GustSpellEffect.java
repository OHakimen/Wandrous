package com.hakimen.wandrous.common.spell.effects.spells.static_projectiles;

import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellEffect;
import com.hakimen.wandrous.common.api.SpellStatus;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SimpleExplosionDamageCalculator;

import java.util.Optional;
import java.util.function.Function;

public class GustSpellEffect extends SpellEffect {

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
                        new SimpleExplosionDamageCalculator(
                                true, false, Optional.of(context.getStatus().getRadius()/4f), BuiltInRegistries.BLOCK.getTag(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())
                        ){
                            @Override
                            public boolean shouldDamageEntity(Explosion p_346248_, Entity pEntity) {
                                return pEntity instanceof FallingBlockEntity;
                            }
                        },
                        context.getLocation().x(),
                        context.getLocation().y(),
                        context.getLocation().z(),
                        context.getStatus().getRadius(),
                        false,
                        Level.ExplosionInteraction.NONE,
                        ParticleTypes.GUST_EMITTER_SMALL,
                        ParticleTypes.GUST_EMITTER_LARGE,
                        SoundEvents.WIND_CHARGE_BURST
                );

    }
}
