package com.hakimen.wandrous.common.spell.effects.modifiers.location;

import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellEffect;
import com.hakimen.wandrous.common.api.SpellStack;
import com.hakimen.wandrous.common.api.SpellStatus;
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

        context.setLocation(newLocation);
        context.setCastPositionModified(true);

        for (Node<SpellStack> child : context.getNode().getChildren()) {
            child.getData().getEffect().cast(context.setNode(child));
        }
    }
}
