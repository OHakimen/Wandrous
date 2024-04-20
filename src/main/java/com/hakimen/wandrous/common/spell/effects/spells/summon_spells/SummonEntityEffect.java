package com.hakimen.wandrous.common.spell.effects.spells.summon_spells;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class SummonEntityEffect extends SpellEffect {

    EntityType<?> entity;
    public SummonEntityEffect(EntityType<?> entity, int cost) {
        this.entity = entity;
        this.setKind(SpellEffect.SPELL);
        this.setStatus(new SpellStatus()
                .setManaDrain(cost)
        );
    }

    @Override
    public void cast(SpellContext context) {

        Level level = context.getLevel();

        Entity cast = entity.create(level);

        cast.setPos(context.getLocation());
        level.addFreshEntity(cast);
    }
}
