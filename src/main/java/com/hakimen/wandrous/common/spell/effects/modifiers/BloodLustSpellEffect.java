package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellEffect;
import com.hakimen.wandrous.common.api.SpellStatus;

public class BloodLustSpellEffect extends SpellEffect {
    public BloodLustSpellEffect() {
        setKind(MODIFIER);
        setStatus(
                new SpellStatus().setManaDrain(50)
                        .setDamageMod(2f)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());
        context.setCanHitCaster(true);
        context.getNode().getChildren().forEach(child -> child.getData().getEffect().cast(context.setNode(child)));
    }
}
