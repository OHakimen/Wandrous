package com.hakimen.wandrous.common.spell.effects.spells.summon_spells;

import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellEffect;
import com.hakimen.wandrous.common.api.SpellStatus;
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
        context.mergeStatus(getStatus());
        Level level = context.getLevel();

        Entity cast = entity.create(level);

        cast.setPos(context.getLocation());
        level.addFreshEntity(cast);
    }
}
