package com.hakimen.wandrous.common.spell.effects.spells.raycast;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.RaycastUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.*;

public class FlameBurstSpellEffect extends SpellEffect {
    public FlameBurstSpellEffect() {
        setKind(SPELL);
        setStatus(new SpellStatus()
                .setRadius(10)
                .setCastDelayMod(1f)
                .setRechargeTimeMod(1f)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(getStatus());
        Entity caster = context.getCaster();
        context.getLevel().playSound(null, context.getCaster().getOnPos(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1f, (float) context.getLevel().getRandom().nextInt(80,120)/100f);
        for (int i = 0; i < context.getStatus().getRadius() * 2; i++) {
            HitResult result = RaycastUtils.raycastWithOffset(caster, new Vec3(0, Math.sin(i), 0), context.getStatus().getRadius(), 0);
            ServerLevel level = (ServerLevel) context.getLevel();

            for (int j = 2; j < context.getStatus().getRadius() ; j++) {
                Vec3 off = caster.getViewVector(0).yRot((float) Math.sin(i * context.getStatus().getRadius() / 10f)).scale(j*2f);
                Vec3 loc = caster.getEyePosition().add(off);
                if (AABB.ofSize(loc, 1, 1, 1).contains(result.getLocation())) {
                    break;
                } else {
                    level.sendParticles(ParticleTypes.FLAME, loc.x, loc.y, loc.z, 2, 0.06125, 0.06125, 0.06125, 0.002);
                        if (result instanceof BlockHitResult bhr && result.getType() != HitResult.Type.MISS) {
                            if (level.getBlockState(bhr.getBlockPos().above()).getBlock().defaultBlockState().canBeReplaced() && !level.getBlockState(bhr.getBlockPos().above()).is(Blocks.WATER)) {
                                context.getLevel().setBlock(bhr.getBlockPos().above(), Blocks.FIRE.defaultBlockState(), 3);
                            }
                    } else if (result instanceof EntityHitResult ehr) {
                        ehr.getEntity().setRemainingFireTicks(100);
                    }
                }
            }
        }


    }
}
