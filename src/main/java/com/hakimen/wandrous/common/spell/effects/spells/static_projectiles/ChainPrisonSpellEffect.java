package com.hakimen.wandrous.common.spell.effects.spells.static_projectiles;

import com.hakimen.wandrous.common.entity.static_spell.ChainPrisonEntity;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class ChainPrisonSpellEffect extends SpellEffect {
    public ChainPrisonSpellEffect() {
        setKind(SPELL);
        setStatus(
                new SpellStatus()
                        .setManaDrain(80)
                        .setLifeTime(200)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(getStatus());

        if(!context.getHit().isEmpty()){
            ChainPrisonEntity chainPrisonEntity = new ChainPrisonEntity(context.getLevel(), context.getStatus().getLifeTime(), context.getHit().get(context.getHit().size()-1));
            context.getLevel().playSound(null, context.getCaster().getOnPos(), SoundEvents.CHAIN_PLACE, SoundSource.PLAYERS, 2f,0.5f);
            context.getLevel().addFreshEntity(chainPrisonEntity);
        }
    }
}
