package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.spell.mover.ISpellMover;
import com.hakimen.wandrous.common.utils.data.Node;

import java.util.function.Supplier;

public class MoverSpellEffect extends SpellEffect {
    Supplier<ISpellMover> mover;

    public MoverSpellEffect(Supplier<ISpellMover> mover) {
        this.mover = mover;
        this.setKind(MODIFIER);
        this.setStatus(new SpellStatus()
                .setManaDrain(80)
        );
    }

    public ISpellMover getMover() {
        return mover.get();
    }

    public MoverSpellEffect setMover(ISpellMover mover) {
        this.mover = () -> mover;
        return this;
    }

    @Override
    public void cast(SpellContext context) {
        for (Node<SpellEffect> child : context.getNode().getChildren()) {
            child.getData().cast(context.clone().setNode(child));
        }
    }
}
