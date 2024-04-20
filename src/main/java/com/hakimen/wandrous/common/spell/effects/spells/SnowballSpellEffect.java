package com.hakimen.wandrous.common.spell.effects.spells;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class SnowballSpellEffect extends SpellEffect {

    public SnowballSpellEffect(boolean isTrigger) {
        this.setKind(isTrigger ? TRIGGER : SPELL);
        this.setStatus(new SpellStatus()
                .setDamage(4f)
                .setManaDrain(20)
                .setSpeed(1f)
                .setSpread(0)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(this.getStatus());

        Level level = context.getLevel();
        Vec3 location = context.getLocation();
        Entity caster = context.getCaster();
        SpellStatus status = context.getStatus();


        TriggerSnowball snowball = new TriggerSnowball(level, location.x, location.y, location.z, context);


        if (caster instanceof LivingEntity livingEntity) {
            snowball.shootFromRotation(livingEntity, livingEntity.getXRot(), livingEntity.getYRot(), 0, status.getSpeed(), status.getSpread() * 10);
        } else {
            snowball.setDeltaMovement(caster.getDeltaMovement());
        }

        level.addFreshEntity(snowball);
    }

    class TriggerSnowball extends Snowball {

        SpellContext context;

        public TriggerSnowball(Level pLevel, double pX, double p_37396_, double pY, SpellContext context) {
            super(pLevel, pX, p_37396_, pY);
            this.context = context.clone();
            this.context.setCaster(this);
        }

        @Override
        protected void onHit(HitResult pResult) {
            if (!(pResult instanceof EntityHitResult ehr && ehr.getEntity().equals(context.getCaster())) && context.getNode().getData().hasKind(TRIGGER)) {
                super.onHit(pResult);
            } else if (!context.getNode().getData().hasKind(TRIGGER)) {
                super.onHit(pResult);
            }
        }

        @Override
        protected void onHitBlock(BlockHitResult pResult) {
            if (context.getNode().getData().hasKind(TRIGGER)) {
                this.setDeltaMovement(new Vec3(this.getDeltaMovement().toVector3f().reflect(Vec3.atLowerCornerOf(pResult.getDirection().getNormal()).toVector3f())));
                context.getNode().getChildren().forEach(
                        (child) -> child.getData().cast(context.setNode(child).setLocation(pResult.getLocation()))
                );
            }
        }

        @Override
        protected void onHitEntity(EntityHitResult pResult) {
            if (context.getNode().getData().hasKind(TRIGGER)) {
                this.setDeltaMovement(this.getDeltaMovement().multiply(1, -1, 1));
                context.getNode().getChildren().forEach(
                        (child) -> child.getData().cast(context.setNode(child).setLocation(pResult.getLocation()))
                );
            }
            pResult.getEntity().hurt(damageSources().magic(), context.getStatus().getDamage());
        }
    }
}
