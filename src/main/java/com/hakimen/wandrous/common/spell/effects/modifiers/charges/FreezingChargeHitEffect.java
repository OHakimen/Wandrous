package com.hakimen.wandrous.common.spell.effects.modifiers.charges;

import com.hakimen.wandrous.common.registers.EffectRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.effects.modifiers.ProjectileHitEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Iterator;

import static net.minecraft.world.level.dimension.BuiltinDimensionTypes.NETHER_EFFECTS;

public class FreezingChargeHitEffect extends ProjectileHitEffect {
    public FreezingChargeHitEffect(int cost) {
        super(cost);
    }

    @Override
    public void onHitEntity(SpellContext context, Entity hit) {
        context.mergeStatus(this.getStatus());
        if(hit instanceof LivingEntity entity && !context.getLevel().dimensionType().effectsLocation().equals(NETHER_EFFECTS)) {
            entity.addEffect(new MobEffectInstance(EffectRegister.FREEZING, 30 * 20));
        }
    }

    @Override
    public void onHitBlock(SpellContext context, Level level, BlockPos pos, BlockState state) {
        context.mergeStatus(this.getStatus());
        int radius = (int)context.getStatus().getRadius();
        Iterator<BlockPos> positions = BlockPos.betweenClosed(pos.offset(-radius, -radius, -radius), pos.offset(radius, radius, radius)).iterator();

        while(positions.hasNext()) {
            BlockPos blockpos = positions.next();
            if (blockpos.closerToCenterThan(pos.getCenter(), radius-1) && !level.dimensionType().effectsLocation().equals(NETHER_EFFECTS)) {
                BlockState blockstate = level.getBlockState(blockpos);
                BlockState aboveState = level.getBlockState(blockpos.above());

                if(blockstate.is(Blocks.WATER)){
                    level.setBlockAndUpdate(blockpos, Blocks.FROSTED_ICE.defaultBlockState());
                }

                if((!blockstate.is(Blocks.AIR) && Block.isShapeFullBlock(blockstate.getShape(level, pos))) && aboveState.is(Blocks.AIR)){
                    level.setBlockAndUpdate(blockpos.above(), Blocks.SNOW.defaultBlockState());
                }

                if(blockstate.is(Blocks.LAVA)){
                    if(blockstate.equals(Blocks.LAVA.defaultBlockState())){
                        level.setBlockAndUpdate(blockpos, Blocks.OBSIDIAN.defaultBlockState());
                    } else {
                        level.setBlockAndUpdate(blockpos, Blocks.COBBLESTONE.defaultBlockState());
                    }
                }

                if(blockstate.is(Blocks.MAGMA_BLOCK)){
                    level.setBlockAndUpdate(blockpos, Blocks.NETHERRACK.defaultBlockState());
                }
            }
        }
    }
}
