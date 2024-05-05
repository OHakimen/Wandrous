package com.hakimen.wandrous.common.spell.effects.modifiers.charges;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.effects.modifiers.ProjectileHitEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

import java.util.Iterator;

public class CrumblingChargeHitEffect extends ProjectileHitEffect {

    public CrumblingChargeHitEffect(int cost) {
        super(cost);
    }

    @Override
    public void onHitEntity(SpellContext context, Entity hit) {
    }

    @Override
    public void onHitBlock(SpellContext context, Level level, BlockPos pos, BlockState state) {
        context.mergeStatus(this.getStatus());

        int radius = (int) context.getStatus().getRadius();
        Iterator<BlockPos> positions = BlockPos.betweenClosed(pos.offset(-radius, -radius, -radius), pos.offset(radius, radius, radius)).iterator();

        while (positions.hasNext()) {
            BlockPos blockpos = positions.next();
            if (blockpos.closerToCenterThan(pos.getCenter(), radius - 1)) {
                BlockState blockstate = level.getBlockState(blockpos);
                if (blockstate.getDestroySpeed(level, blockpos) != -1 && blockstate.getPistonPushReaction() != PushReaction.BLOCK && level.getBlockState(blockpos.below()).isAir()) {
                    FallingBlockEntity.fall(level, blockpos, blockstate);
                }
            }
        }
    }
}
