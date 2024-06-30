package com.hakimen.wandrous.common.spell.effects.spells.summon_spells;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.Vec3;

import java.util.Iterator;

public class SummonWebbingSpellEffect extends SpellEffect {

    public SummonWebbingSpellEffect() {
        setKind(SPELL);
        setStatus(new SpellStatus()
                .setManaDrain(50)
                .setRadius(3)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        Level level = context.getLevel();
        Vec3 location = context.getLocation();
        BlockPos pos = new BlockPos((int) Math.round(location.x), (int) Math.round(location.y), (int) Math.round(location.z));int radius = (int) context.getStatus().getRadius();

        Iterator<BlockPos> positions = BlockPos.betweenClosedStream(pos.offset(-radius, -radius, -radius), pos.offset(radius, radius, radius)).filter(blockPos ->
                level.getBlockState(blockPos).getPistonPushReaction() != PushReaction.BLOCK
                        && level.getBlockState(blockPos).is(Blocks.AIR)
                        && blockPos.closerToCenterThan(location, radius) && level.getBlockState(blockPos).getDestroySpeed(level, blockPos) != Block.INDESTRUCTIBLE
        ).iterator();

        while(positions.hasNext()){
            BlockPos blockPos = positions.next();

            if(blockPos.closerToCenterThan(location, radius)) {
                level.setBlockAndUpdate(blockPos, Blocks.COBWEB.defaultBlockState());
            }
        }

    }
}
