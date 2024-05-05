package com.hakimen.wandrous.common.spell.mover;

import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.spell.SpellContext;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

public class BoomerangMover implements ISpellMover {
    @Override
    public void move(SpellContext context, SpellCastingProjectile projectile) {
        if(projectile.tickCount >= projectile.getMaxTicks() / 2f){
            Entity original = context.getOriginalCaster();
            projectile.setDeltaMovement(original.getEyePosition().subtract(projectile.getEyePosition()).normalize().scale(context.getStatus().getSpeed()));
        }
    }
}
