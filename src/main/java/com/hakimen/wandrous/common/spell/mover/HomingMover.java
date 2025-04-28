package com.hakimen.wandrous.common.spell.mover;

import com.hakimen.wandrous.common.api.mover.ISpellMover;
import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.api.SpellContext;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class HomingMover implements ISpellMover {
    @Override
    public void move(SpellContext context, SpellCastingProjectile projectile) {
        if(projectile.tickCount > 2){
            if (context.getHomingTarget() == null) {
                Level level = context.getLevel();
                Vec3 pos = projectile.getEyePosition();

                LivingEntity target =  context.isCanHitCaster() ? null : (LivingEntity) context.getOriginalCaster();
                LivingEntity closest = level.getNearestEntity(LivingEntity.class,TargetingConditions.DEFAULT,target, pos.x,pos.y,pos.z, AABB.ofSize(pos, 32, 32, 32));

                if (closest != null) {
                    context.setHomingTarget(closest);
                }

            } else if (context.getHomingTarget() != null) {
                LivingEntity target = context.getHomingTarget();
                if(target.getHealth() > 0){
                    projectile.setNoGravity(true);
                    projectile.lookAt(EntityAnchorArgument.Anchor.EYES, target.getPosition(0));
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
