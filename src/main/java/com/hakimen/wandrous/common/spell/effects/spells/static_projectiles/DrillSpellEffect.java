package com.hakimen.wandrous.common.spell.effects.spells.static_projectiles;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.DynamicFluidContainerModel;

import java.util.Iterator;
import java.util.List;

public class DrillSpellEffect extends SpellEffect {

    ItemStack mineAs;
    int radius;

    public DrillSpellEffect(int cost, int radius) {
        setKind(SPELL);
        setStatus(new SpellStatus()
                .setManaDrain(cost)
        );
        this.radius = radius;
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        Vec3 location = context.getLocation();
        BlockPos pos = new BlockPos((int) Math.round(location.x), (int) Math.round(location.y), (int) Math.round(location.z));

        Level level = context.getLevel();

        Iterator<BlockPos> positions = BlockPos.betweenClosed(pos.offset(-radius, -radius, -radius), pos.offset(radius, radius, radius)).iterator();

        while (positions.hasNext()) {
            BlockPos blockpos = positions.next();
            if (blockpos.closerToCenterThan(location, radius)) {
                BlockState state = level.getBlockState(blockpos);
                if (!level.isClientSide) {
                    if (state.is(BlockTags.MINEABLE_WITH_PICKAXE) || state.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
                        List<ItemStack> stackList = state.getDrops(new LootParams.Builder((ServerLevel) context.getLevel())
                                .withParameter(LootContextParams.TOOL, getMineAs())
                                .withParameter(LootContextParams.ORIGIN, pos.getCenter()));
                        level.setBlockAndUpdate(blockpos, Blocks.AIR.defaultBlockState());
                        for (ItemStack stack : stackList) {
                            ItemEntity entity = new ItemEntity(EntityType.ITEM, level);
                            entity.setItem(stack);
                            entity.setPos(blockpos.getX() + 0.5f + level.getRandom().nextInt(-1, 1) / 4f, blockpos.getY() + 0.5f + level.getRandom().nextInt(-1, 1) / 4f, blockpos.getZ() + 0.5f + level.getRandom().nextInt(-1, 1) / 4f);
                            level.addFreshEntity(entity);
                        }
                    }
                }
            }

        }
    }

    public ItemStack getMineAs() {
        return mineAs;
    }

    public DrillSpellEffect setMineAs(ItemStack mineAs) {
        this.mineAs = mineAs;
        return this;
    }
}
