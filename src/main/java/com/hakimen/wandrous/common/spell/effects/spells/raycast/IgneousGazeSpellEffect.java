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
import net.minecraft.world.phys.*;

public class IgneousGazeSpellEffect extends SpellEffect {
    public IgneousGazeSpellEffect() {
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
        Entity caster = context.getCaster();
        Vec3 where = context.isCastPositionModified() ? context.getLocation() : context.getCaster().getEyePosition();
        context.getLevel().playSound(null, context.getCaster().getOnPos(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1f, (float) context.getLevel().getRandom().nextInt(80, 120) / 100f);
        for (int i = 0; i < context.getStatus().getRadius() * 2; i++) {
            HitResult result = RaycastUtils.raycastRotatedAtPos(caster,
                    where,
                    new Vec3(0, Math.sin(i), 0), context.getStatus().getRadius(), 0);
            ServerLevel level = (ServerLevel) context.getLevel();

            for (int j = 2; j < context.getStatus().getRadius(); j++) {
                Vec3 off = caster.getViewVector(0).yRot((float) Math.sin(i * context.getStatus().getRadius() / 10f)).scale(j * 2f);
                Vec3 loc = where.add(off);
                if (AABB.ofSize(loc, 1, 1, 1).contains(result.getLocation())) {
                    break;
                } else {
                    level.sendParticles(ParticleRegister.FIERY_PARTICLES.get(), loc.x, loc.y, loc.z, 4, 0.1f,0.1f,0.1f, 0.002);
                    if (result instanceof BlockHitResult bhr && result.getType() != HitResult.Type.MISS) {
                        if (level.getBlockState(bhr.getBlockPos().above()).getBlock().defaultBlockState().canBeReplaced() && !level.getBlockState(bhr.getBlockPos().above()).is(Blocks.WATER)) {
                            context.getLevel().setBlock(bhr.getBlockPos().above(), Blocks.FIRE.defaultBlockState(), 3);
                        }
                    } else if (result instanceof EntityHitResult ehr) {
                        if (ehr.getEntity() instanceof LivingEntity livingEntity) {
                            livingEntity.addEffect(new MobEffectInstance(Holder.direct(EffectRegister.IGNITE.get()), 20 * 20));
                        }
                    }
                }
            }
        }


    }
}
