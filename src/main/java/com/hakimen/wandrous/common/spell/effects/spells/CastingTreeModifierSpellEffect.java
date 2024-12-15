package com.hakimen.wandrous.common.spell.effects.spells;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStack;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.CastingUtils;
import com.hakimen.wandrous.common.utils.data.Node;
import org.apache.commons.lang3.function.TriFunction;

import java.util.List;

public class CastingTreeModifierSpellEffect extends SpellEffect {

    TriFunction<Node<SpellStack>, CastingUtils, List<SpellStack>, Node<SpellStack>> apply;
    public CastingTreeModifierSpellEffect(TriFunction<Node<SpellStack>, CastingUtils, List<SpellStack>, Node<SpellStack>> apply) {
        this.apply = apply;
        setKind(MODIFIER | GREEK_LETTER);
        setStatus(new SpellStatus()
                .setManaDrain(100)
        );
    }

    public CastingTreeModifierSpellEffect(int manaCost, int kinds,TriFunction<Node<SpellStack>, CastingUtils, List<SpellStack>, Node<SpellStack>> apply) {
        this.apply = apply;
        setKind(kinds);
        setStatus(new SpellStatus()
                .setManaDrain(manaCost)
        );
    }

    public Node<SpellStack> apply(Node<SpellStack> tree, CastingUtils castingUtils, List<SpellStack> itemHandler){
        return apply.apply(tree, castingUtils,itemHandler);
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(getStatus());
        for (Node<SpellStack> child : context.getNode().getChildren()) {
           if(child.getData() != null){
               child.getData().getEffect().cast(context.setNode(child));
           }
        }
    }
}
