package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellEffect;
import com.hakimen.wandrous.common.api.SpellStack;
import com.hakimen.wandrous.common.api.SpellStatus;
import com.hakimen.wandrous.common.api.mover.ISpellMover;
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
        for (Node<SpellStack> child : context.getNode().getChildren()) {
            child.getData().getEffect().cast(context.setNode(child));
        }
    }
}
