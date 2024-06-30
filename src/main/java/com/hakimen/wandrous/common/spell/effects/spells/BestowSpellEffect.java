package com.hakimen.wandrous.common.spell.effects.spells;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class BestowSpellEffect extends SpellEffect {

    Holder<MobEffect> instance;

    public BestowSpellEffect(Holder<MobEffect>  effect) {
        setKind(SPELL);
        setStatus(new SpellStatus()
                .setLifeTime(30 * 20)
                .setDamage(1)
        );


        instance = effect;
    }


    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        if(context.getHit().isEmpty() && context.getCaster() instanceof LivingEntity caster) {
            caster.addEffect(new MobEffectInstance(instance, context.getStatus().getLifeTime(), (int)context.getStatus().getDamage() - 1));
        }else{
            for (LivingEntity livingEntity : context.getHit()) {
                livingEntity.addEffect(new MobEffectInstance(instance, context.getStatus().getLifeTime(), (int)context.getStatus().getDamage() - 1));
            }
        }
    }
}
