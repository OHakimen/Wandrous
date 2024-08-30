package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;

import java.util.Random;

public class SpreadCastSpellEffect extends SpellEffect {

    float radius;

    public SpreadCastSpellEffect(float radius) {
        this.radius = radius;
        this.setKind(MODIFIER);
        this.setStatus(new SpellStatus()
                .setManaDrain((int)radius * 2)
                .setCastDelayMod(-0.25f)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());
        Random r = new Random();
        context.getNode().getChildren().forEach(child -> {
            context.setLocation(context.getLocation().add(r.nextDouble(-1,1) * radius,0,r.nextDouble(-1,1) * radius));
            context.setCastPositionModified(true);
            child.getData().getEffect().cast(context.setNode(child));
        });

    }
}
