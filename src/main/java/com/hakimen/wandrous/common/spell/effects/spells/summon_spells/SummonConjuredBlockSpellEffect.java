package com.hakimen.wandrous.common.spell.effects.spells.summon_spells;

import com.hakimen.wandrous.common.block_entity.ConjuredBlockEntity;
import com.hakimen.wandrous.common.registers.BlockEntityRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SummonConjuredBlockSpellEffect extends SummonBlockEffect{
    public SummonConjuredBlockSpellEffect(BlockState block, int cost) {
        super(block, cost);
        getStatus().setLifeTime(20 * 15);
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(getStatus());
        super.cast(context);
    }

    @Override
    protected boolean placeBlockIfReplaceable(Level level, BlockPos pos, SpellContext context) {

        boolean did = super.placeBlockIfReplaceable(level, pos, context);

        if(did){
            ConjuredBlockEntity conjuredBlockEntity = level.getBlockEntity(pos, BlockEntityRegister.CONJURED_BLOCK_ENTITY.get()).orElse(null);

            if(conjuredBlockEntity != null) {
                conjuredBlockEntity.setLifeTime(context.getStatus().getLifeTime());
            }
        }

        return did;
    }
}
