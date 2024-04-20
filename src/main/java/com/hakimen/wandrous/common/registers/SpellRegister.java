package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.custom.register.WandrousRegistries;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.spell.effects.modifiers.*;
import com.hakimen.wandrous.common.spell.effects.spells.ExplosionEffect;
import com.hakimen.wandrous.common.spell.effects.spells.FireballSpellEffect;
import com.hakimen.wandrous.common.spell.effects.spells.SnowballSpellEffect;
import com.hakimen.wandrous.common.spell.effects.spells.TeleportEffect;
import com.hakimen.wandrous.common.spell.effects.spells.summon_spells.SummonBeeEffect;
import com.hakimen.wandrous.common.spell.effects.spells.summon_spells.SummonConjuredBlock;
import com.hakimen.wandrous.common.spell.effects.spells.summon_spells.SummonEntityEffect;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SpellRegister {
    public static final DeferredRegister<SpellEffect> SPELL_EFFECTS = DeferredRegister.create(WandrousRegistries.SPELLS_REGISTER, Wandrous.MODID);

    public static final DeferredHolder<SpellEffect, SpellEffect> EXPLOSION = SPELL_EFFECTS.register("explosion", () -> new ExplosionEffect(2));
    public static final DeferredHolder<SpellEffect, SpellEffect> MAJOR_EXPLOSION = SPELL_EFFECTS.register("major_explosion", () -> new ExplosionEffect(5));

    public static final DeferredHolder<SpellEffect, SpellEffect> SNOWBALL = SPELL_EFFECTS.register("snowball", () -> new SnowballSpellEffect(false));
    public static final DeferredHolder<SpellEffect, SpellEffect> TRIGGER_SNOWBALL = SPELL_EFFECTS.register("trigger_snowball", () -> new SnowballSpellEffect(true));

    public static final DeferredHolder<SpellEffect, SpellEffect> FIREBALL = SPELL_EFFECTS.register("fireball", () -> new FireballSpellEffect(false));
    public static final DeferredHolder<SpellEffect, SpellEffect> TRIGGER_FIREBALL = SPELL_EFFECTS.register("trigger_fireball", () -> new FireballSpellEffect(true));

    public static final DeferredHolder<SpellEffect, SpellEffect> TELEPORT = SPELL_EFFECTS.register("teleport", TeleportEffect::new);
    public static final DeferredHolder<SpellEffect, SpellEffect> LIGHTINING_BOLT = SPELL_EFFECTS.register("lighting_bolt", () -> new SummonEntityEffect(EntityType.LIGHTNING_BOLT,70));
    public static final DeferredHolder<SpellEffect, SpellEffect> SUMMON_BEE = SPELL_EFFECTS.register("summon_bee", SummonBeeEffect::new);

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

    public static final DeferredHolder<SpellEffect, SpellEffect> CONJURE_LIGHT = SPELL_EFFECTS.register("conjure_light", () ->new SummonConjuredBlock(BlockRegister.CONJURED_LIGHT_BLOCK.get().defaultBlockState(),20));
    public static final DeferredHolder<SpellEffect, SpellEffect> CONJURE_BLOCK = SPELL_EFFECTS.register("conjure_block", () ->new SummonConjuredBlock(BlockRegister.CONJURED_BLOCK.get().defaultBlockState(),20));

    public static void register(IEventBus bus){
        SPELL_EFFECTS.register(bus);
    }
}
