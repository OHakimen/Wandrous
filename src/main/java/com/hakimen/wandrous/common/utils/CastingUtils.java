package com.hakimen.wandrous.common.utils;

import com.hakimen.wandrous.common.registers.SpellRegister;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStack;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.spell.effects.modifiers.MultiCastEffect;
import com.hakimen.wandrous.common.spell.effects.spells.GreekLetterSpellEffect;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

import static com.hakimen.wandrous.config.ServerConfig.STRENGTH_DAMAGE_MODIFIER;

public class CastingUtils {
    public int idx;

    public List<ItemStack> toConsumeCharges = new ArrayList<>();

    public Node<SpellStack> makeCastingTree(List<SpellStack> effects, List<SpellStack> alleffects) {
        if (idx > effects.size() - 1) {
            return new Node<SpellStack>(null);
        }

        Node<SpellStack> tree = new Node<SpellStack>(effects.get(idx++));

        if(tree.getData().getCharges() == 0 && tree.getData().hasCharges() && !tree.getData().isCopy()){
            tree = new Node<SpellStack>(new SpellStack(SpellRegister.DUMMY.get(), 0));
        }

        if (tree.getData().getEffect() instanceof MultiCastEffect effect) {
            int casts = Math.min(effect.getCastCount(), effects.size());
            for (int i = 0; i < casts; i++) {
                Node<SpellStack> cast = makeCastingTree(effects, alleffects);
                if (cast.getData() != null) {
                    cast.setParent(tree);
                    tree.addChild(cast);
                }
            }
        } else if (tree.getData().getEffect() instanceof GreekLetterSpellEffect greekLetterSpellEffect) {
            Node<SpellStack> cast = greekLetterSpellEffect.apply(tree, this, alleffects);

            if (cast != null) {
                if (cast.getData().getEffect().hasKind(SpellEffect.TRIGGER) || cast.getData().getEffect().hasKind(SpellEffect.MODIFIER) || cast.getData().getEffect().hasKind(SpellEffect.TIMER)) {
                    Node<SpellStack> cast2 = makeCastingTree(effects, alleffects);
                    if (cast2.getData() != null) {
                        cast2.setParent(cast);
                        cast.addChild(cast2);
                    }
                }
                cast.setParent(tree);
                tree.addChild(cast);
            } else {
                Node<SpellStack> thing = new Node<SpellStack>(new SpellStack(SpellRegister.DUMMY.get(), 0));
                thing.setParent(tree);
                tree.addChild(thing);
            }
        } else if (tree.getData().getEffect().hasKind(SpellEffect.TRIGGER) || tree.getData().getEffect().hasKind(SpellEffect.MODIFIER) || tree.getData().getEffect().hasKind(SpellEffect.TIMER)) {
            Node<SpellStack> cast = makeCastingTree(effects, alleffects);
            if (cast.getData() != null) {
                cast.setParent(tree);
                tree.addChild(cast);
            }
        }

        if((tree.getData().hasCharges() && tree.getData().getCharges() != 0) || !tree.getData().hasCharges() || !tree.getData().isCopy()) {
            if (tree.getData().hasCharges()) {
                toConsumeCharges.add(tree.getData().getReferenceStack());
            }
        }
        return tree;
    }

    public static int calculateManaCost(Node<SpellStack> castTree) {
        int cost = castTree.getData().getEffect().getStatus().getManaDrain();

        for (Node<SpellStack> child : castTree.getChildren()) {
            cost += calculateManaCost(child);
        }
        return cost;
    }

    public static float calculateCastDelayMod(Node<SpellStack> castTree) {
        float castDelay = castTree.getData().getEffect().getStatus().getCastDelayMod();

        for (Node<SpellStack> child : castTree.getChildren()) {
            castDelay += calculateCastDelayMod(child);
        }

        return castDelay;
    }

    public static float calculateRechargeSpeedMod(Node<SpellStack> castTree) {
        float rechargeTimeMod = castTree.getData().getEffect().getStatus().getRechargeTimeMod();

        for (Node<SpellStack> child : castTree.getChildren()) {
            rechargeTimeMod += calculateRechargeSpeedMod(child);
        }

        return rechargeTimeMod;
    }

    public static void castSpells(Entity entity, ItemStack wand, Level pLevel, Vec3 location, Node<SpellStack> toCast) {
        SpellContext context = new SpellContext()
                .setCaster(entity)
                .setWand(wand)
                .setLevel(pLevel)
                .setLocation(location)
                .setStatus(new SpellStatus())
                .setNode(toCast)
                .setSplit(0)
                .setOriginalCaster(entity)
                .setPiercing(false)
                .setCastPositionModified(false)
                .setHit(new ArrayList<>());

        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity.hasEffect(MobEffects.DAMAGE_BOOST)) {
                context.getStatus().setDamageMod((float) ((livingEntity.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() + 1) * STRENGTH_DAMAGE_MODIFIER.get()));
            }
        }

        toCast.getData().getEffect().cast(context);
    }

    public static SpellStatus mergeStatus(SpellStatus first, SpellStatus second) {

        SpellStatus status = new SpellStatus();

        status.setDamage(first.getRawDamage());
        status.setLifeTime(first.getLifeTime());
        status.setRadius(first.getRadius());

        status.setCastDelayMod(first.getCastDelayMod() + second.getCastDelayMod());
        status.setDamageMod(first.getDamageMod() + second.getDamageMod());
        status.setSpeedMod(first.getSpeedMod() + second.getSpeedMod());
        status.setCritChance(first.getCritChance() + second.getCritChance());
        status.setSpreadMod(first.getSpreadMod() + second.getSpreadMod());
        status.setRadiusMod(first.getRadiusMod() + second.getRadiusMod());
        status.setLifetimeMod(first.getLifetimeMod() + second.getLifetimeMod());

        status.setSpeed(first.getRawSpeed() + second.getRawSpeed());
        status.setSpread(first.getRawSpread() + second.getRawSpread());

        return status;
    }

    public static void clearMods(SpellContext spellContext){
        spellContext
                .setStatus(new SpellStatus())
                .setSplit(0)
                .setPiercing(false)
                .setCastPositionModified(false);
    }
}
