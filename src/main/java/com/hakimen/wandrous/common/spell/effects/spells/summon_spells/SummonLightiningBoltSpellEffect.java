package com.hakimen.wandrous.common.spell.effects.spells.summon_spells;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;

public class SummonLightiningBoltSpellEffect extends SpellEffect {

    public SummonLightiningBoltSpellEffect() {
        this.setKind(SpellEffect.SPELL);
        this.setStatus(new SpellStatus()
                .setManaDrain(100)
                .setDamage(5)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(getStatus());
        Level level = context.getLevel();

        LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
        bolt.setDamage(context.getStatus().getDamage());
        bolt.setPos(context.getLocation());
        level.addFreshEntity(bolt);
    }
}
