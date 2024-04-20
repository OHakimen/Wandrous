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

public class MulticastEffect extends SpellEffect {
    int castCount;

    public MulticastEffect(int castCount) {
        this.castCount = castCount;
        this.setStatus(new SpellStatus()
                .setManaDrain(20 * castCount));
    }

    public int getCastCount() {
        return castCount;
    }

    @Override
    public void cast(SpellContext context) {
        for (Node<SpellEffect> child : context.getNode().getChildren()) {
            SpellContext nextContext = context.clone();
            child.getData().cast(nextContext.setNode(child));
        }
    }
}
