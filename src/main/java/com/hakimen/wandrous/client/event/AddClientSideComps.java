package com.hakimen.wandrous.client.event;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.tooltip.GlyphTooltipRenderer;
import com.hakimen.wandrous.client.tooltip.SpellStatsTooltipRenderer;
import com.hakimen.wandrous.client.tooltip.SpellTooltipRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;

@EventBusSubscriber(modid = Wandrous.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class AddClientSideComps {

    @SubscribeEvent
    public static void addTooltipTypes(RegisterClientTooltipComponentFactoriesEvent e) {
        e.register(SpellTooltipRenderer.SpellTooltipComponent.class, SpellTooltipRenderer::new);
        e.register(GlyphTooltipRenderer.GlyphTooltipComponent.class, GlyphTooltipRenderer::new);
        e.register(SpellStatsTooltipRenderer.SpellStatsComponent.class, SpellStatsTooltipRenderer::new);
    }

}

