package com.hakimen.wandrous.common.spell.effects.spells.summon_spells;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;

public class SummonBeeEffect extends SpellEffect {
    public SummonBeeEffect() {
        setKind(SPELL);
        setStatus(new SpellStatus()
                .setManaDrain(50)
        );
    }

    @Override
    public void cast(SpellContext context) {
        Level level = context.getLevel();

        Bee bee = new Bee(EntityType.BEE, level);
        bee.setPos(context.getLocation());

        if (level.addFreshEntity(bee)) {
            bee.setAggressive(true);
            bee.setRemainingPersistentAngerTime(5000);
        }

    }
}
