package com.hakimen.wandrous.client.screens;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.menus.WandTinkerMenu;
import com.hakimen.wandrous.common.item.WandItem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

public class WandTinkerScreen extends AbstractContainerScreen<WandTinkerMenu> {

    private final ResourceLocation SPELL_SLOT = new ResourceLocation(Wandrous.MODID, "textures/gui/spell_slot.png");
    private final ResourceLocation BACKGROUND = new ResourceLocation(Wandrous.MODID, "textures/gui/wand_tinker_gui.png");

    public WandTinkerScreen(WandTinkerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle.copy().withColor(0xffffff));
        titleLabelY = 0;
        titleLabelX = 2;

        this.imageHeight = 120 + 6 * 18;
        this.inventoryLabelY = this.imageHeight - 91;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(BACKGROUND, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
        for (int j = 0; j < getMenu().getWand().getOrCreateTag().getInt(WandItem.CAPACITY); j++) {
            Slot slot = menu.slots.get(j);
            guiGraphics.blit(SPELL_SLOT, relX + slot.x - 1, relY + slot.y - 1, 0, 0, 18, 18,18,18);
        }
    }
}
