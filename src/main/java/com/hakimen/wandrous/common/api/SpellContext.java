package com.hakimen.wandrous.common.api;

import com.hakimen.wandrous.common.utils.CastingUtils;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.List;
/**
 * <h1>Spell Context</h1>
 * This class gives the context needed for executing spells on casts
 * <br><br>
 * Every property are defined as just a key into a hashmap allowing it to be extended by just getting the hashmap and adding extra properties into it
 */
public class SpellContext implements Cloneable {

    HashMap<String, Object> properties;

    public SpellContext() {
        properties = new HashMap<>();
    }

    public HashMap<String, Object> getProperties() {
        return properties;
    }

    public SpellContext setProperties(HashMap<String, Object> properties) {
        this.properties = properties;
        return this;
    }

    public Entity getOriginalCaster() {
        return (Entity) properties.get("originalCaster");
    }

    public SpellContext setOriginalCaster(Entity originalCaster) {
        properties.put("originalCaster", originalCaster);
        return this;
    }

    public List<SpellEffect> getSpells() {
        return (List<SpellEffect>) properties.get("spells");
    }

    public SpellContext setSpells(List<SpellEffect> spells) {
        properties.put("spells", spells);
        return this;
    }

    public Entity getCaster() {
        return (Entity) properties.get("caster");
    }

    public SpellContext setCaster(Entity caster) {
        properties.put("caster", caster);
        return this;
    }

    public ItemStack getWand() {
        return (ItemStack) properties.get("wand");
    }

    public SpellContext setWand(ItemStack wand) {
        properties.put("wand", wand);
        return this;
    }

    public Level getLevel() {
        return (Level) properties.get("level");
    }

    public SpellContext setLevel(Level level) {
        properties.put("level", level);
        return this;
    }

    public Vec3 getLocation() {
        return (Vec3) properties.get("location");
    }

    public SpellContext setLocation(Vec3 location) {
        properties.put("location", location);
        return this;
    }

    public Node<SpellStack> getNode() {
        return (Node<SpellStack>) properties.get("node");
    }

    public SpellContext setNode(Node<SpellStack> node) {
        properties.put("node", node);
        return this;
    }

    public SpellStatus getStatus() {
        return (SpellStatus) properties.get("status");
    }

    public SpellContext setStatus(SpellStatus status) {
        properties.put("status", status);
        return this;
    }


    public void mergeStatus(SpellStatus status) {
        this.setStatus(CastingUtils.mergeStatus(status, this.getStatus()));
    }

    public boolean isCastPositionModified() {
        return properties.containsKey("castPositionModified") &&  (boolean)properties.get("castPositionModified");
    }

    public SpellContext setCastPositionModified(boolean castPositionModified) {
        properties.put("castPositionModified", castPositionModified);
        return this;
    }

    public List<LivingEntity> getHit() {
        return (List<LivingEntity>) properties.get("hit");
    }

    public SpellContext setHit(List<LivingEntity> hit) {
        properties.put("hit",hit);
        return this;
    }

    public int getSplit() {
        return properties.containsKey("split") ? (int) properties.get("split") : 0 ;
    }

    public SpellContext setSplit(int split) {
        properties.put("split", split);
        return this;
    }

    public LivingEntity getHomingTarget() {
        return (LivingEntity) properties.get("homingTarget");
    }

    public SpellContext setHomingTarget(LivingEntity homingTarget) {
        properties.put("homingTarget", homingTarget);
        return this;
    }

    public boolean isPiercing(){
        return properties.containsKey("piercing") && (boolean) properties.get("piercing");
    }
    public SpellContext setPiercing(boolean piercing) {
        properties.put("piercing", piercing);
        return this;
    }

    public boolean isCanHitCaster(){
        return properties.containsKey("hits_caster") && (boolean) properties.get("hits_caster");
    }
    public SpellContext setCanHitCaster(boolean hitcaster) {
        properties.put("hits_caster", hitcaster);
        return this;
    }

    public SpellContext clone() {
        return new SpellContext().setProperties((HashMap<String, Object>) properties.clone());
    }

}
