package com.hakimen.wandrous.common.spell;

import java.util.Random;

public class SpellStatus {

    protected int manaDrain;
    protected int lifeTime;
    protected float radius;
    protected float spread;
    protected float speed;
    protected float damage;
    protected float castDelayMod;
    protected float rechargeTimeMod;
    protected float spreadMod;
    protected float speedMod;
    protected float lifetimeMod;
    protected float radiusMod;
    protected float damageMod;
    protected float critChance;              // Crit multiplies damage by 5

    public int getManaDrain() {
        return manaDrain;
    }

    public SpellStatus setManaDrain(int manaDrain) {
        this.manaDrain = manaDrain;
        return this;
    }

    public float getDamage() {
        Random random = new Random();
        return (Math.max(0,damage) * (1f + getDamageMod())) * (random.nextFloat(0, 1) < getCritChance() ? 5f : 1f) * (getCritChance() > 1 ? getCritChance() : 1);
    }

    public float getRawDamage() {
        return damage;
    }

    public SpellStatus setDamage(float damage) {
        this.damage = damage;
        return this;
    }

    public float getRadius() {
        return Math.max(0,radius) * (1 + getRadiusMod());
    }

    public SpellStatus setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public float getSpread() {
        return Math.max(0,spread) * (1 + getSpreadMod());
    }

    public float getRawSpread() {
        return spread;
    }

    public SpellStatus setSpread(float spread) {
        this.spread = spread;
        return this;
    }

    public float getSpeed() {
        return Math.max(0,speed) * (1 + getSpeedMod());
    }

    public float getRawSpeed() {
        return speed;
    }

    public SpellStatus setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public int getLifeTime() {
        return (int) (Math.max(0,lifeTime) * (1 + getLifetimeMod()));
    }

    public float getRawLifeTime() {
        return lifeTime;
    }

    public SpellStatus setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
        return this;
    }

    public float getCastDelayMod() {
        return castDelayMod;
    }

    public SpellStatus setCastDelayMod(float castDelayMod) {
        this.castDelayMod = castDelayMod;
        return this;
    }

    public float getRechargeTimeMod() {
        return rechargeTimeMod;
    }

    public SpellStatus setRechargeTimeMod(float rechargeTimeMod) {
        this.rechargeTimeMod = rechargeTimeMod;
        return this;
    }

    public float getSpreadMod() {
        return spreadMod;
    }

    public SpellStatus setSpreadMod(float spreadMod) {
        this.spreadMod = spreadMod;
        return this;
    }

    public float getSpeedMod() {
        return speedMod;
    }

    public SpellStatus setSpeedMod(float speedMod) {
        this.speedMod = speedMod;
        return this;
    }

    public float getRadiusMod() {
        return radiusMod;
    }

    public SpellStatus setRadiusMod(float radiusMod) {
        this.radiusMod = radiusMod;
        return this;
    }

    public float getLifetimeMod() {
        return lifetimeMod;
    }

    public SpellStatus setLifetimeMod(float lifetimeMod) {
        this.lifetimeMod = lifetimeMod;
        return this;
    }

    public float getCritChance() {
        return critChance;
    }

    public SpellStatus setCritChance(float critChance) {
        this.critChance = critChance;
        return this;
    }

    public float getDamageMod() {
        return damageMod;
    }

    public SpellStatus setDamageMod(float damageMod) {
        this.damageMod = damageMod;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SpellStatus{");
        sb.append("manaDrain=").append(manaDrain);
        sb.append(", radius=").append(radius);
        sb.append(", spread=").append(spread);
        sb.append(", speed=").append(speed);
        sb.append(", lifeTime=").append(lifeTime);
        sb.append(", damage=").append(damage);
        sb.append(", castDelayMod=").append(castDelayMod);
        sb.append(", rechargeTimeMod=").append(rechargeTimeMod);
        sb.append(", spreadMod=").append(spreadMod);
        sb.append(", speedMod=").append(speedMod);
        sb.append(", lifetimeMod=").append(lifetimeMod);
        sb.append(", damageMod=").append(damageMod);
        sb.append(", critChance=").append(critChance);
        sb.append('}');
        return sb.toString();
    }

}
