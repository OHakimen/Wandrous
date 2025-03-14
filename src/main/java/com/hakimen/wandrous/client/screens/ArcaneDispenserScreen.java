package com.hakimen.wandrous.client.screens;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.menus.ArcaneDispenserMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ArcaneDispenserScreen extends AbstractContainerScreen<ArcaneDispenserMenu> {

    private final ResourceLocation SPELL_SLOT = ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "textures/gui/spell_slot.png");
    private final ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "textures/gui/arcane_dispenser_gui.png");

    public ArcaneDispenserScreen(ArcaneDispenserMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle.copy().withColor(0xffffff));
        titleLabelY = 0;
        titleLabelX = 2;

        this.imageHeight = 120 + 6 * 18;
        this.inventoryLabelY = this.imageHeight - 91;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBlurredBackground(partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(BACKGROUND, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }
}
