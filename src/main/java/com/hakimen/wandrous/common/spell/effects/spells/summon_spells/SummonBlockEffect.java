package com.hakimen.wandrous.common.spell.effects.spells.summon_spells;

import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellEffect;
import com.hakimen.wandrous.common.api.SpellStatus;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class SummonBlockEffect extends SpellEffect {

    BlockState block;

    public SummonBlockEffect(BlockState block, int cost) {
        this.setKind(SpellEffect.SPELL);
        this.block = block;
        setStatus(new SpellStatus()
                .setManaDrain(cost)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(getStatus());
        Entity caster = context.getCaster();
        Level level = context.getLevel();

        Vec3 location = context.getLocation();
        BlockPos pos = new BlockPos((int) location.x, (int) location.y, (int) location.z);


        if(caster != null){
            if (caster instanceof LivingEntity entity) {
                BlockPos newPos = entity.getOnPos().below();

                if(placeBlockIfReplaceable(level, newPos, context))
                    return;
            }

            if(!context.isCastPositionModified()){
                BlockPos newPos = caster.getOnPos();
                if(placeBlockIfReplaceable(level, newPos, context))
                    return;
            }

            if (placeBlockIfReplaceable(level, pos, context)) return;

            for (Direction dir : new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST, Direction.UP, Direction.DOWN}) {
                if (placeBlockIfReplaceable(level, pos.relative(dir), context)) {
                    return;
                }
            }

        }

    }

    protected boolean placeBlockIfReplaceable(Level level, BlockPos pos, SpellContext context) {
        SoundType sound = this.block.getBlock().getSoundType(block, level, pos, null);

        if (level.getBlockState(pos).canBeReplaced()) {
            level.setBlockAndUpdate(pos, this.block);
            level.playSound(null, pos, sound.getPlaceSound(), SoundSource.BLOCKS, 1f, 1f);
            return true;
        }
        return false;
    }
}
