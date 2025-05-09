package com.hakimen.wandrous.common.spell.effects.spells.static_projectiles.utility;

import com.hakimen.wandrous.common.registers.DamageTypeRegister;
import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellEffect;
import com.hakimen.wandrous.common.api.SpellStatus;
import com.hakimen.wandrous.common.utils.CastingUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChainsawSpellEffect extends SpellEffect {

    ItemStack mineAs;

    public ChainsawSpellEffect(int cost, float radius) {
        setKind(SPELL);
        setStatus(new SpellStatus()
                .setManaDrain(cost)
                .setRadius(radius)
                .setDamage(4f)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        float radius = context.getStatus().getRadius();
        context.getStatus().setRadiusMod(0);
        Vec3 location = context.getLocation();
        BlockPos pos = new BlockPos((int) Math.round(location.x), (int) Math.round(location.y), (int) Math.round(location.z));

        Level level = context.getLevel();

        List<LivingEntity> toDealDamage = context.getLevel().getEntitiesOfClass(LivingEntity.class, AABB.ofSize(location, radius,radius,radius), livingEntity -> !livingEntity.equals(context.getOriginalCaster()));
        toDealDamage.forEach(
                livingEntity -> {
                    livingEntity.hurt(DamageTypeRegister.sliceDamage(context.getOriginalCaster()),getStatus().getDamage());
                    CastingUtils.iFrameApply(livingEntity, context);
                }
        );
        context.getStatus().setDamageMod(0);

        Iterator<BlockPos> positions = BlockPos.betweenClosed(pos.offset((int) -radius, (int) -radius, (int) -radius), pos.offset((int) radius, (int) radius, (int) radius)).iterator();

        List<ItemStack> stacksList = new ArrayList<>();

        while (positions.hasNext()) {
            BlockPos blockpos = positions.next();
            if (blockpos.closerToCenterThan(location, radius)) {
                BlockState state = level.getBlockState(blockpos);
                if (!level.isClientSide) {
                    if (state.is(BlockTags.MINEABLE_WITH_AXE) || state.is(BlockTags.LEAVES)) {
                        List<ItemStack> stackList = state.getDrops(new LootParams.Builder((ServerLevel) context.getLevel())
                                .withParameter(LootContextParams.TOOL, getMineAs())
                                .withParameter(LootContextParams.ORIGIN, pos.getCenter()));
                        level.setBlockAndUpdate(blockpos, Blocks.AIR.defaultBlockState());
                        stacksList.addAll(stackList);
                    }
                }
            }
        }

        stacksList.stream().reduce((i1, i2) -> {
            if (i1.getCount() + i2.getCount() <= i1.getMaxStackSize()) {
                i1.setCount(i1.getCount() + i2.getCount());
            }
            return i1;
        });
        for (ItemStack stack : stacksList) {
            ItemEntity entity = new ItemEntity(EntityType.ITEM, level);
            entity.setItem(stack);
            entity.setPos(location.x() + 0.5f + level.getRandom().nextInt(-1, 1) / 4f, location.y() + 0.5f + level.getRandom().nextInt(-1, 1) / 4f, location.z() + 0.5f + level.getRandom().nextInt(-1, 1) / 4f);
            level.addFreshEntity(entity);
        }
    }

    public ItemStack getMineAs() {
        return mineAs;
    }

    public ChainsawSpellEffect setMineAs(ItemStack mineAs) {
        this.mineAs = mineAs;
        return this;
    }
}
