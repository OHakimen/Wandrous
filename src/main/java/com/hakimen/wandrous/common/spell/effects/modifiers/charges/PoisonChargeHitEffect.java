package com.hakimen.wandrous.common.spell.effects.modifiers.charges;

import com.hakimen.wandrous.common.registers.EffectRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.effects.modifiers.ProjectileHitEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Iterator;

public class PoisonChargeHitEffect extends ProjectileHitEffect {
    public PoisonChargeHitEffect(int cost) {
        super(cost);
    }

    @Override
    public void onHitEntity(SpellContext context, Entity hit) {
        context.mergeStatus(this.getStatus());
        if(hit instanceof LivingEntity entity) {
            entity.addEffect(new MobEffectInstance(MobEffects.POISON, 30 * 20, 1));
        }
    }

    @Override
    public void onHitBlock(SpellContext context, Level level, BlockPos pos, BlockState state) {
        context.mergeStatus(this.getStatus());
        AreaEffectCloud cloud = new AreaEffectCloud(level, pos.getX(), pos.getY(), pos.getZ());
        cloud.addEffect(new MobEffectInstance(MobEffects.POISON, 30 * 20, 1));
        level.addFreshEntity(cloud);
    }
}
