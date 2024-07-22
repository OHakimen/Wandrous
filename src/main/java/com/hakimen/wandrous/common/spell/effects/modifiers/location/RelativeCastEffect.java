package com.hakimen.wandrous.common.spell.effects.modifiers.location;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStack;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.world.phys.Vec3;

public class RelativeCastEffect extends SpellEffect {
    public RelativeCastEffect() {
        setKind(MODIFIER);
        setStatus(
                new SpellStatus()
                        .setManaDrain(40)
                        .setRadius(4)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        float castDistance = context.getStatus().getRadius();

        Vec3 location = context.getLocation();
        Vec3 lookAngle = context.getCaster().getLookAngle();

        Vec3 newLocation = new Vec3(location.x + castDistance * lookAngle.x, location.y + castDistance * lookAngle.y, location.z + castDistance * lookAngle.z);

        context.setCastPositionModified(true);
        context.setLocation(newLocation);

        for (Node<SpellStack> child : context.getNode().getChildren()) {
            child.getData().getEffect().cast(context.setNode(child));
        }
    }
}
