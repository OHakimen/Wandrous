package com.hakimen.wandrous.common.block_entity;

import com.hakimen.wandrous.common.block.ConjuredLightBlock;
import com.hakimen.wandrous.common.registers.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class ConjuredBlockEntity extends BlockEntity implements BlockEntityTicker<ConjuredBlockEntity> {

    int lifeTime;
    int tick;

    public ConjuredBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegister.CONJURED_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.putInt("LifeTime", lifeTime);
        pTag.putInt("Tick", tick);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        tick = pTag.getInt("Tick");
        lifeTime = pTag.getInt("LifeTime");
        super.load(pTag);
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, ConjuredBlockEntity conjuredLightBlockEntity) {
        if(level.isClientSide()) {
            Random r = new Random();
            if(r.nextFloat(0,1) < 0.5){
                if(blockState.getBlock() instanceof ConjuredLightBlock){
                    level.addParticle(ParticleTypes.END_ROD,blockPos.getX() + 0.5 + r.nextFloat(-0.2f,0.2f), blockPos.getY() + 0.5 + r.nextFloat(-0.2f,0.2f), blockPos.getZ() + 0.5 + r.nextFloat(-0.2f,0.2f), 0.0D, 0.0D, 0.0D);
                }else{
                    level.addParticle(
                            ParticleTypes.END_ROD,
                            blockPos.getX() + 0.5 + r.nextFloat(-0.6f,0.6f),
                            blockPos.getY() + 0.5 + r.nextFloat(-0.6f,0.6f),
                            blockPos.getZ() + 0.5 + r.nextFloat(-0.6f,0.6f),
                            0.0D, 0.0D, 0.0D);
                }
            }
        }else{
            tick++;
            if(tick >= lifeTime){
                BlockState self = level.getBlockState(blockPos);
                SoundType sound = self.getSoundType();

                level.playLocalSound(blockPos,sound.getBreakSound(), SoundSource.BLOCKS, 1f,1f,false);

                level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
            }
        }
    }

    public ConjuredBlockEntity setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
        return this;
    }
}
