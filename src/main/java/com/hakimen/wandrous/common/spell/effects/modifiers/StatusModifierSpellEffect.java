package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStack;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.data.Node;

public class StatusModifierSpellEffect extends SpellEffect {
    public StatusModifierSpellEffect(SpellStatus status) {
        setKind(MODIFIER);
        setStatus(status);
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());
        for (Node<SpellStack> child : context.getNode().getChildren()) {
            child.getData().getEffect().cast(context.setNode(child));
        }
    }
}
