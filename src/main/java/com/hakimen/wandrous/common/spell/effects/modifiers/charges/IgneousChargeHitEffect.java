package com.hakimen.wandrous.common.spell.effects.modifiers.charges;

import com.hakimen.wandrous.common.registers.EffectRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.effects.modifiers.ProjectileHitEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Iterator;

public class IgneousChargeHitEffect extends ProjectileHitEffect {
    public IgneousChargeHitEffect(int cost) {
        super(cost);
    }

    @Override
    public void onHitEntity(SpellContext context, Entity hit) {
        context.mergeStatus(this.getStatus());
        if(hit instanceof LivingEntity entity) {
            entity.addEffect(new MobEffectInstance(EffectRegister.IGNITE, 30 * 20));
        }
    }

    @Override
    public void onHitBlock(SpellContext context,Level level, BlockPos pos, BlockState state) {
        context.mergeStatus(this.getStatus());
        int radius = (int)context.getStatus().getRadius();
        Iterator<BlockPos> positions = BlockPos.betweenClosed(pos.offset(-radius, -radius, -radius), pos.offset(radius, radius, radius)).iterator();

        while(positions.hasNext()) {
            BlockPos blockpos = positions.next();
            if (blockpos.closerToCenterThan(pos.getCenter(), radius-1)) {
                BlockState blockstate = level.getBlockState(blockpos);
                BlockState aboveState = level.getBlockState(blockpos.above());

                if(!blockstate.is(Blocks.AIR) && (!blockstate.is(Blocks.FIRE) || !blockstate.is(Blocks.SOUL_FIRE)) && aboveState.is(Blocks.AIR)){
                    level.setBlockAndUpdate(blockpos.above(), Blocks.FIRE.defaultBlockState());
                }

            }
        }
    }
}
