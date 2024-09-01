package com.hakimen.wandrous.client.event;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.item.SpellEffectItem;
import com.hakimen.wandrous.common.item.component.WandDataComponent;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import com.hakimen.wandrous.common.registers.ItemRegister;
import com.hakimen.wandrous.common.utils.ChargesUtils;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD, modid = Wandrous.MODID)
public class AddItemDecorator {

    @SubscribeEvent
    public static void renderDecoration(RegisterItemDecorationsEvent event) {
        ItemRegister.ITEMS.getRegistry().get().stream().filter(item -> item instanceof SpellEffectItem sei && ChargesUtils.hasCharge(sei.getDefaultInstance())).toList().forEach(toRender ->
                event.register(toRender, (guiGraphics, font, stack, xOffset, yOffset) -> {
                    guiGraphics.pose().pushPose();
                    guiGraphics.pose().translate(0, 0, 200);
                    guiGraphics.pose().scale(0.5f, 0.5f, 0.5f);
                    guiGraphics.drawString(font,
                            "" + ChargesUtils.currentCharges(stack), xOffset * 2 + 30 - font.width("" + ChargesUtils.currentCharges(stack)), yOffset * 2 + 22, ChargesUtils.currentCharges(stack) == 0 ? 0xff5555 : 0xffffff);
                    guiGraphics.pose().popPose();
                    return true;
                })
        );

        event.register(ItemRegister.WAND.get(), (guiGraphics, font, stack, xOffset, yOffset) -> {
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(0, 0, 200);

            if(stack.get(DataComponentsRegister.WAND_COMPONENT.get()).getWandCooldown() > 0){
                WandDataComponent.WandStat stat = stack.get(DataComponentsRegister.WAND_COMPONENT.get());
                guiGraphics.fill(RenderType.guiOverlay(), xOffset, (int) (yOffset + 16 - ((float)stat.getWandCooldown() / stat.getMaxCooldown() * 16)), xOffset + 16, yOffset + 16, stat.isFromCastDelay() ? 0x64ffffff : 0x64ffc7c7);
            }

            guiGraphics.pose().popPose();
            return false;
        });
    }
}
