package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.data.Node;

public class SplitCastEffect extends MulticastEffect{
    public SplitCastEffect(int castCount) {
        super(castCount);
        setKind(MODIFIER);
        setStatus(new SpellStatus()
                .setManaDrain(10 * castCount)
                .setSpreadMod(0.1f));
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        int i = (context.getSplit() + castCount) % 2 == 0 ? 1 : 0;
        for (Node<SpellEffect> child : context.getNode().getChildren()) {
            SpellContext spellContext = context.clone();
            spellContext.setSplit(spellContext.getSplit() + i++);
            child.getData().cast(spellContext.setNode(child));
        }
    }
}
