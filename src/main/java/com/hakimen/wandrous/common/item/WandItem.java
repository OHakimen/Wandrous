package com.hakimen.wandrous.common.item;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.model.DynamicTextureModel;
import com.hakimen.wandrous.common.item.component.WandDataComponent;
import com.hakimen.wandrous.common.registers.ContainerRegister;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import com.hakimen.wandrous.common.registers.EffectRegister;
import com.hakimen.wandrous.common.registers.GameRuleRegister;
import com.hakimen.wandrous.common.spell.SpellStack;
import com.hakimen.wandrous.common.utils.CastingUtils;
import com.hakimen.wandrous.common.utils.ChargesUtils;
import com.hakimen.wandrous.common.utils.WandUtils;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.joml.Math;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hakimen.wandrous.common.item.component.WandDataComponent.DEFAULT_STAT;

public class WandItem extends Item implements DynamicModelled {

    public WandItem() {
        super(new Properties().stacksTo(1)
                .component(DataComponentsRegister.WAND_COMPONENT.get(), DEFAULT_STAT));
    }


    @Override
    public boolean isBarVisible(ItemStack pStack) {
        WandDataComponent.WandStat stat = pStack.get(DataComponentsRegister.WAND_COMPONENT);

        return stat.getMana() != stat.getMaxMana();
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        WandDataComponent.WandStat stat = pStack.get(DataComponentsRegister.WAND_COMPONENT);

        return Math.round((float) stat.getMana() * 13.0F / (float) stat.getMaxMana());
    }

    @Override
    public Component getName(ItemStack pStack) {
        return pStack.get(DataComponentsRegister.WAND_COMPONENT.get()).equals(DEFAULT_STAT) ? super.getName(pStack) : Component.literal(pStack.get(DataComponentsRegister.WAND_COMPONENT.get()).getName());
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return 0x90d8fc;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {

        WandDataComponent.WandStat stat = pStack.get(DataComponentsRegister.WAND_COMPONENT);

        if (!stat.equals(DEFAULT_STAT)) {
            pTooltipComponents.add(Component.literal("WAND_STATS_MARKER"));
            pTooltipComponents.add(Component.literal("WAND_SPELLS_MARKER"));
        } else {
            pTooltipComponents.add(Component.literal("Put in inventory to initialize"));
        }

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity p_344979_) {
        return 200;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        if (pPlayer.isShiftKeyDown()) {
            MenuProvider containerProvider = new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return getName(pPlayer.getItemInHand(pUsedHand));
                }


                @Override
                public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
                    return ContainerRegister.WAND_TINKER_MENU.get().create(windowId, playerInventory);
                }
            };

