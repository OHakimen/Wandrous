package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellEffect;
import com.hakimen.wandrous.common.api.SpellStatus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public abstract class ProjectileHitEffect extends SpellEffect{

    public ProjectileHitEffect(int cost) {
        setKind(MODIFIER);
        setStatus(new SpellStatus()
                .setManaDrain(cost)
                .setRadius(5)
        );
    }

    public abstract void onHitEntity(SpellContext context, Entity hit);

    public abstract void onHitBlock(SpellContext context, Level level, BlockPos pos, BlockState state);

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());
        context.getNode().getChildren().forEach(child -> child.getData().getEffect().cast(context.setNode(child)));
    }
}
