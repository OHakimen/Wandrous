package com.hakimen.wandrous.common.spell.effects.spells.raycast;

import com.hakimen.wandrous.common.registers.EffectRegister;
import com.hakimen.wandrous.common.registers.ParticleRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.RaycastUtils;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;

public class FreezingGazeSpellEffect extends SpellEffect {
    public FreezingGazeSpellEffect() {
        setKind(SPELL);
        setStatus(new SpellStatus()
                .setManaDrain(90)
                .setRadius(20)
                .setCastDelayMod(1f)
                .setRechargeTimeMod(1f)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(getStatus());
        Vec3 where = context.isCastPositionModified() ? context.getLocation() : context.getCaster().getEyePosition();
        Entity caster = context.getCaster();
        context.getLevel().playSound(null, context.getCaster().getOnPos(), SoundEvents.BREEZE_WIND_CHARGE_BURST.value(), SoundSource.PLAYERS, 1f, (float) context.getLevel().getRandom().nextInt(80, 120) / 100f);
        for (int i = 0; i < context.getStatus().getRadius() * 2; i++) {
            HitResult result = RaycastUtils.raycastRotatedAtPos(caster, where,
                    new Vec3(0, Math.sin(i * context.getStatus().getRadius() / 10f), 0), context.getStatus().getRadius(), 0);
            ServerLevel level = (ServerLevel) context.getLevel();
            for (int j = 2; j < context.getStatus().getRadius(); j++) {
                Vec3 off = caster.getViewVector(0).yRot((float) Math.sin(i * context.getStatus().getRadius() / 10f)).scale(j * 2f);
                Vec3 loc = where.add(off);
                if (AABB.ofSize(loc, 2, 2, 2).contains(result.getLocation())) {
                    break;
                } else {
                    level.sendParticles(ParticleRegister.FREEZING_GAZE.get(), loc.x, loc.y, loc.z, 2, 0.1f,0.1f,0.1f, 0.1);
                    if (result instanceof BlockHitResult bhr && result.getType() != HitResult.Type.MISS) {

                        for (int k = 1; k <= 3; k++) {
                            BlockState state = context.getLevel().getBlockState(bhr.getBlockPos().above(k));
                            if (state.canBeReplaced())
                                context.getLevel().setBlock(bhr.getBlockPos().above(k), Blocks.ICE.defaultBlockState(), 3);
                        }
                    } else if (result instanceof EntityHitResult ehr) {
                        if(ehr.getEntity() instanceof LivingEntity livingEntity){
                            livingEntity.addEffect(new MobEffectInstance(Holder.direct(EffectRegister.FREEZING.get()), 20 * 20));
                        }
                    }
                }
            }
        }


    }
}
