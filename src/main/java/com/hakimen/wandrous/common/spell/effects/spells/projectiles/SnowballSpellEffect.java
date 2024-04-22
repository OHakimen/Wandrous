package com.hakimen.wandrous.common.spell.effects.spells.projectiles;

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

public class SnowballSpellEffect extends ProjectileSpellEffect {

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
        TriggerSnowball snowball = new TriggerSnowball(level, location.x, location.y, location.z, context);
        shootProjectile(snowball, context);
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
            ProjectileSpellEffect.onHitBlock(this,pResult,context);
            super.onHitBlock(pResult);
        }

        @Override
        protected void onHitEntity(EntityHitResult pResult) {
            ProjectileSpellEffect.onHitEntity(this, pResult, context);
            super.onHitEntity(pResult);
        }
    }
}
