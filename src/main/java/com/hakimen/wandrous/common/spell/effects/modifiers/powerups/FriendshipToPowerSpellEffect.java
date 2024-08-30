package com.hakimen.wandrous.common.spell.effects.modifiers.powerups;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStack;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.phys.AABB;

public class FriendshipToPowerSpellEffect extends SpellEffect {
    public FriendshipToPowerSpellEffect() {
        setKind(MODIFIER);
        setStatus(
                new SpellStatus()
                        .setManaDrain(60)
                        .setRadius(8)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        LivingEntity actualCaster = (LivingEntity)context.getOriginalCaster();

        float radius = context.getStatus().getRadius();

        float owned = context.getLevel().getEntities(actualCaster, AABB.ofSize(context.getLocation(), radius,radius,radius)).stream().filter(
                entity -> entity instanceof OwnableEntity && ((OwnableEntity)entity).getOwner() == actualCaster
        ).count();

        context.getStatus().setDamageMod(owned/4f);
        for (Node<SpellStack> child : context.getNode().getChildren()) {
            child.getData().getEffect().cast(context.setNode(child));
        }
    }
}
