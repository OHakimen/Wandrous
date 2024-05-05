package com.hakimen.wandrous.common.spell.effects.modifiers.location;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

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
        SpellContext clone = context.clone();

        float castDistance = clone.getStatus().getRadius();

        Vec3 location = context.getLocation();
        Vec3 lookAngle = context.getCaster().getLookAngle();

        Vec3 newLocation = new Vec3(location.x + castDistance * lookAngle.x, location.y + castDistance * lookAngle.y, location.z + castDistance * lookAngle.z);

        clone.setCastPositionModified(true);
        clone.setLocation(newLocation);

        for (Node<SpellEffect> child : clone.getNode().getChildren()) {
            child.getData().cast(clone.setNode(child));
        }
    }
}
