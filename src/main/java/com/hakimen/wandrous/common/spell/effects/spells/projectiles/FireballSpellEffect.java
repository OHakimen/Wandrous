package com.hakimen.wandrous.common.spell.effects.spells.projectiles;

import com.hakimen.wandrous.common.mixin.server.FireballExplosionPowerAccessorMixin;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class FireballSpellEffect extends ProjectileSpellEffect {

    public FireballSpellEffect(boolean isTrigger) {
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

        TriggerFireball fireball = new TriggerFireball(level, location.x, location.y, location.z, context);

        if (caster instanceof Player player) {
            fireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, status.getSpeed(), status.getSpread() * 10);
        } else {
            fireball.setDeltaMovement(caster.getDeltaMovement());
        }

        level.addFreshEntity(fireball);
    }

    class TriggerFireball extends LargeFireball {

        SpellContext context;

        public TriggerFireball(Level pLevel, double pX, double p_37396_, double pY, SpellContext context) {
            super(EntityType.FIREBALL, pLevel);

            this.context = context.clone();
            ((FireballExplosionPowerAccessorMixin) this).setExplosionPower((int) context.getStatus().getDamage());
            this.setPos(new Vec3(pX, p_37396_, pY));
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
