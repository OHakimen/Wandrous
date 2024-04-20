package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.CastingUtils;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityInLevelCallback;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class SpreadCastEffect extends SpellEffect {

    float radius;

    public SpreadCastEffect(float radius) {
        this.radius = radius;
        this.setKind(MODIFIER);
        this.setStatus(new SpellStatus()
                .setManaDrain((int)radius * 2)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());
        Random r = new Random();
        context.setLocation(context.getLocation().add(r.nextDouble(-1,1) * radius,0,r.nextDouble(-1,1) * radius));
        context.getNode().getChildren().forEach(child -> {
            child.getData().cast(context.setNode(child));
        });

    }
}
