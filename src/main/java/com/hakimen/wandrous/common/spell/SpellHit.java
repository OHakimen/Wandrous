package com.hakimen.wandrous.common.spell;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
public interface SpellHit {
    void onHitEntity(Entity entityHit);

    void onHitBlock(Level level, BlockPos blockPos, BlockState blockState);
}
