package com.hakimen.wandrous.common.item;

import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.function.Supplier;

public class SpellEffectItem extends Item {

    Supplier<SpellEffect> spellEffect;

    public SpellEffectItem(Supplier<SpellEffect> spellEffect) {
        super(new Properties().stacksTo(1));
        this.spellEffect = spellEffect;
    }


    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {

        pTooltipComponents.add(Component.translatable(this.getDescriptionId() + ".desc").withStyle(Style.EMPTY.applyFormats(ChatFormatting.DARK_GRAY)));
        SpellStatus status = spellEffect.get().getStatus();

        pTooltipComponents.add(Component.literal(" "));
        if (status.getRawDamage() != 0) {
            pTooltipComponents.add(Component.literal("Damage %s".formatted((int) status.getRawDamage())));
        }
        if (status.getManaDrain() != 0) {
            pTooltipComponents.add(Component.literal("Mana Cost %s".formatted(status.getManaDrain())));
        }

        if (status.getSpeed() != 0) {
            pTooltipComponents.add(Component.literal("Speed %.2f".formatted(status.getSpeed())));
        }

        if (status.getSpread() != 0) {
            pTooltipComponents.add(Component.literal("Spread %.2f".formatted(status.getSpread())));
        }

        if (status.getLifeTime() != 0) {
            pTooltipComponents.add(Component.literal("Lifetime %.2fs".formatted(status.getLifeTime() / 20f)));
        }

        if (status.getRadius() != 0) {
            pTooltipComponents.add(Component.literal("Radius %.2f".formatted(status.getRadius())));
        }

        if (status.getSpeedMod() != 0) {
            pTooltipComponents.add(Component.literal("Speed Modifier %s".formatted(status.getSpeedMod() > 0 ? "+%.2f".formatted(status.getSpeedMod()) : "%.2f".formatted(status.getSpeedMod()))));
        }

        if (status.getSpreadMod() != 0) {
            pTooltipComponents.add(Component.literal("Spread Modifier %s".formatted(status.getSpreadMod() > 0 ? "+%.2f".formatted(status.getSpreadMod()) : "%.2f".formatted(status.getSpreadMod()))));
        }

        if (status.getCastDelayMod() != 0) {
            pTooltipComponents.add(Component.literal("Cast Delay Modifier %s".formatted(status.getCastDelayMod() > 0 ? "+%.2f".formatted(status.getCastDelayMod()) : "%.2f".formatted(status.getCastDelayMod()))));
        }

        if (status.getRechargeTimeMod() != 0) {
            pTooltipComponents.add(Component.literal("Recharge Time Modifier %s".formatted(status.getRechargeTimeMod() > 0 ? "+%.2f".formatted(status.getRechargeTimeMod()) : "%.2f".formatted(status.getRechargeTimeMod()))));
        }

        if (status.getDamageMod() != 0) {
            pTooltipComponents.add(Component.literal("Damage Modifier %s".formatted(status.getDamageMod() > 0 ? "+%.2f".formatted(status.getDamageMod()) : "%.2f".formatted(status.getDamageMod()))));
        }

        if (status.getCritChance() != 0) {
            pTooltipComponents.add(Component.literal("Crit Chance %s".formatted(status.getCritChance() > 0 ? "+%.2f".formatted(status.getCritChance()) : "%.2f".formatted(status.getCritChance()))));
        }

        if (status.getLifetimeMod() != 0) {
            pTooltipComponents.add(Component.literal("Lifetime Mod %s".formatted(status.getLifetimeMod() > 0 ? "+%.2f".formatted(status.getLifetimeMod()) : "%.2f".formatted(status.getLifetimeMod()))));
        }

        if (status.getRadiusMod() != 0) {
            pTooltipComponents.add(Component.literal("Radius Mod %s".formatted(status.getRadiusMod() > 0 ? "+%.2f".formatted(status.getRadiusMod()) : "%.2f".formatted(status.getRadiusMod()))));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

    public SpellEffect getSpellEffect() {
        return spellEffect.get();
    }

    public SpellEffectItem setSpellEffect(Supplier<SpellEffect> spellEffect) {
        this.spellEffect = spellEffect;
        return this;
    }
}
