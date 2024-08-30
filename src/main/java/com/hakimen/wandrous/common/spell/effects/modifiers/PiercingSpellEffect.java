package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;

public class PiercingSpellEffect extends SpellEffect {
    public PiercingSpellEffect() {
        setKind(MODIFIER);
        setStatus(new SpellStatus().setManaDrain(50));
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());
        context.setPiercing(true);
        context.getNode().getChildren().forEach(child -> child.getData().getEffect().cast(context.setNode(child)));
    }
}
