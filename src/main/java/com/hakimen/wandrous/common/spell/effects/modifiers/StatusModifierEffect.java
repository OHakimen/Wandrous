package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.CastingUtils;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class StatusModifierEffect extends SpellEffect {
    public StatusModifierEffect(SpellStatus status) {
        setKind(MODIFIER);
        setStatus(status);
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());
        context.getNode().getChildren().forEach((child)-> {
            child.getData().cast(context.setNode(child));
        });
    }
}
