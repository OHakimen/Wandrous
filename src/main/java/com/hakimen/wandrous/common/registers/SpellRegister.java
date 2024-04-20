package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.custom.register.WandrousRegistries;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.spell.effects.modifiers.*;
import com.hakimen.wandrous.common.spell.effects.modifiers.SpellHitModifierEffect;
import com.hakimen.wandrous.common.spell.effects.spells.ExplosionEffect;
import com.hakimen.wandrous.common.spell.effects.spells.projectiles.FireballSpellEffect;
import com.hakimen.wandrous.common.spell.effects.spells.projectiles.SnowballSpellEffect;
import com.hakimen.wandrous.common.spell.effects.spells.TeleportEffect;
import com.hakimen.wandrous.common.spell.effects.spells.summon_spells.SummonConjuredBlock;
import com.hakimen.wandrous.common.spell.effects.spells.summon_spells.SummonEntityEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class SpellRegister {
    public static final DeferredRegister<SpellEffect> SPELL_EFFECTS = DeferredRegister.create(WandrousRegistries.SPELLS_REGISTER, Wandrous.MODID);

    public static final DeferredHolder<SpellEffect, SpellEffect> EXPLOSION = SPELL_EFFECTS.register("explosion", () -> new ExplosionEffect(2));
    public static final DeferredHolder<SpellEffect, SpellEffect> MAJOR_EXPLOSION = SPELL_EFFECTS.register("major_explosion", () -> new ExplosionEffect(5));

    public static final DeferredHolder<SpellEffect, SpellEffect> SNOWBALL = SPELL_EFFECTS.register("snowball", () -> new SnowballSpellEffect(false));
    public static final DeferredHolder<SpellEffect, SpellEffect> TRIGGER_SNOWBALL = SPELL_EFFECTS.register("trigger_snowball", () -> new SnowballSpellEffect(true));

    public static final DeferredHolder<SpellEffect, SpellEffect> FIREBALL = SPELL_EFFECTS.register("fireball", () -> new FireballSpellEffect(false));
    public static final DeferredHolder<SpellEffect, SpellEffect> TRIGGER_FIREBALL = SPELL_EFFECTS.register("trigger_fireball", () -> new FireballSpellEffect(true));

    public static final DeferredHolder<SpellEffect, SpellEffect> TELEPORT = SPELL_EFFECTS.register("teleport", TeleportEffect::new);
    public static final DeferredHolder<SpellEffect, SpellEffect> LIGHTNING_BOLT = SPELL_EFFECTS.register("lightning_bolt", () -> new SummonEntityEffect(EntityType.LIGHTNING_BOLT, 70));

    public static final DeferredHolder<SpellEffect, SpellEffect> FREEZING_CHARGE = SPELL_EFFECTS.register("freezing_charge", () -> new SpellHitModifierEffect(
            entity -> {
                if(entity instanceof LivingEntity livingEntity){
                    livingEntity.addEffect(new MobEffectInstance(EffectRegister.FREEZING.get(), 30 * 20));
                }
            },
            (level, blockPos, blockState) -> {
                Iterator positions = BlockPos.betweenClosed(blockPos.offset(-5, -5, -5), blockPos.offset(5, 5, 5)).iterator();

                while(positions.hasNext()){
                    BlockPos pos = (BlockPos)positions.next();
                    if(pos.closerToCenterThan(blockPos.getCenter(), 5)){
                        if(level.getBlockState(pos).is(Blocks.WATER)){
                            level.setBlockAndUpdate(pos, Blocks.FROSTED_ICE.defaultBlockState());
                        }else if(!level.getBlockState(pos).is(Blocks.AIR) && Block.isShapeFullBlock(level.getBlockState(pos).getShape(level,pos)) && !level.getBlockState(pos).is(Blocks.FROSTED_ICE) && level.getBlockState(pos.above()).is(Blocks.AIR)){
                            level.setBlockAndUpdate(pos.above(), Blocks.SNOW.defaultBlockState());
                        }
                    }
                }

            },
            50
    ));

    public static final DeferredHolder<SpellEffect, SpellEffect> IGNEOUS_CHARGE = SPELL_EFFECTS.register("igneous_charge", () -> new SpellHitModifierEffect(
            entity -> {
                if(entity instanceof LivingEntity livingEntity){
                    livingEntity.addEffect(new MobEffectInstance(EffectRegister.IGNITE.get(), 30 * 20));
                }
            },
            (level, blockPos, blockState) -> {
                Iterator positions = BlockPos.betweenClosed(blockPos.offset(-5, -5, -5), blockPos.offset(5, 5, 5)).iterator();

                while(positions.hasNext()){
                    BlockPos pos = (BlockPos)positions.next();
                    if(!level.getBlockState(pos).is(Blocks.AIR) && Block.isShapeFullBlock(level.getBlockState(pos).getShape(level,pos)) && level.getBlockState(pos.above()).is(Blocks.AIR) && pos.closerToCenterThan(blockPos.getCenter(), 5)){
                        level.setBlockAndUpdate(pos.above(), Blocks.FIRE.defaultBlockState());
                    }
                }
            },50
    ));

    public static final DeferredHolder<SpellEffect, SpellEffect> POISON_CHARGE = SPELL_EFFECTS.register("poison_charge", () -> new SpellHitModifierEffect(
            entity -> {
                if(entity instanceof LivingEntity livingEntity){
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 30 * 20,1));
                }
            },
            (level, blockPos, blockState) -> {
                AreaEffectCloud cloud = new AreaEffectCloud(level, blockPos.getX(), blockPos.getY()+1, blockPos.getZ());
                cloud.addEffect(new MobEffectInstance(MobEffects.POISON, 30 * 20,1));

                level.addFreshEntity(cloud);
            },50
    ));


    public static final DeferredHolder<SpellEffect, SpellEffect> DOUBLE_CAST = SPELL_EFFECTS.register("double_cast", () -> new MulticastEffect(2));
    public static final DeferredHolder<SpellEffect, SpellEffect> TRIPLE_CAST = SPELL_EFFECTS.register("triple_cast", () -> new MulticastEffect(3));

    public static final DeferredHolder<SpellEffect, SpellEffect> SMALL_DELAY_CAST = SPELL_EFFECTS.register("small_delay_cast", () -> new DelayCastEffect(1));
    public static final DeferredHolder<SpellEffect, SpellEffect> MEDIUM_DELAY_CAST = SPELL_EFFECTS.register("medium_delay_cast", () -> new DelayCastEffect(5));
    public static final DeferredHolder<SpellEffect, SpellEffect> BIG_DELAY_CAST = SPELL_EFFECTS.register("big_delay_cast", () -> new DelayCastEffect(10));

    public static final DeferredHolder<SpellEffect, SpellEffect> DIVIDE_BY_2 = SPELL_EFFECTS.register("divide_by_2", () -> new DivideBySpellEffect(2));
    public static final DeferredHolder<SpellEffect, SpellEffect> DIVIDE_BY_3 = SPELL_EFFECTS.register("divide_by_3", () -> new DivideBySpellEffect(3));
    public static final DeferredHolder<SpellEffect, SpellEffect> DIVIDE_BY_4 = SPELL_EFFECTS.register("divide_by_4", () -> new DivideBySpellEffect(4));

    public static final DeferredHolder<SpellEffect, SpellEffect> SPREAD_CAST = SPELL_EFFECTS.register("spread_cast", () -> new SpreadCastEffect(5));
    public static final DeferredHolder<SpellEffect, SpellEffect> HEXAGON_CAST = SPELL_EFFECTS.register("hexagon_cast", () -> new SphericalCastEffect(6));

    public static final DeferredHolder<SpellEffect, SpellEffect> ACCELERATE_CAST = SPELL_EFFECTS.register("accelerate_cast", () -> new StatusModifierEffect(
            new SpellStatus()
                    .setManaDrain(30)
                    .setSpeedMod(1)
    ));

    public static final DeferredHolder<SpellEffect, SpellEffect> ADD_MANA = SPELL_EFFECTS.register("add_mana", () -> new StatusModifierEffect(
            new SpellStatus()
                    .setManaDrain(-50)
    ));

    public static final DeferredHolder<SpellEffect, SpellEffect> CRITICAL_PLUS = SPELL_EFFECTS.register("critical_plus", () -> new StatusModifierEffect(
            new SpellStatus()
                    .setCritChance(0.15f)
                    .setManaDrain(30)
    ));

    public static final DeferredHolder<SpellEffect, SpellEffect> INCREASE_LIFETIME = SPELL_EFFECTS.register("increase_lifetime", () -> new StatusModifierEffect(
            new SpellStatus()
                    .setLifetimeMod(0.15f)
                    .setManaDrain(30)
    ));

    public static final DeferredHolder<SpellEffect, SpellEffect> CONJURE_LIGHT = SPELL_EFFECTS.register("conjure_light", () -> new SummonConjuredBlock(BlockRegister.CONJURED_LIGHT_BLOCK.get().defaultBlockState(), 20));
    public static final DeferredHolder<SpellEffect, SpellEffect> CONJURE_BLOCK = SPELL_EFFECTS.register("conjure_block", () -> new SummonConjuredBlock(BlockRegister.CONJURED_BLOCK.get().defaultBlockState(), 20));

    public static void register(IEventBus bus) {
        SPELL_EFFECTS.register(bus);
    }
}
