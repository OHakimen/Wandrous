package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellEffect;
import com.hakimen.wandrous.common.api.SpellStack;
import com.hakimen.wandrous.common.api.SpellStatus;
import com.hakimen.wandrous.common.utils.data.Node;

public class MultiCastEffect extends SpellEffect {
    int castCount;

    public MultiCastEffect(int castCount) {
        this.castCount = castCount;
        this.setStatus(new SpellStatus()
                .setManaDrain(20 * castCount));
    }

    public int getCastCount() {
        return castCount;
    }

    @Override
    public void cast(SpellContext context) {
        for (Node<SpellStack> child : context.getNode().getChildren()) {
            SpellContext nextContext = context.clone();
            child.getData().getEffect().cast(nextContext.setNode(child));
        }
    }
}
