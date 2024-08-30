package com.hakimen.wandrous.common.datagen;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.data.Glyph;
import com.hakimen.wandrous.common.datagen.recipes.ArcaneInscriberRecipeBuilder;
import com.hakimen.wandrous.common.item.InscribedLensItem;
import com.hakimen.wandrous.common.registers.GlyphRegister;
import com.hakimen.wandrous.common.registers.ItemRegister;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {
    public RecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        arcaneInscriberRecipes(pRecipeOutput);
        craftingTableRecipes(pRecipeOutput);
    }

    public void craftingTableRecipes(RecipeOutput pRecipeOutput) {
        ItemStack stack = ItemRegister.INSCRIBED_LENS.get().getDefaultInstance();

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.BIND.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.CHAIN)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, "bind_lens");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.CONTROL.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.AMETHYST_SHARD)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, "control_lens");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.DESTINY.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.ENDER_EYE)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, "destiny_lens");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.FOCUS.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")

                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.GLOWSTONE_DUST)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, "focus_lens");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.GUIDANCE.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.REDSTONE)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, "guidance_lens");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.KNOWLEDGE.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.BOOK)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, "knowledge_lens");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.MIND.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.GOLD_NUGGET)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, "mind_lens");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.NEW.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.PAPER)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, "new_lens");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.POWER.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.GUNPOWDER)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, "power_lens");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.TINKER.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.REPEATER)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, "tinker_lens");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.VITALITY.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.GOLDEN_CARROT)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, "vitality_lens");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.WEAVE.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.STRING)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, "weave_lens");
    }

    static List<Glyph> TRIGGER = List.of(
            GlyphRegister.TINKER.get(),
            GlyphRegister.CONTROL.get()
    );

    static List<Glyph> TIMER = List.of(
            GlyphRegister.DESTINY.get(),
            GlyphRegister.FOCUS.get()
    );

    private void projectiles(RecipeOutput pRecipeOutput) {
        inscriberRecipe(pRecipeOutput, "glimmering_bolt", List.of(
                        GlyphRegister.NEW.get(),
                        GlyphRegister.CONTROL.get()
                ),
                Ingredient.of(Items.AMETHYST_SHARD),
                ItemRegister.GLIMMERING_BOLT_SPELL.get().getDefaultInstance(),
                100,
                1);


        inscriberRecipe(pRecipeOutput, "trigger_glimmering_bolt",
                TRIGGER,
                Ingredient.of(ItemRegister.GLIMMERING_BOLT_SPELL.get()),
                ItemRegister.TRIGGER_GLIMMERING_BOLT_SPELL.get().getDefaultInstance(),
                100,
                1);

        inscriberRecipe(pRecipeOutput, "timer_glimmering_bolt",
                TIMER,
                Ingredient.of(ItemRegister.GLIMMERING_BOLT_SPELL.get()),
                ItemRegister.TIMER_GLIMMERING_BOLT_SPELL.get().getDefaultInstance(),
                100,
                1);

        inscriberRecipe(pRecipeOutput, "fireball", List.of(
                GlyphRegister.WEAVE.get(),
                GlyphRegister.POWER.get(),
                GlyphRegister.POWER.get(),
                GlyphRegister.POWER.get(),
                GlyphRegister.POWER.get(),
                GlyphRegister.FOCUS.get(),
                GlyphRegister.CONTROL.get(),
                GlyphRegister.CONTROL.get()
        ), Ingredient.of(Items.FIRE_CHARGE), ItemRegister.FIREBALL_SPELL.get().getDefaultInstance(), 100, 2);

        inscriberRecipe(pRecipeOutput, "trigger_fireball",
                TRIGGER,
                Ingredient.of(ItemRegister.FIREBALL_SPELL.get()),
                ItemRegister.TRIGGER_FIREBALL_SPELL.get().getDefaultInstance(),
                100,
                2);

        inscriberRecipe(pRecipeOutput, "timer_fireball",
                TIMER,
                Ingredient.of(ItemRegister.FIREBALL_SPELL.get()),
                ItemRegister.TIMER_FIREBALL_SPELL.get().getDefaultInstance(),
                100,
                2);


        inscriberRecipe(pRecipeOutput, "bomb",
                List.of(
                        GlyphRegister.NEW.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(Items.GUNPOWDER),
                ItemRegister.BOMB_SPELL.get().getDefaultInstance(),
                100,
                3);

        inscriberRecipe(pRecipeOutput, "black_hole",
                List.of(
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.ENDER_EYE),
                ItemRegister.BLACK_HOLE_SPELL.get().getDefaultInstance(),
                100,
                4
        );

        inscriberRecipe(pRecipeOutput, "timer_black_hole",
                TIMER,
                Ingredient.of(ItemRegister.BLACK_HOLE_SPELL.get()),
                ItemRegister.TIMER_BLACK_HOLE_SPELL.get().getDefaultInstance(),
                100,
                4);


        inscriberRecipe(pRecipeOutput, "sonic_boom",
                List.of(
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.VITALITY.get()
                ),
                Ingredient.of(Items.ECHO_SHARD),
                ItemRegister.SONIC_BOOM_SPELL.get().getDefaultInstance(),
                100,
                4);


        inscriberRecipe(pRecipeOutput, "trigger_sonic_boom",
                TRIGGER,
                Ingredient.of(ItemRegister.SONIC_BOOM_SPELL.get()),
                ItemRegister.TRIGGER_SONIC_BOOM_SPELL.get().getDefaultInstance(),
                100,
                4);

        inscriberRecipe(pRecipeOutput, "timer_sonic_boom",
                TIMER,
                Ingredient.of(ItemRegister.SONIC_BOOM_SPELL.get()),
                ItemRegister.TIMER_SONIC_BOOM_SPELL.get().getDefaultInstance(),
                100,
                4);

        inscriberRecipe(pRecipeOutput, "chain_shot",
                List.of(
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(ItemRegister.TEALESTITE_SHARD.get()),
                ItemRegister.CHAIN_SHOT_SPELL.get().getDefaultInstance(),
                100,
                3);

        inscriberRecipe(pRecipeOutput, "trigger_chain_shot",
                TRIGGER,
                Ingredient.of(ItemRegister.CHAIN_SHOT_SPELL.get()),
                ItemRegister.TRIGGER_CHAIN_SHOT_SPELL.get().getDefaultInstance(),
                100,
                3);

        inscriberRecipe(pRecipeOutput, "timer_chain_shot",
                TIMER,
                Ingredient.of(ItemRegister.CHAIN_SHOT_SPELL.get()),
                ItemRegister.TIMER_CHAIN_SHOT_SPELL.get().getDefaultInstance(),
                100,
                3);
    }

    private void staticProjectiles(RecipeOutput pRecipeOutput) {
        inscriberRecipe(pRecipeOutput, "explosion", List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.TNT),
                ItemRegister.EXPLOSION_SPELL.get().getDefaultInstance(),
                100,
                1);

        inscriberRecipe(pRecipeOutput, "major_explosion", List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.TNT),
                ItemRegister.MAJOR_EXPLOSION_SPELL.get().getDefaultInstance(),
                100,
                2);

        inscriberRecipe(pRecipeOutput, "gust", List.of(
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.WIND_CHARGE),
                ItemRegister.GUST_SPELL.get().getDefaultInstance(),
                100,
                2);


        inscriberRecipe(pRecipeOutput, "lighting_bolt", List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.LIGHTNING_ROD),
                ItemRegister.LIGHTING_BOLT_SPELL.get().getDefaultInstance(),
                100,
                2);


        inscriberRecipe(pRecipeOutput, "chain_prison", List.of(
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.CHAIN),
                ItemRegister.CHAIN_PRISON_SPELL.get().getDefaultInstance(),
                100,
                3);

        inscriberRecipe(pRecipeOutput, "conjure_webs", List.of(
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.SPIDER_EYE),
                ItemRegister.CONJURE_WEBS_SPELL.get().getDefaultInstance(),
                100,
                2);


        inscriberRecipe(pRecipeOutput, "conjure_light", List.of(
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.GLOWSTONE_DUST),
                ItemRegister.CONJURE_LIGHT_SPELL.get().getDefaultInstance(),
                100,
                1);

        inscriberRecipe(pRecipeOutput, "conjure_block", List.of(
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Tags.Items.GLASS_BLOCKS),
                ItemRegister.CONJURE_BLOCK_SPELL.get().getDefaultInstance(),
                100,
                1);


        inscriberRecipe(pRecipeOutput, "collect", List.of(
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.HOPPER),
                ItemRegister.COLLECT_SPELL.get().getDefaultInstance(),
                100,
                2);

        inscriberRecipe(pRecipeOutput, "teleport", List.of(
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.ENDER_PEARL),
                ItemRegister.TELEPORT_SPELL.get().getDefaultInstance(),
                100,
                2);


        inscriberRecipe(pRecipeOutput, "swap_teleport", List.of(
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.ENDER_PEARL),
                ItemRegister.SWAP_TELEPORT_SPELL.get().getDefaultInstance(),
                100,
                2);

        inscriberRecipe(pRecipeOutput, "homebringer_teleport", List.of(
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.ENDER_PEARL),
                ItemRegister.HOME_BRINGER_TELEPORT_SPELL.get().getDefaultInstance(),
                100,
                2);

        inscriberRecipe(pRecipeOutput, "drill", List.of(
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(Items.IRON_PICKAXE),
                ItemRegister.DRILL_SPELL.get().getDefaultInstance(),
                100,
                1);

        inscriberRecipe(pRecipeOutput, "chainsaw", List.of(
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(Items.IRON_AXE),
                ItemRegister.CHAINSAW_SPELL.get().getDefaultInstance(),
                100,
                1);

        inscriberRecipe(pRecipeOutput, "giga_drill", List.of(
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(Items.DIAMOND_PICKAXE),
                ItemRegister.GIGA_DRILL_SPELL.get().getDefaultInstance(),
                100,
                2);
    }

    private void movers(RecipeOutput pRecipeOutput) {
        inscriberRecipe(pRecipeOutput, "homing", List.of(
                GlyphRegister.GUIDANCE.get(),
                GlyphRegister.GUIDANCE.get(),
                GlyphRegister.WEAVE.get(),
                GlyphRegister.WEAVE.get(),
                GlyphRegister.FOCUS.get(),
                GlyphRegister.FOCUS.get(),
                GlyphRegister.CONTROL.get(),
                GlyphRegister.CONTROL.get()
        ), Ingredient.of(Items.ENDER_EYE), ItemRegister.HOMING_SPELL.get().getDefaultInstance(), 100, 2);

        inscriberRecipe(pRecipeOutput, "boomerang", List.of(
                GlyphRegister.GUIDANCE.get(),
                GlyphRegister.GUIDANCE.get(),
                GlyphRegister.WEAVE.get(),
                GlyphRegister.WEAVE.get(),
                GlyphRegister.WEAVE.get(),
                GlyphRegister.WEAVE.get(),
                GlyphRegister.TINKER.get(),
                GlyphRegister.DESTINY.get()
        ), Ingredient.of(Items.SLIME_BALL), ItemRegister.BOOMERANG_SPELL.get().getDefaultInstance(), 100, 2);

    }

    public void modifiers(RecipeOutput pRecipeOutput) {
        inscriberRecipe(pRecipeOutput,
                "small_delay_cast",
                List.of(
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.DESTINY.get()
                ),
                Ingredient.of(Items.CLOCK),
                ItemRegister.SMALL_DELAY_CAST_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "medium_delay_cast",
                List.of(
                        GlyphRegister.BIND.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.DESTINY.get()
                ),
                Ingredient.of(ItemRegister.SMALL_DELAY_CAST_SPELL.get()),
                ItemRegister.MEDIUM_DELAY_CAST_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "big_delay_cast",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.DESTINY.get()
                ),
                Ingredient.of(ItemRegister.MEDIUM_DELAY_CAST_SPELL.get()),
                ItemRegister.BIG_DELAY_CAST_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "freezing_charge",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(Items.PACKED_ICE),
                ItemRegister.FREEZING_CHARGE_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "igneous_charge",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(Items.MAGMA_BLOCK),
                ItemRegister.IGNEOUS_CHARGE_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "poison_charge",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(Items.POISONOUS_POTATO),
                ItemRegister.POISON_CHARGE_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "crumbling_charge",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(Items.GUNPOWDER),
                ItemRegister.CRUMBLING_CHARGE_SPELL.get().getDefaultInstance(),
                100,
                2
        );
    }

    private void arcaneInscriberRecipes(RecipeOutput pRecipeOutput) {
        projectiles(pRecipeOutput);
        staticProjectiles(pRecipeOutput);
        movers(pRecipeOutput);
    }


    public static void inscriberRecipe(RecipeOutput out, String name, List<Glyph> glyphs, Ingredient onSlate, ItemStack output, int ticks, int tier) {
        ArcaneInscriberRecipeBuilder builder = new ArcaneInscriberRecipeBuilder()
                .setGlyphs(glyphs)
                .setOnSlate(onSlate)
                .setTier(tier)
                .setTicks(ticks)
                .setOutput(output);

        builder.save(out, location(name));
    }

    private static ResourceLocation location(String name) {
        return ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, name);
    }
}