            pPlayer.openMenu(containerProvider);
        } else if (!pLevel.isClientSide()) {
            pPlayer.startUsingItem(pUsedHand);
        }

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack wand, int pRemainingUseDuration) {
        if (pLivingEntity instanceof Player pPlayer && !pLevel.isClientSide) {
            if (wand.getItem() instanceof WandItem) {
                if (!pPlayer.hasContainerOpen() && wand.get(DataComponentsRegister.WAND_COMPONENT.get()).getWandCooldown() <= 0) {
                    if (pPlayer.hasEffect(EffectRegister.SILENCE)) {
                        pPlayer.displayClientMessage(Component.translatable("item.wandrous.wand.silenced"), true);
                        return;
                    }
                    CastingUtils castingUtils = new CastingUtils();

                    Optional<ItemStackHandler> handler = Optional.ofNullable((ItemStackHandler) wand.getCapability(Capabilities.ItemHandler.ITEM));
                    List<SpellStack> effect = new ArrayList<>();
                    handler.ifPresent(iItemHandler -> {
                        iItemHandler.deserializeNBT(pPlayer.level().registryAccess(), wand.get(DataComponentsRegister.WAND_COMPONENT.get()).getInventory());
                        for (int i = 0; i < iItemHandler.getSlots(); i++) {
                            ItemStack stack = iItemHandler.getStackInSlot(i);
                            if (stack.getItem() instanceof SpellEffectItem) {
                                effect.add(SpellStack.fromItemStack(stack));
                            }
                        }
                    });


                    if (effect.size() != wand.get(DataComponentsRegister.WAND_COMPONENT.get()).getCastableSize()) {
                        wand.update(DataComponentsRegister.WAND_COMPONENT.get(), DEFAULT_STAT, wandStat -> new WandDataComponent.WandStatBuilder(wandStat).setCurrentIdx(0).setCastableSize(effect.size()).build());
                    }

                    int getOldIdx = wand.get(DataComponentsRegister.WAND_COMPONENT.get()).getCurrentIdx();


                    if (effect.isEmpty()) {
                        pPlayer.displayClientMessage(Component.translatable("item.wandrous.wand.empty_wand"), true);
                        return;
                    }

                    List<SpellStack> stackList = effect.subList(getOldIdx, effect.size());
                    Node<SpellStack> cast = castingUtils.makeCastingTree(stackList, effect);

                    float rechargeSpeed = wand.get(DataComponentsRegister.WAND_COMPONENT.get()).getRechargeSpeed();
                    float castDelay = wand.get(DataComponentsRegister.WAND_COMPONENT.get()).getCastDelay();


                    if (cast.getData() != null && cast.getData().getEffect() != null) {
                        int current = (getOldIdx + castingUtils.idx) % effect.size();
                        wand.update(DataComponentsRegister.WAND_COMPONENT.get(), DEFAULT_STAT, wandStat -> new WandDataComponent.WandStatBuilder(wandStat).setCurrentIdx(current).build());

                        int cost = pPlayer.isCreative() && pLevel.getGameRules().getBoolean(GameRuleRegister.RULE_CREATIVE_CASTING) ? 0 : CastingUtils.calculateManaCost(cast);
                        float delayMod = CastingUtils.calculateCastDelayMod(cast);
                        float rechargeSpeedMod = CastingUtils.calculateRechargeSpeedMod(cast);
                        int currentMana = wand.get(DataComponentsRegister.WAND_COMPONENT.get()).getMana();

                        if (cost <= currentMana) {
                            castingUtils.toConsumeCharges.forEach(ChargesUtils::loseCharge);
                            handler.ifPresent(iItemHandler -> {
                                wand.update(DataComponentsRegister.WAND_COMPONENT.get(), DEFAULT_STAT, wandStat -> new WandDataComponent.WandStatBuilder(wandStat).
                                        setInventory(iItemHandler.serializeNBT(pLevel.registryAccess())).build());
                            });
                            CastingUtils.castSpells(pPlayer, wand, pLevel, pPlayer.getEyePosition(), cast);
                            wand.update(DataComponentsRegister.WAND_COMPONENT.get(), DEFAULT_STAT, wandStat ->
                                    new WandDataComponent.WandStatBuilder(wandStat)
                                            .setMana(currentMana - Math.max(cost, 0))
                                            .setWandCooldown((int) ((current == 0 ? rechargeSpeed + rechargeSpeedMod : castDelay + delayMod) * 20))
                                            .setMaxCooldown((int) ((current == 0 ? rechargeSpeed + rechargeSpeedMod : castDelay + delayMod) * 20))
                                            .setFromCastDelay(current != 0)
                                            .build());
                        } else {
                            pPlayer.displayClientMessage(Component.translatable("item.wandrous.wand.no_mana"), true);
                        }
                    }
                }
            }
        }
        super.onUseTick(pLevel, pLivingEntity, wand, pRemainingUseDuration);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pStack.get(DataComponentsRegister.WAND_COMPONENT.get()).equals(DEFAULT_STAT)) {
            WandUtils.makeWand(pStack);
        }

        if (pStack.get(DataComponentsRegister.WAND_COMPONENT.get()).getWandCooldown() >= 0) {
            pStack.update(DataComponentsRegister.WAND_COMPONENT.get(), DEFAULT_STAT, wandStat -> new WandDataComponent.WandStatBuilder(wandStat)
                    .setWandCooldown(Math.max(0, wandStat.getWandCooldown() - 1)).build());
        }

        WandDataComponent.WandStat stat = pStack.get(DataComponentsRegister.WAND_COMPONENT.get());

        int mana = stat.getMana();
        int maxMana = stat.getMaxMana();
        int manaRegen = stat.getManaChargeSpeed();

        if (mana <= maxMana) {
            int manaCost = (int) Math.clamp(0, maxMana, (mana + manaRegen / 20f));
            pStack.update(DataComponentsRegister.WAND_COMPONENT.get(), DEFAULT_STAT, wandStat -> new WandDataComponent.WandStatBuilder(wandStat).setMana(manaCost).build());
        }
    }


    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        if (stack.get(DataComponentsRegister.WAND_COMPONENT.get()).equals(DEFAULT_STAT)) {
            WandUtils.makeWand(stack);
        }
        return stack;
    }


    public ItemStack getNonInitializedInstance() {
        return new ItemStack(this);
    }

    @Override
    public DynamicTextureModel makeModel(ItemStack stack) {

        WandDataComponent.WandStat stat = stack.get(DataComponentsRegister.WAND_COMPONENT.get());

        int gem = stat.getGem();
        int wand = stat.getWand();

        DynamicTextureModel model = DynamicTextureModel.fromTextures(
                        List.of(
                                ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "item/wand/gems/gem_%s".formatted(gem)),
                                ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "item/wand/wands/wand_%s".formatted(wand))
                        ),
                        ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "wand")
                )
                .setDisplay("{ \"ground\": " +
                            "{\"rotation\": [0, 0, 0]," +
                            "\"translation\": [0, 3, 0]," +
                            "\"scale\":[ 1,1,1 ]}, " +
                            "\"thirdperson_righthand\":{\"rotation\":[0,90,50],\"translation\":[0,10,2.5],\"scale\":[2,2,1]}," +
                            "\"thirdperson_lefthand\":{\"rotation\":[0,-90,-50],\"translation\":[0,10,2.5],\"scale\":[2,2,1]}," +
                            "\"firstperson_righthand\":{\"rotation\":[0,90,35],\"translation\":[0,5,0],\"scale\":[1.25,1.25,0.8]}," +
                            "\"firstperson_lefthand\":{\"rotation\":[0,-90,-35],\"translation\":[0,5,0],\"scale\":[1.25,1.25,0.8]}}")
                .setStack(stack);
        return model;
    }
}
