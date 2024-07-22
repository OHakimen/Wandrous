package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStack;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.data.Node;

public class DivideBySpellEffect extends SpellEffect {
    int castCount;

    public DivideBySpellEffect(int castCount) {
        this.castCount = castCount;
        this.setKind(MODIFIER);
        this.setStatus(new SpellStatus()
                .setManaDrain(castCount * 10)
                .setDamageMod(-(1f/castCount)));
    }

    public int getCastCount() {
        return castCount;
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());
        for (int i = 0; i < castCount; i++) {
            for (Node<SpellStack> child : context.getNode().getChildren()) {
                child.getData().getEffect().cast(context.clone().setNode(child));
            }
        }
    }
}
