package com.hakimen.wandrous.common.spell.effects.modifiers.powerups;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStack;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.world.entity.LivingEntity;

public class HealthToPowerSpellEffect extends SpellEffect {
    public HealthToPowerSpellEffect() {
        setKind(MODIFIER);
        setStatus(
                new SpellStatus()
                        .setManaDrain(60)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        LivingEntity actualCaster = (LivingEntity)context.getOriginalCaster();

        float damage = actualCaster.getHealth() - 0.5f;
        context.getOriginalCaster().hurt(context.getLevel().damageSources().magic(), damage);
        context.getStatus().setDamageMod(damage/4f);
        for (Node<SpellStack> child : context.getNode().getChildren()) {
            child.getData().getEffect().cast(context.setNode(child));
        }
    }
}
