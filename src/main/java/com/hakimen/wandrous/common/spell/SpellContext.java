package com.hakimen.wandrous.common.spell;

import com.hakimen.wandrous.common.utils.CastingUtils;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SpellContext implements Cloneable{

    Entity originalCaster;
    Entity caster;
    ItemStack wand;
    Level level;
    Vec3 location;
    Node<SpellEffect> node;
    SpellStatus status;
    boolean castPositionModified;
    List<LivingEntity> hit;
    int split;

    public Entity getOriginalCaster() {
        return originalCaster;
    }

    public SpellContext setOriginalCaster(Entity originalCaster) {
        this.originalCaster = originalCaster;
        return this;
    }

    public Entity getCaster() {
        return caster;
    }

    public SpellContext setCaster(Entity caster) {
        this.caster = caster;
        return this;
    }

    public ItemStack getWand() {
        return wand;
    }

    public SpellContext setWand(ItemStack wand) {
        this.wand = wand;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public SpellContext setLevel(Level level) {
        this.level = level;
        return this;
    }

    public Vec3 getLocation() {
        return location;
    }

    public SpellContext setLocation(Vec3 location) {
        this.location = location;
        return this;
    }

    public Node<SpellEffect> getNode() {
        return node;
    }

    public SpellContext setNode(Node<SpellEffect> node) {
        this.node = node;
        return this;
    }

    public SpellStatus getStatus() {
        return status;
    }

    public SpellContext setStatus(SpellStatus status) {
        this.status = status;
        return this;
    }


    public void mergeStatus(SpellStatus status) {
        this.setStatus(CastingUtils.mergeStatus(status, this.getStatus()));
    }

    public boolean isCastPositionModified() {
        return castPositionModified;
    }

    public SpellContext setCastPositionModified(boolean castPositionModified) {
        this.castPositionModified = castPositionModified;
        return this;
    }

    public List<LivingEntity> getHit() {
        return hit;
    }

    public SpellContext setHit(List<LivingEntity> hit) {
        this.hit = hit;
        return this;
    }

    public int getSplit() {
        return split;
    }

    public SpellContext setSplit(int split) {
        this.split = split;
        return this;
    }

    public SpellContext clone(){
        try {
            return (SpellContext) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
