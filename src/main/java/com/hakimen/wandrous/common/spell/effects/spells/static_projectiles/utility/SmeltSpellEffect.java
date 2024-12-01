package com.hakimen.wandrous.common.spell.effects.spells.static_projectiles.utility;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Optional;

public class SmeltSpellEffect extends SpellEffect {
    public SmeltSpellEffect() {
        setKind(SPELL);
        setStatus(new SpellStatus()
                .setManaDrain(40)
                .setRadius(2)
        );
    }

    @Override
    public void cast(SpellContext context) {
        context.mergeStatus(getStatus());

        Level level = context.getLevel();
        float radius = context.getStatus().getRadius();
        AABB aabb = AABB.ofSize(context.getLocation(), radius, radius, radius);
        List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, aabb);
        for (ItemEntity item : items) {
            Optional<RecipeHolder<SmeltingRecipe>> recipeOptional = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(item.getItem()), level);
            if (recipeOptional.isPresent()) {
                RecipeHolder<SmeltingRecipe> smeltingRecipeRecipeHolder = recipeOptional.get();
                ItemStack stack = smeltingRecipeRecipeHolder.value().getResultItem(level.registryAccess());
                item.getItem().shrink(1);
                double x = item.getPosition(0).x;
                double y = item.getPosition(0).y;
                double z = item.getPosition(0).z;
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.FLAME, x, y, z, 10, 0.1 * radius, 0.1 * radius, 0.1 * radius, 0.01f);
                }
                level.addFreshEntity(
                        new ItemEntity(level, x, y, z, stack.copyWithCount(1))
                );
                level.addFreshEntity(
                        new ExperienceOrb(level, x, y, z, (int) smeltingRecipeRecipeHolder.value().getExperience())
                );
                break;
            }
        }
    }
}
