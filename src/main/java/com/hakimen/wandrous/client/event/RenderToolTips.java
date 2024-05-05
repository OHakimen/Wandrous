package com.hakimen.wandrous.client.event;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.tooltip.SpellTooltipRenderer;
import com.hakimen.wandrous.common.item.SpellEffectItem;
import com.hakimen.wandrous.common.item.WandItem;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.mojang.datafixers.util.Either;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.contents.PlainTextContents;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = Wandrous.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RenderToolTips {
    @SubscribeEvent
    public static void renderTooltips(RenderTooltipEvent.GatherComponents e) {

        ItemStack stack = e.getItemStack();

        if(stack.getItem() instanceof WandItem) {
            int wandCapacity = stack.getOrCreateTag().getInt(WandItem.CAPACITY);
            if (wandCapacity == 0) return;

            Optional<IItemHandler> handler = Optional.ofNullable(stack.getCapability(Capabilities.ItemHandler.ITEM));

            List<ItemStack> spells = new ArrayList<>();

            handler.ifPresent(iItemHandler -> {
                for (int i = 0; i < iItemHandler.getSlots(); i++) {
                    if(!iItemHandler.getStackInSlot(i).isEmpty()) {
                        spells.add(iItemHandler.getStackInSlot(i));
                    }
                }
            });
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
            if (rmvIdx == -1) return;
            if(spells.isEmpty()) return;
            e.getTooltipElements().add(rmvIdx, Either.right(new SpellTooltipRenderer.SpellTooltipComponent(spells.stream().limit(16).toList(), spells.size() > 16)));
        }
    }
}
