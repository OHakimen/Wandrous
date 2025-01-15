package com.hakimen.wandrous.common.entity.static_spell;

import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.registers.DamageTypeRegister;
import com.hakimen.wandrous.common.registers.EntityRegister;
import com.hakimen.wandrous.common.registers.ParticleRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.utils.CastingUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class NukeEntity extends SpellCastingProjectile {

    public static final EntityDataAccessor<Integer> MAX_TICK_TIME = SynchedEntityData.defineId(NukeEntity.class, EntityDataSerializers.INT);
    public int maxTickTime;
    public SpellContext context;


    public NukeEntity(Level pLevel, int maxTickTime, Vec3 location, SpellContext context) {
        super(EntityRegister.NUKE_ENTITY.get(), pLevel);
        this.maxTickTime = maxTickTime;
        this.setDeltaMovement(new Vec3(0, 0, 0));
        this.setNoGravity(true);
        this.context = context.clone();
        this.setPos(location);
        this.entityData.set(MAX_TICK_TIME, maxTickTime);
        ServerLevel lvl = ((ServerLevel) context.getLevel());
        for (ServerPlayer player : lvl.players()) {
            if(player.distanceTo(this) <= 512){
                lvl.sendParticles(player, ParticleRegister.SHOCKWAVE.get(),
                        true,
                        getX(),
                        getY(),
                        getZ(),
                        1,0,0,0,0);
            }
        }
    }

    public NukeEntity(Level pLevel, int maxTickTime, Vec3 location) {
        super(EntityRegister.NUKE_ENTITY.get(), pLevel);
        this.maxTickTime = maxTickTime;
        this.setDeltaMovement(new Vec3(0, 0, 0));
        this.setNoGravity(true);
        this.setPos(location);
        this.entityData.set(MAX_TICK_TIME, maxTickTime);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        pBuilder.define(MAX_TICK_TIME, maxTickTime);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        maxTickTime = compoundTag.getInt("MaxTickTime");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putInt("MaxTickTime", maxTickTime);
    }

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide && (tickCount / (float)maxTickTime) >= 0.25f) {
            Level level = level();
            BlockPos pos = this.getOnPos();
            Vec3 location = this.getPosition(0);
            float radius = ((tickCount / (float)maxTickTime) - 0.25f) * 50f;

            Random r = new Random();

            List<LivingEntity> entityList = level.getEntities(EntityTypeTest.forClass(LivingEntity.class), AABB.ofSize(location,radius * 2,radius * 2,radius * 2),entity -> entity instanceof LivingEntity);

            for (LivingEntity livingEntity : entityList) {
                livingEntity.hurt(DamageTypeRegister.nuke(context.getOriginalCaster()), 10);
                livingEntity.invulnerableTime = 10;
                CastingUtils.iFrameApply(livingEntity, context);
            }

            Iterator<BlockPos> positions = BlockPos.betweenClosedStream(pos.offset((int) radius, (int) radius, (int) radius), pos.offset((int) -radius, (int) -radius, (int) -radius)).filter(blockPos ->
                    !level.getBlockState(blockPos).is(Blocks.AIR)
                    && blockPos.closerToCenterThan(location, radius + r.nextFloat(-1,1))
                    && level.getBlockState(blockPos).getDestroySpeed(level, blockPos) != Block.INDESTRUCTIBLE
                    && level.getBlockState(blockPos).getPistonPushReaction() != PushReaction.BLOCK
            ).iterator();

            if (tickedEnough()) {
                if (EventHooks.canEntityGrief(level, this)) {
                    while (positions.hasNext()) {
                        BlockPos blockpos = positions.next();

                        if (r.nextFloat() < 0.7 && !level.getBlockState(blockpos).is(Blocks.FIRE)) {
                            level.setBlock(blockpos, Blocks.NETHERRACK.defaultBlockState(), Block.UPDATE_ALL);
                        }

                        if (r.nextFloat() < 0.5 && !level.getBlockState(blockpos).is(Blocks.FIRE)) {
                            level.setBlock(blockpos, Blocks.MAGMA_BLOCK.defaultBlockState(), Block.UPDATE_ALL);
                        }

                        if (level.getBlockState(blockpos.above()).getDestroySpeed(level, blockpos) != Block.INDESTRUCTIBLE && r.nextFloat() < 0.2f) {
                            level.setBlock(blockpos.above(), Blocks.FIRE.defaultBlockState(), Block.UPDATE_ALL);
                        }
                    }

                }

                discard();
            } else if (EventHooks.canEntityGrief(level, this)) {
                while (positions.hasNext()) {
                    BlockPos blockpos = positions.next();

                    level.setBlock(blockpos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                }
            }
        }
    }

    public boolean tickedEnough() {
        return maxTickTime != -1 && tickCount > maxTickTime;
    }
}
