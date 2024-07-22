package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellStack;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.data.Node;

public class SplitCastEffect extends MultiCastEffect {
    public SplitCastEffect(int castCount) {
        super(castCount);
        setKind(MODIFIER);
        setStatus(new SpellStatus()
                .setManaDrain(10 * castCount)
                .setSpread(1f));
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        int i = (context.getSplit() + castCount) % 2 == 0 ? 1 : 0;
        for (Node<SpellStack> child : context.getNode().getChildren()) {
            SpellContext spellContext = context.clone();
            spellContext.setSplit(spellContext.getSplit() + i++);
            child.getData().getEffect().cast(spellContext.setNode(child));
        }
    }
}
