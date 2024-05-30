package com.hakimen.wandrous.common.spell.mover;

import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.spell.SpellContext;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

public class HomingMover implements ISpellMover {
    @Override
    public void move(SpellContext context, SpellCastingProjectile projectile) {
        if (context.getHomingTarget() == null) {
            Level level = context.getLevel();
            Vec3 pos = projectile.getPosition(0);
            List<Entity> entities = level.getEntities(context.getCaster(), AABB.ofSize(pos, 32, 32, 32), (entity) -> entity instanceof LivingEntity && !entity.equals(context.getOriginalCaster()));

            LivingEntity closest = !entities.isEmpty() ? (LivingEntity) entities.get(new Random().nextInt(0, entities.size())) : null ;

            if (closest != null) {
                context.setHomingTarget(closest);
            }

        } else if (context.getHomingTarget() != null) {
            LivingEntity target = context.getHomingTarget();
            if(target.isAlive()){
                projectile.setDeltaMovement(target.getEyePosition().subtract(projectile.getPosition(0)).normalize().scale(0.9f));
            }else{
                context.setHomingTarget(null);
            }
        }
    }
}
