package com.hakimen.wandrous.common.spell.effects.spells.summon_spells;

import com.hakimen.wandrous.common.registers.EffectRegister;
import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellEffect;
import com.hakimen.wandrous.common.api.SpellStatus;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.Level;

import java.util.List;

public class SummonBeeSwarmEffect extends SpellEffect {
    public SummonBeeSwarmEffect() {
        setKind(SPELL);
        setStatus(new SpellStatus()
                .setManaDrain(50)
                .setLifeTime(20 * 15)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(getStatus());
        Level level = context.getLevel();

        List<LivingEntity> hit = context.getHit().isEmpty() ? null : context.getHit();
        for (int i = 0; i < 4; i++) {
            Bee cast = new Bee(EntityType.BEE, level);
            if(hit != null){
                cast.setTarget(hit.getFirst());
            }
            cast.addEffect(new MobEffectInstance(EffectRegister.SUMMONED_ENTITY_EFFECT, context.getStatus().getLifeTime()));
            cast.setPos(context.getLocation());
            level.addFreshEntity(cast);
        }
    }
}
