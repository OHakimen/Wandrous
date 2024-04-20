package com.hakimen.wandrous.common.spell.effects.spells.projectiles;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;


public class ProjectileSpellEffect extends SpellEffect {
    public ProjectileSpellEffect() {
    }

    protected static void onHitBlock(Projectile self, BlockHitResult pResult, SpellContext context){
        if (context.getNode().getData().hasKind(TRIGGER)) {
            self.setDeltaMovement(new Vec3(self.getDeltaMovement().toVector3f().reflect(Vec3.atLowerCornerOf(pResult.getDirection().getNormal()).toVector3f())));
            context.getNode().getChildren().forEach(
                    (child) -> child.getData().cast(context.setNode(child).setLocation(pResult.getLocation()))
            );
        }
        if (!context.getHitEffects().isEmpty()) {
            context.getHitEffects().forEach(spellHit -> {
                spellHit.onHitBlock(self.level(), pResult.getBlockPos(), self.level().getBlockState(pResult.getBlockPos()));
            });
        }
    }

    protected static void onHitEntity(Projectile self, EntityHitResult pResult, SpellContext context) {
        if (pResult.getEntity() instanceof LivingEntity entity) {
            if (!context.getHitEffects().isEmpty()) {
                context.getHitEffects().forEach(spellHit -> {
                    spellHit.onHitEntity(entity);
                });
            }
            context.getHit().add(entity);
        }
        if (context.getNode().getData().hasKind(TRIGGER)) {
            self.setDeltaMovement(self.getDeltaMovement().multiply(1, -1, 1));
            context.getNode().getChildren().forEach(
                    (child) -> child.getData().cast(context.setNode(child).setLocation(pResult.getLocation()))
            );
        }
        pResult.getEntity().hurt(self.damageSources().magic(), context.getStatus().getDamage());
    }
}
