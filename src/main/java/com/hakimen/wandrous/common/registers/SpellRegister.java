package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.custom.register.WandrousRegistries;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.spell.effects.modifiers.*;
import com.hakimen.wandrous.common.spell.effects.modifiers.charges.*;
import com.hakimen.wandrous.common.spell.effects.modifiers.location.RelativeCastEffect;
import com.hakimen.wandrous.common.spell.effects.modifiers.location.TeleportCastEffect;
import com.hakimen.wandrous.common.spell.effects.spells.BestowSpellEffect;
import com.hakimen.wandrous.common.spell.effects.spells.projectiles.*;
import com.hakimen.wandrous.common.spell.effects.spells.static_projectiles.GustSpellEffect;
import com.hakimen.wandrous.common.spell.effects.spells.static_projectiles.*;
import com.hakimen.wandrous.common.spell.effects.spells.summon_spells.SummonConjuredBlockSpellEffect;
import com.hakimen.wandrous.common.spell.effects.spells.summon_spells.SummonEntityEffect;
import com.hakimen.wandrous.common.spell.effects.spells.summon_spells.SummonWebbingSpellEffect;
import com.hakimen.wandrous.common.spell.effects.spells.teleports.CollectEffect;
import com.hakimen.wandrous.common.spell.effects.spells.teleports.HomebringerTeleportEffect;
import com.hakimen.wandrous.common.spell.effects.spells.teleports.SwapTeleportEffect;
import com.hakimen.wandrous.common.spell.effects.spells.teleports.TeleportEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SpellRegister {
    public static final DeferredRegister<SpellEffect> SPELL_EFFECTS = DeferredRegister.create(WandrousRegistries.SPELLS_REGISTER, Wandrous.MODID);

    public static final DeferredHolder<SpellEffect, SpellEffect> EXPLOSION = SPELL_EFFECTS.register("explosion", () -> new ExplosionSpellEffect(2));
    public static final DeferredHolder<SpellEffect, SpellEffect> MAJOR_EXPLOSION = SPELL_EFFECTS.register("major_explosion", () -> new ExplosionSpellEffect(5));
    public static final DeferredHolder<SpellEffect, SpellEffect> BOMB = SPELL_EFFECTS.register("bomb", BombSpellEffect::new);
    public static final DeferredHolder<SpellEffect, SpellEffect> NUKE = SPELL_EFFECTS.register("nuke", NukeSpellEffect::new);

    public static final DeferredHolder<SpellEffect, SpellEffect> CHAIN_PRISON = SPELL_EFFECTS.register("chain_prison", ChainPrisonSpell::new);
    public static final DeferredHolder<SpellEffect, SpellEffect> GUST = SPELL_EFFECTS.register("gust", GustSpellEffect::new);

    public static final DeferredHolder<SpellEffect, SpellEffect> GLIMMERING_BOLT = SPELL_EFFECTS.register("glimmering_bolt", () -> new GlimmeringBoltSpellEffect(SpellEffect.SPELL));
    public static final DeferredHolder<SpellEffect, SpellEffect> TRIGGER_GLIMMERING_BOLT = SPELL_EFFECTS.register("trigger_glimmering_bolt", () -> new GlimmeringBoltSpellEffect(SpellEffect.TRIGGER));
    public static final DeferredHolder<SpellEffect, SpellEffect> TIMER_GLIMMERING_BOLT = SPELL_EFFECTS.register("timer_glimmering_bolt", () -> new GlimmeringBoltSpellEffect(SpellEffect.TIMER));

    public static final DeferredHolder<SpellEffect, SpellEffect> FIREBALL = SPELL_EFFECTS.register("fireball", () -> new FireboltSpellEffect(SpellEffect.SPELL));
    public static final DeferredHolder<SpellEffect, SpellEffect> TRIGGER_FIREBALL = SPELL_EFFECTS.register("trigger_fireball", () -> new FireboltSpellEffect(SpellEffect.TRIGGER));
    public static final DeferredHolder<SpellEffect, SpellEffect> TIMER_FIREBALL = SPELL_EFFECTS.register("timer_fireball", () -> new FireboltSpellEffect(SpellEffect.TIMER));

    public static final DeferredHolder<SpellEffect, SpellEffect> BLACK_HOLE = SPELL_EFFECTS.register("black_hole", () -> new BlackHoleSpellEffect(SpellEffect.SPELL));
    public static final DeferredHolder<SpellEffect, SpellEffect> TIMER_BLACK_HOLE = SPELL_EFFECTS.register("timer_black_hole", () -> new BlackHoleSpellEffect(SpellEffect.TIMER));

    public static final DeferredHolder<SpellEffect, SpellEffect> SONIC_BOOM = SPELL_EFFECTS.register("sonic_boom", () -> new SonicBoomSpellEffect(SpellEffect.SPELL));
    public static final DeferredHolder<SpellEffect, SpellEffect> TRIGGER_SONIC_BOOM = SPELL_EFFECTS.register("trigger_sonic_boom", () -> new SonicBoomSpellEffect(SpellEffect.TRIGGER));
    public static final DeferredHolder<SpellEffect, SpellEffect> TIMER_SONIC_BOOM = SPELL_EFFECTS.register("timer_sonic_boom", () -> new SonicBoomSpellEffect(SpellEffect.TIMER));

    public static final DeferredHolder<SpellEffect, SpellEffect> CHAIN_SHOT = SPELL_EFFECTS.register("chain_shot", () -> new ChainShotSpellEffect(SpellEffect.SPELL));
    public static final DeferredHolder<SpellEffect, SpellEffect> TRIGGER_CHAIN_SHOT = SPELL_EFFECTS.register("trigger_chain_shot", () -> new ChainShotSpellEffect(SpellEffect.TRIGGER));
    public static final DeferredHolder<SpellEffect, SpellEffect> TIMER_CHAIN_SHOT = SPELL_EFFECTS.register("timer_chain_shot", () -> new ChainShotSpellEffect(SpellEffect.TIMER));

    public static final DeferredHolder<SpellEffect, SpellEffect> TELEPORT = SPELL_EFFECTS.register("teleport", TeleportEffect::new);
    public static final DeferredHolder<SpellEffect, SpellEffect> SWAP_TELEPORT = SPELL_EFFECTS.register("swap_teleport", SwapTeleportEffect::new);
    public static final DeferredHolder<SpellEffect, SpellEffect> HOME_BRINGER_TELEPORT = SPELL_EFFECTS.register("home_bringer_teleport", HomebringerTeleportEffect::new);

    public static final DeferredHolder<SpellEffect, SpellEffect> COLLECT = SPELL_EFFECTS.register("collect", CollectEffect::new);

    public static final DeferredHolder<SpellEffect, SpellEffect> DOUBLE_SPLIT = SPELL_EFFECTS.register("double_split", () -> new SplitCastEffect(2));
    public static final DeferredHolder<SpellEffect, SpellEffect> TRIPLE_SPLIT = SPELL_EFFECTS.register("triple_split", () -> new SplitCastEffect(3));
    public static final DeferredHolder<SpellEffect, SpellEffect> QUAD_SPLIT = SPELL_EFFECTS.register("quad_split", () -> new SplitCastEffect(4));

    public static final DeferredHolder<SpellEffect, SpellEffect> LIGHTNING_BOLT = SPELL_EFFECTS.register("lightning_bolt", () -> new SummonEntityEffect(EntityType.LIGHTNING_BOLT, 70));

    public static final DeferredHolder<SpellEffect, SpellEffect> FREEZING_CHARGE = SPELL_EFFECTS.register("freezing_charge", () -> new FreezingChargeHitEffect(35));
    public static final DeferredHolder<SpellEffect, SpellEffect> IGNEOUS_CHARGE = SPELL_EFFECTS.register("igneous_charge", () -> new IgneousChargeHitEffect(35));
    public static final DeferredHolder<SpellEffect, SpellEffect> POISON_CHARGE = SPELL_EFFECTS.register("poison_charge", () -> new PoisonChargeHitEffect(35));
    public static final DeferredHolder<SpellEffect, SpellEffect> CRUMBLING_CHARGE = SPELL_EFFECTS.register("crumbling_charge", () -> new CrumblingChargeHitEffect(35));

    public static final DeferredHolder<SpellEffect, SpellEffect> DOUBLE_CAST = SPELL_EFFECTS.register("double_cast", () -> new MultiCastEffect(2));
    public static final DeferredHolder<SpellEffect, SpellEffect> TRIPLE_CAST = SPELL_EFFECTS.register("triple_cast", () -> new MultiCastEffect(3));

    public static final DeferredHolder<SpellEffect, SpellEffect> TELEPORT_CAST = SPELL_EFFECTS.register("teleport_cast", TeleportCastEffect::new);
    public static final DeferredHolder<SpellEffect, SpellEffect> LONG_DISTANCE_CAST = SPELL_EFFECTS.register("long_distance_cast", RelativeCastEffect::new);
    public static final DeferredHolder<SpellEffect, SpellEffect> LINE_CAST = SPELL_EFFECTS.register("line_cast", () -> new LineCastEffect(5));

    public static final DeferredHolder<SpellEffect, SpellEffect> DRILL = SPELL_EFFECTS.register("drill", () -> new DrillSpellEffect(40, 3).setMineAs(Items.IRON_PICKAXE.getDefaultInstance()));
    public static final DeferredHolder<SpellEffect, SpellEffect> GIGA_DRILL = SPELL_EFFECTS.register("giga_drill", () -> new DrillSpellEffect(80, 5).setMineAs(Items.DIAMOND_PICKAXE.getDefaultInstance()));

    public static final DeferredHolder<SpellEffect, SpellEffect> CHAINSAW = SPELL_EFFECTS.register("chainsaw", () -> new ChainsawSpellEffect(40, 3).setMineAs(Items.IRON_AXE.getDefaultInstance()));

    public static final DeferredHolder<SpellEffect, SpellEffect> SMALL_DELAY_CAST = SPELL_EFFECTS.register("small_delay_cast", () -> new DelayCastEffect(1));
    public static final DeferredHolder<SpellEffect, SpellEffect> MEDIUM_DELAY_CAST = SPELL_EFFECTS.register("medium_delay_cast", () -> new DelayCastEffect(5));
    public static final DeferredHolder<SpellEffect, SpellEffect> BIG_DELAY_CAST = SPELL_EFFECTS.register("big_delay_cast", () -> new DelayCastEffect(10));

    public static final DeferredHolder<SpellEffect, SpellEffect> DIVIDE_BY_2 = SPELL_EFFECTS.register("divide_by_2", () -> new DivideBySpellEffect(2));
    public static final DeferredHolder<SpellEffect, SpellEffect> DIVIDE_BY_3 = SPELL_EFFECTS.register("divide_by_3", () -> new DivideBySpellEffect(3));
    public static final DeferredHolder<SpellEffect, SpellEffect> DIVIDE_BY_4 = SPELL_EFFECTS.register("divide_by_4", () -> new DivideBySpellEffect(4));

    public static final DeferredHolder<SpellEffect, SpellEffect> SPREAD_CAST = SPELL_EFFECTS.register("spread_cast", () -> new SpreadCastSpellEffect(5));
    public static final DeferredHolder<SpellEffect, SpellEffect> HEXAGON_CAST = SPELL_EFFECTS.register("hexagon_cast", () -> new SphericalCastSpellEffect(6));

    public static final DeferredHolder<SpellEffect, SpellEffect> HEALTH_TO_POWER = SPELL_EFFECTS.register("health_to_power", HealthToPowerSpellEffect::new);

    public static final DeferredHolder<SpellEffect, SpellEffect> ACCELERATE_CAST = SPELL_EFFECTS.register("accelerate_cast", () -> new StatusModifierSpellEffect(
            new SpellStatus()
                    .setManaDrain(30)
                    .setSpeedMod(1)
    ));

    public static final DeferredHolder<SpellEffect, SpellEffect> ADD_MANA = SPELL_EFFECTS.register("add_mana", () -> new StatusModifierSpellEffect(
            new SpellStatus()
                    .setManaDrain(-50)
    ));

    public static final DeferredHolder<SpellEffect, SpellEffect> CRITICAL_PLUS = SPELL_EFFECTS.register("critical_plus", () -> new StatusModifierSpellEffect(
            new SpellStatus()
                    .setManaDrain(30)
                    .setCritChance(0.15f)
    ));

    public static final DeferredHolder<SpellEffect, SpellEffect> INCREASE_LIFETIME = SPELL_EFFECTS.register("increase_lifetime", () -> new StatusModifierSpellEffect(
            new SpellStatus()
                    .setManaDrain(30)
                    .setLifetimeMod(0.15f)
    ));

    public static final DeferredHolder<SpellEffect, SpellEffect> DECREASE_LIFETIME = SPELL_EFFECTS.register("decrease_lifetime", () -> new StatusModifierSpellEffect(
            new SpellStatus()
                    .setManaDrain(30)
                    .setLifetimeMod(-0.15f)
    ));

    public static final DeferredHolder<SpellEffect, SpellEffect> INCREASE_RANGE = SPELL_EFFECTS.register("increase_range", () -> new StatusModifierSpellEffect(
            new SpellStatus()
                    .setManaDrain(30)
                    .setRadiusMod(0.25f)
    ));

    public static final DeferredHolder<SpellEffect, SpellEffect> DECREASE_RANGE = SPELL_EFFECTS.register("decrease_range", () -> new StatusModifierSpellEffect(
            new SpellStatus()
                    .setManaDrain(30)
                    .setRadiusMod(-0.25f)
    ));

    public static final DeferredHolder<SpellEffect, SpellEffect> INCREASE_SPREAD = SPELL_EFFECTS.register("increase_spread", () -> new StatusModifierSpellEffect(
            new SpellStatus()
                    .setManaDrain(10)
                    .setSpreadMod(0.25f)
    ));
    public static final DeferredHolder<SpellEffect, SpellEffect> DECREASE_SPREAD = SPELL_EFFECTS.register("decrease_spread", () -> new StatusModifierSpellEffect(
            new SpellStatus()
                    .setManaDrain(10)
                    .setSpreadMod(-0.25f)
    ));

    public static final DeferredHolder<SpellEffect, SpellEffect> HOMING = SPELL_EFFECTS.register("homing", () -> new MoverSpellEffect(SpellMoverRegister.HOMING));
    public static final DeferredHolder<SpellEffect, SpellEffect> BOOMERANG = SPELL_EFFECTS.register("boomerang", () -> new MoverSpellEffect(SpellMoverRegister.BOOMERANG));

    public static final DeferredHolder<SpellEffect, SpellEffect> CONJURE_LIGHT = SPELL_EFFECTS.register("conjure_light", () -> new SummonConjuredBlockSpellEffect(BlockRegister.CONJURED_LIGHT_BLOCK.get().defaultBlockState(), 20));
    public static final DeferredHolder<SpellEffect, SpellEffect> CONJURE_BLOCK = SPELL_EFFECTS.register("conjure_block", () -> new SummonConjuredBlockSpellEffect(BlockRegister.CONJURED_BLOCK.get().defaultBlockState(), 20));
    public static final DeferredHolder<SpellEffect, SpellEffect> CONJURE_WEBS = SPELL_EFFECTS.register("conjure_webs", SummonWebbingSpellEffect::new);

    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_CURSE_HUNGER = SPELL_EFFECTS.register("curse_hunger",() -> new BestowSpellEffect(MobEffects.HUNGER));
    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_CURSE_DARKNESS = SPELL_EFFECTS.register("curse_darkness",() -> new BestowSpellEffect(MobEffects.DARKNESS));
    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_CURSE_WEAKNESS = SPELL_EFFECTS.register("curse_weakness",() -> new BestowSpellEffect(MobEffects.WEAKNESS));
    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_CURSE_NAUSEA = SPELL_EFFECTS.register("curse_nausea",() -> new BestowSpellEffect(MobEffects.CONFUSION));
    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_CURSE_GLOW = SPELL_EFFECTS.register("curse_glow",() -> new BestowSpellEffect(MobEffects.GLOWING));
    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_CURSE_LEVITATE = SPELL_EFFECTS.register("curse_levitate",() -> new BestowSpellEffect(MobEffects.LEVITATION));
    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_CURSE_SLOWNESS = SPELL_EFFECTS.register("curse_slowness",() -> new BestowSpellEffect(MobEffects.MOVEMENT_SLOWDOWN));
    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_CURSE_MINING_FATIGUE = SPELL_EFFECTS.register("curse_fatigue",() -> new BestowSpellEffect(MobEffects.DIG_SLOWDOWN));

    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_BLESSING_HASTE = SPELL_EFFECTS.register("blessing_haste",() -> new BestowSpellEffect(MobEffects.DIG_SPEED));
    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_BLESSING_REGENERATION = SPELL_EFFECTS.register("blessing_regeneration",() -> new BestowSpellEffect(MobEffects.REGENERATION));
    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_BLESSING_SATURATION = SPELL_EFFECTS.register("blessing_saturation",() -> new BestowSpellEffect(MobEffects.SATURATION));
    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_BLESSING_BOOST_HEALTH = SPELL_EFFECTS.register("blessing_boost_health",() -> new BestowSpellEffect(MobEffects.HEALTH_BOOST));
    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_BLESSING_RESISTANCE = SPELL_EFFECTS.register("blessing_resist",() -> new BestowSpellEffect(MobEffects.DAMAGE_RESISTANCE));
    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_BLESSING_NIGHT_VISION = SPELL_EFFECTS.register("blessing_night_vision",() -> new BestowSpellEffect(MobEffects.NIGHT_VISION));
    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_BLESSING_RESIST_FIRE = SPELL_EFFECTS.register("blessing_resist_fire",() -> new BestowSpellEffect(MobEffects.FIRE_RESISTANCE));
    public static final DeferredHolder<SpellEffect, SpellEffect> BESTOW_BLESSING_SPEED = SPELL_EFFECTS.register("blessing_speed",() -> new BestowSpellEffect(MobEffects.MOVEMENT_SPEED));

    public static void register(IEventBus bus) {
        SPELL_EFFECTS.register(bus);
    }
}
