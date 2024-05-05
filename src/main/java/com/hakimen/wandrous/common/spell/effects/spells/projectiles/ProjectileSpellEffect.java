package com.hakimen.wandrous.common.spell.effects.spells.projectiles;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.spell.effects.modifiers.ProjectileHitEffect;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;


public class ProjectileSpellEffect extends SpellEffect {
    public ProjectileSpellEffect() {
    }

    protected static void shootProjectile(Projectile self, SpellContext context){
        Entity caster = context.getCaster();
        SpellStatus status = context.getStatus();

        if (caster instanceof LivingEntity livingEntity) {
            float yRot = livingEntity.getYRot() + Math.round(context.getSplit() / 2.0) * (10 + status.getSpread() * 10) * (context.getSplit() % 2 == 1 ? -1 : 1);
            float xRot = livingEntity.getXRot();
            self.shootFromRotation(livingEntity,xRot, yRot, 0, status.getSpeed(), status.getSpread() * 10);
        } else {
            float yRot = Math.round(context.getSplit() / 2.0) * (10 + status.getSpread() * 10) * (context.getSplit() % 2 == 1 ? -1 : 1);
            self.setDeltaMovement(caster.getDeltaMovement().yRot((float) Math.toRadians(yRot)));
        }
    }

    protected static void onHitBlock(Projectile self, BlockHitResult pResult, SpellContext context){
        SpellContext nextContext = context.clone();

        List<ProjectileHitEffect> effects = addProjectileEffects(context.getNode());

        effects.forEach(projectileHitEffect -> {
            projectileHitEffect.onHitBlock(nextContext, self.level(), pResult.getBlockPos(), self.level().getBlockState(pResult.getBlockPos()));
        });

        if (nextContext.getNode().getData().hasKind(TRIGGER)) {
            self.setDeltaMovement(new Vec3(self.getDeltaMovement().toVector3f().reflect(Vec3.atLowerCornerOf(pResult.getDirection().getNormal()).toVector3f())));
            nextContext.getNode().getChildren().forEach(
                    (child) -> child.getData().cast(nextContext.setNode(child).setLocation(pResult.getLocation()))
            );
        }
    }

    protected static void onHitEntity(Projectile self, EntityHitResult pResult, SpellContext context) {
        SpellContext nextContext = context.clone();
        if (pResult.getEntity() instanceof LivingEntity entity) {
            nextContext.getHit().add(entity);
        }

        List<ProjectileHitEffect> effects = addProjectileEffects(context.getNode());

        effects.forEach(projectileHitEffect -> {
            projectileHitEffect.onHitBlock(nextContext, self.level(), pResult.getEntity().getOnPos(), self.level().getBlockState(pResult.getEntity().getOnPos()));
            projectileHitEffect.onHitEntity(nextContext, pResult.getEntity());
        });

        if (nextContext.getNode().getData().hasKind(TRIGGER)) {
            self.setDeltaMovement(self.getDeltaMovement().multiply(1, -1, 1));
            nextContext.getNode().getChildren().forEach(
                    (child) -> child.getData().cast(nextContext.setNode(child).setLocation(pResult.getLocation()))
            );
        }
        pResult.getEntity().hurt(self.damageSources().source(DamageTypes.MAGIC, context.getOriginalCaster()), context.getStatus().getDamage());
    }

    private static List<ProjectileHitEffect> addProjectileEffects(Node<SpellEffect> node){
        List<ProjectileHitEffect> effects = new ArrayList<>();
        if(node.getParent() != null &&  node.getParent().getData().hasKind(MODIFIER)){
            if( node.getParent().getData() instanceof ProjectileHitEffect effect){
                effects.add(effect);
            }
            effects.addAll(addProjectileEffects(node.getParent()));
        }
        return effects;
    }
}
