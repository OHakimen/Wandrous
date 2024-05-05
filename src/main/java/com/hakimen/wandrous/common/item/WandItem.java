package com.hakimen.wandrous.common.item;

import com.hakimen.wandrous.client.tooltip.SpellTooltipRenderer;
import com.hakimen.wandrous.common.registers.ContainerRegister;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.utils.CastingUtils;
import com.hakimen.wandrous.common.utils.WandUtils;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;
import org.joml.Math;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class WandItem extends Item {

    public static final String CAPACITY = "Capacity";
    public static final String MAX_MANA = "MaxMana";
    public static final String MANA = "Mana";
    public static final String CAST_DELAY = "CastDelay";
    public static final String RECHARGE_SPEED = "RechargeSpeed";
    public static final String MANA_CHARGE_SPEED = "ManaChargeSpeed";
    public static final String CASTABLE_SIZE = "CastableSize";
    public static final String CURRENT_IDX = "CurrentIdx";
    public static final String TIER = "Tier";

    public WandItem() {
        super(new Properties().stacksTo(1));
    }


    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Nullable
            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                return entityLiving instanceof Player player && player.isUsingItem() ? HumanoidModel.ArmPose.CROSSBOW_HOLD : HumanoidModel.ArmPose.ITEM;
            }
        });
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return pStack.getOrCreateTag().getInt(MANA) != pStack.getOrCreateTag().getInt(MAX_MANA);
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        return Math.round((float) pStack.getOrCreateTag().getInt(MANA) * 13.0F / (float) pStack.getOrCreateTag().getInt(MAX_MANA));
    }


    @Override
    public int getBarColor(ItemStack pStack) {
        return 0x90d8fc;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {



        if (!pStack.getOrCreateTag().isEmpty()) {
            pTooltipComponents.add(Component.literal("Cast Delay %.2fs".formatted(pStack.getOrCreateTag().getFloat(CAST_DELAY))));
            pTooltipComponents.add(Component.literal("Recharge Speed %.2fs".formatted(pStack.getOrCreateTag().getFloat(RECHARGE_SPEED))));
            pTooltipComponents.add(Component.literal("Mana Max %s".formatted(pStack.getOrCreateTag().getInt(MAX_MANA))));
            pTooltipComponents.add(Component.literal("Mana %s".formatted(pStack.getOrCreateTag().getInt(MANA))));
            pTooltipComponents.add(Component.literal("Mana Charge Speed %s".formatted(pStack.getOrCreateTag().getInt(MANA_CHARGE_SPEED))));
            pTooltipComponents.add(Component.literal(" "));
            pTooltipComponents.add(Component.literal("Capacity %s".formatted(pStack.getOrCreateTag().getInt(CAPACITY))));
            pTooltipComponents.add(Component.literal("WAND_SPELLS_MARKER"));
        } else {
            pTooltipComponents.add(Component.literal("Put in inventory to initialize"));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
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
        if (pLivingEntity instanceof Player pPlayer) {
            if (wand.getItem() instanceof WandItem) {
                if (!pPlayer.hasContainerOpen() && !pPlayer.getCooldowns().isOnCooldown(wand.getItem())) {
                    CastingUtils castingUtils = new CastingUtils();

                    Optional<IItemHandler> handler = Optional.ofNullable(wand.getCapability(Capabilities.ItemHandler.ITEM));
                    List<SpellEffect> effect = new ArrayList<>();

                    handler.ifPresent(iItemHandler -> {
                        for (int i = 0; i < iItemHandler.getSlots(); i++) {
                            ItemStack stack = iItemHandler.getStackInSlot(i);
                            if (stack.getItem() instanceof SpellEffectItem spellEffectItem) {
                                effect.add(spellEffectItem.getSpellEffect());
                            }
                        }
                    });

                    if (effect.size() != wand.getOrCreateTag().getInt(CASTABLE_SIZE)) {
                        wand.getOrCreateTag().putInt(CURRENT_IDX, 0);
                        wand.getOrCreateTag().putInt(CASTABLE_SIZE, effect.size());
                    }

                    int getOldIdx = wand.getOrCreateTag().getInt(CURRENT_IDX);

                    Node<SpellEffect> cast = castingUtils.makeCastingTree(effect.subList(getOldIdx, effect.size()));

                    float rechargeSpeed = wand.getOrCreateTag().getFloat(RECHARGE_SPEED);
                    float castDelay = wand.getOrCreateTag().getFloat(CAST_DELAY);

                    if (!effect.isEmpty() && cast.getData() != null) {

                        int current = (getOldIdx + castingUtils.idx) % effect.size();
                        wand.getOrCreateTag().putInt(CURRENT_IDX, current);

                        int cost = CastingUtils.calculateManaCost(0, cast);
                        int currentMana = wand.getOrCreateTag().getInt(MANA);

                        if (cost <= currentMana) {
                            CastingUtils.castSpells(pPlayer, wand, pLevel, pPlayer.getEyePosition(), cast);
                            wand.getOrCreateTag().putInt(MANA, currentMana - Math.max(cost, 0));

                            pPlayer.getCooldowns().addCooldown(wand.getItem(), (int) (current == 0 ? rechargeSpeed : castDelay) * 20);
                        } else {
                            pPlayer.displayClientMessage(Component.literal("Not enough mana"), true);
                        }
                    } else {
                        pPlayer.displayClientMessage(Component.literal("No spells to cast"), true);
                    }
                }
            }
        }
        super.onUseTick(pLevel, pLivingEntity, wand, pRemainingUseDuration);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pStack.getOrCreateTag().isEmpty()) {
            WandUtils.makeWand(pStack);
        }

        Optional<ItemStackHandler> handler = Optional.ofNullable((ItemStackHandler) pStack.getCapability(Capabilities.ItemHandler.ITEM));

        handler.ifPresent(itemStackHandler -> {
            pStack.getOrCreateTag().put("Inventory", itemStackHandler.serializeNBT());
        });

        int mana = pStack.getOrCreateTag().getInt(MANA);
        int maxMana = pStack.getOrCreateTag().getInt(MAX_MANA);

        if (mana <= maxMana) {
            pStack.getOrCreateTag().putInt(MANA, (int) Math.clamp(0, maxMana, (mana + pStack.getOrCreateTag().getInt(MANA_CHARGE_SPEED) / 20f)));
        }

    }

    @Override
    public Component getName(ItemStack pStack) {
        return pStack.getOrCreateTag().isEmpty() ? super.getName(pStack) : Component.literal(pStack.getOrCreateTag().getString("name"));
    }
}
