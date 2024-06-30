package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;

public class StatusModifierSpellEffect extends SpellEffect {
    public StatusModifierSpellEffect(SpellStatus status) {
        setKind(MODIFIER);
        setStatus(status);
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());
        context.getNode().getChildren().forEach((child)-> {
            child.getData().cast(context.setNode(child));
        });
    }
}
