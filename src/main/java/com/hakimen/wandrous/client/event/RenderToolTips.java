package com.hakimen.wandrous.client.event;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.tooltip.SpellTooltipRenderer;
import com.hakimen.wandrous.common.item.ScrollItem;
import com.hakimen.wandrous.common.item.SpellEffectItem;
import com.hakimen.wandrous.common.item.WandItem;
import com.hakimen.wandrous.common.item.component.ScrollDataComponent;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import com.hakimen.wandrous.common.registers.ItemRegister;
import com.mojang.datafixers.util.Either;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.contents.PlainTextContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = Wandrous.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
public class RenderToolTips {
    @SubscribeEvent
    public static void renderTooltips(RenderTooltipEvent.GatherComponents e) {

        ItemStack stack = e.getItemStack();

        if (stack.getItem() instanceof WandItem) {
            renderForWand(e, stack);
        } else if (stack.getItem() instanceof ScrollItem) {
            renderForScroll(e, stack);
        }
    }
    private static void renderForWand(RenderTooltipEvent.GatherComponents e, ItemStack stack) {
        int wandCapacity = stack.get(DataComponentsRegister.WAND_COMPONENT.get()).getCapacity();
        if (wandCapacity == 0) return;

        List<ItemStack> spells = new ArrayList<>();

        List<String> keys = stack.get(DataComponentsRegister.WAND_COMPONENT.get()).getInventory().getList("Items", Tag.TAG_COMPOUND).stream().map(tag -> ((CompoundTag) tag).getString("id")).toList();
        for (String spell : keys) {
            Item effect = ItemRegister.ITEMS.getRegistry().get().get(ResourceLocation.parse(spell));
            if (effect != null && effect instanceof SpellEffectItem sei) {
                spells.add(sei.getDefaultInstance());
            }
        }
        render(e, spells);
    }

    private static void renderForScroll(RenderTooltipEvent.GatherComponents e, ItemStack stack) {

        List<ItemStack> spells = new ArrayList<>();

        ScrollDataComponent.ScrollData data = stack.get(DataComponentsRegister.SCROLL_COMPONENT.get());

        for (String spell : data.getSpells()) {
            Item effect = ItemRegister.ITEMS.getRegistry().get().get(ResourceLocation.parse(spell));
            if (effect != null && effect instanceof SpellEffectItem sei) {
                spells.add(sei.getDefaultInstance());
            }
        }

        render(e, spells);
    }

    private static boolean render(RenderTooltipEvent.GatherComponents e, List<ItemStack> spells) {
        List<Either<FormattedText, TooltipComponent>> list = e.getTooltipElements();
        int rmvIdx = -1;
        for (int i = 0; i < list.size(); i++) {
            Optional<FormattedText> o = list.get(i).left();
            if (o.isPresent() && o.get() instanceof Component comp && comp.getContents() instanceof PlainTextContents.LiteralContents tc) {
                if ("WAND_SPELLS_MARKER".equals(tc.text())) {
                    rmvIdx = i;
                    list.remove(i);
                    break;
                }
            }
        }
        if (rmvIdx == -1) return true;
        if (spells.isEmpty()) return true;
        e.getTooltipElements().add(rmvIdx, Either.right(new SpellTooltipRenderer.SpellTooltipComponent(spells.stream().limit(16).toList(), spells.size() > 16)));
        return false;
    }
}
