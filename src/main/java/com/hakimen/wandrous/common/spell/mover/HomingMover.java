package com.hakimen.wandrous.common.spell.mover;

import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.spell.SpellContext;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class HomingMover implements ISpellMover {
    @Override
    public void move(SpellContext context, SpellCastingProjectile projectile) {
        if(projectile.tickCount > 2){
            if (context.getHomingTarget() == null) {
                Level level = context.getLevel();
                Vec3 pos = projectile.getEyePosition();
                List<LivingEntity> entities = level.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, (LivingEntity) context.getOriginalCaster(), AABB.ofSize(pos, 32, 32, 32));

                LivingEntity closest = null;

                for (LivingEntity entity : entities) {
                    if(closest == null){
                        closest = entity;
                    }else if(entity.getPosition(0).distanceTo(projectile.getPosition(0)) < closest.getPosition(0).distanceTo(projectile.getPosition(0))){
                        closest = entity;
                    }
                }

                if (closest != null) {
                    context.setHomingTarget(closest);
                }

            } else if (context.getHomingTarget() != null) {
                LivingEntity target = context.getHomingTarget();
                if(target.getHealth() > 0){
                    projectile.setNoGravity(true);
                    projectile.setDeltaMovement(target.getPosition(0).subtract(projectile.getPosition(0).subtract(0,+target.getBbHeight()/2f, 0)).normalize()
                            .scale(Math.clamp(target.getPosition(0).distanceTo(projectile.getPosition(0))/4f,0f, context.getStatus().getSpeed() > 0 ? context.getStatus().getSpeed() : 0.1f)));
                }else {
                    context.setHomingTarget(null);
                    projectile.setNoGravity(false);
                }
            }
        }
    }
}
