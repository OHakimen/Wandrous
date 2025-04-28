package com.hakimen.wandrous.common.item;

import com.hakimen.wandrous.common.data.Scroll;
import com.hakimen.wandrous.common.data.ScrollDataListener;
import com.hakimen.wandrous.common.item.component.ScrollDataComponent;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import com.hakimen.wandrous.common.registers.EffectRegister;
import com.hakimen.wandrous.common.registers.ItemRegister;
import com.hakimen.wandrous.common.api.SpellStack;
import com.hakimen.wandrous.common.utils.CastingUtils;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScrollItem extends Item {
    public ScrollItem() {
        super(new Properties().stacksTo(1)
                .component(DataComponentsRegister.SCROLL_COMPONENT.get(), ScrollDataComponent.DEFAULT)
                .durability(4));
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(pStack.get(DataComponentsRegister.SCROLL_COMPONENT.get()).equals(ScrollDataComponent.DEFAULT)){
            makeScroll(pStack);
        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pPlayer.getCooldowns().isOnCooldown(this) && !pLevel.isClientSide){
            if(pPlayer.hasEffect(EffectRegister.SILENCE)) {
                pPlayer.displayClientMessage(Component.translatable("item.wandrous.wand.silenced"), true);
                return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
            }
            ItemStack stack = pPlayer.getItemInHand(pUsedHand);

            ScrollDataComponent.ScrollData data = stack.get(DataComponentsRegister.SCROLL_COMPONENT.get());

            List<SpellStack> effects = new ArrayList<>();

            for (String spell : data.getSpells()) {
                Item effect = ItemRegister.ITEMS.getRegistry().get().get(ResourceLocation.parse(spell));
                if (effect != null && effect instanceof SpellEffectItem sei) {

                    effects.add(SpellStack.fromItemStack(sei.getDefaultInstance()));
                }
            }

            CastingUtils castingUtils = new CastingUtils();
            Node<SpellStack> castingTree = castingUtils.makeCastingTree(effects,effects);

            if(castingTree.getData() != null){
                CastingUtils.castSpells(pPlayer, stack, pLevel, pPlayer.getEyePosition(), castingTree);
                pPlayer.getCooldowns().addCooldown(this, 80);
                stack.hurtAndBreak(1, pPlayer, EquipmentSlot.MAINHAND);
                return InteractionResultHolder.success(stack);
            }
        }
        return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {

        if(pStack.getDamageValue() != 0){
            pTooltipComponents.add(Component.literal("WAND_SPELLS_MARKER"));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

    @Override
    public Component getName(ItemStack pStack) {
        if(pStack.get(DataComponentsRegister.SCROLL_COMPONENT.get()).equals(ScrollDataComponent.DEFAULT)){
            return super.getName(pStack);
        }

        Component named = Component.translatable(pStack.get(DataComponentsRegister.SCROLL_COMPONENT.get()).getName());
        if(pStack.getDamageValue() == 0){
            named = Component.translatable(pStack.get(DataComponentsRegister.SCROLL_COMPONENT.get()).getName()).withStyle(ChatFormatting.OBFUSCATED);
        }
        return named;
    }

    public static void makeScroll(ItemStack stack){

        Random r = new Random();
        Scroll scroll = ScrollDataListener.getAllScrolls().get(r.nextInt(0, ScrollDataListener.getAllScrolls().size()));

        makeScroll(stack, scroll);
    }

    public static void makeScroll(ItemStack stack, Scroll scroll){
        stack.update(DataComponentsRegister.SCROLL_COMPONENT.get(), stack.get(DataComponentsRegister.SCROLL_COMPONENT.get()), scrollData -> {
            return new ScrollDataComponent.ScrollDataBuilder(scrollData)
                    .setName(scroll.getName())
                    .setSpells(scroll.getSpells())
                    .build();
        });


        stack.set(DataComponents.LORE, new ItemLore(scroll.getLore().stream().map(s -> (Component)Component.literal(s)).toList()));
    }
}
