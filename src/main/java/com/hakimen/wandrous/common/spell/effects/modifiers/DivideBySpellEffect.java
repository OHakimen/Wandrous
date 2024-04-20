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

public class DivideBySpellEffect extends SpellEffect {
    int castCount;

    public DivideBySpellEffect(int castCount) {
        this.castCount = castCount;
        this.setKind(MODIFIER);
        this.setStatus(new SpellStatus()
                .setManaDrain(castCount * 10)
                .setDamageMod(-(1f/castCount)));
    }

    public int getCastCount() {
        return castCount;
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());
        for (int i = 0; i < castCount; i++) {
            context.getNode().getChildren().forEach((child) -> {
                child.getData().cast(context.clone().setNode(child));
            });
        }
    }
}
