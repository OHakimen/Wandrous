package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.world.phys.Vec3;

public class LineCastEffect extends MultiCastEffect {
    public LineCastEffect(int count) {
        super(count);
        setKind(MODIFIER);
        setStatus(
                new SpellStatus()
                        .setManaDrain(40)
                        .setRadius(2)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        float castDistance = context.getStatus().getRadius();

        Vec3 location = context.getLocation();

        for (Node<SpellEffect> child : context.getNode().getChildren()) {
            SpellContext clone = context.clone();
            castDistance += context.getStatus().getRadius();
            Vec3 lookAngle = context.getCaster().getLookAngle();

            Vec3 newLocation = new Vec3(location.x + castDistance * lookAngle.x, location.y + castDistance * lookAngle.y, location.z + castDistance * lookAngle.z);
            clone.setCastPositionModified(true);

            clone.setCastPositionModified(true);
            clone.setLocation(newLocation);
            location = newLocation;
            child.getData().cast(clone.setNode(child));
        }
    }
}
