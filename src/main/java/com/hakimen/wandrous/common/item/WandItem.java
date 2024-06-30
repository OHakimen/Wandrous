package com.hakimen.wandrous.common.item;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.model.DynamicTextureModel;
import com.hakimen.wandrous.common.item.component.WandDataComponent;
import com.hakimen.wandrous.common.registers.ContainerRegister;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.utils.CastingUtils;
import com.hakimen.wandrous.common.utils.WandUtils;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.client.model.HumanoidModel;
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
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;
import org.joml.Math;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static com.hakimen.wandrous.common.item.component.WandDataComponent.DEFAULT_STAT;

public class WandItem extends Item implements DynamicModelled {


    public WandItem() {
        super(new Properties().stacksTo(1)
                .component(DataComponentsRegister.WAND_COMPONENT.get(), DEFAULT_STAT));
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
            pTooltipComponents.add(Component.literal("Cast Delay %.2fs".formatted(stat.getCastDelay())));
            pTooltipComponents.add(Component.literal("Recharge Speed %.2fs".formatted(stat.getRechargeSpeed())));
            pTooltipComponents.add(Component.literal("Mana Max %s".formatted(stat.getMaxMana())));
            pTooltipComponents.add(Component.literal("Mana %s".formatted(stat.getMana())));
            pTooltipComponents.add(Component.literal("Mana Charge Speed %s".formatted(stat.getManaChargeSpeed())));
            pTooltipComponents.add(Component.literal(" "));
            pTooltipComponents.add(Component.literal("Capacity %s".formatted(stat.getCapacity())));
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
        if (pLivingEntity instanceof Player pPlayer) {
            if (wand.getItem() instanceof WandItem) {
                if (!pPlayer.hasContainerOpen() && !pPlayer.getCooldowns().isOnCooldown(wand.getItem())) {
                    CastingUtils castingUtils = new CastingUtils();

                    Optional<ItemStackHandler> handler = Optional.ofNullable((ItemStackHandler)wand.getCapability(Capabilities.ItemHandler.ITEM));
                    List<SpellEffect> effect = new ArrayList<>();

                    WandDataComponent.WandStat stat = wand.get(DataComponentsRegister.WAND_COMPONENT.get());
                    handler.ifPresent(iItemHandler -> {
                        iItemHandler.deserializeNBT(pPlayer.level().registryAccess(), stat.getInventory());
                        for (int i = 0; i < iItemHandler.getSlots(); i++) {
                            ItemStack stack = iItemHandler.getStackInSlot(i);
                            if (stack.getItem() instanceof SpellEffectItem spellEffectItem) {
                                effect.add(spellEffectItem.getSpellEffect());
                            }
                        }
                    });

                    WandDataComponent.WandStatBuilder builder = new WandDataComponent.WandStatBuilder(stat);


                    if (effect.size() != stat.getCastableSize()) {
                        builder.setCurrentIdx(0);
                        builder.setCastableSize(effect.size());
                    }

                    int getOldIdx = stat.getCurrentIdx();

                    Node<SpellEffect> cast = castingUtils.makeCastingTree(effect.subList(getOldIdx, effect.size()));

                    float rechargeSpeed = stat.getRechargeSpeed();
                    float castDelay = stat.getCastDelay();

                    if (!effect.isEmpty() && cast.getData() != null) {

                        int current = (getOldIdx + castingUtils.idx) % effect.size();
                        builder.setCurrentIdx(current);

                        int cost = CastingUtils.calculateManaCost(0, cast); //TODO: make a gamerule for making the cast cost nothing in creative

                        int currentMana = stat.getMana();

                        if (cost <= currentMana) {
                            CastingUtils.castSpells(pPlayer, wand, pLevel, pPlayer.getEyePosition(), cast);
                            builder.setMana(currentMana - Math.max(cost, 0));

                            pPlayer.getCooldowns().addCooldown(wand.getItem(), (int) (current == 0 ? rechargeSpeed : castDelay) * 20);
                        } else {
                            pPlayer.displayClientMessage(Component.literal("Not enough mana"), true);
                        }

                        if (!pLevel.isClientSide) {
                            wand.set(DataComponentsRegister.WAND_COMPONENT.get(), builder.build());
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
        if (pStack.get(DataComponentsRegister.WAND_COMPONENT.get()).equals(DEFAULT_STAT)) {
            WandUtils.makeWand(pStack);
        }
        WandDataComponent.WandStat stat = pStack.get(DataComponentsRegister.WAND_COMPONENT.get());
        int mana = stat.getMana();
        int maxMana = stat.getMaxMana();
        int manaRegen = stat.getManaChargeSpeed();

        if (mana <= maxMana) {
            int manaCost = (int) Math.clamp(0, maxMana, (mana +  manaRegen / 20f));
            pStack.update(DataComponentsRegister.WAND_COMPONENT.get(), stat, wandStat -> new WandDataComponent.WandStatBuilder(wandStat).setMana(manaCost).build());
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
                ).setDisplay("{ \"ground\": {\"rotation\": [0, 0, 0],\"translation\": [0, 3, 0],\"scale\":[ 1,1,1 ]}, \"thirdperson_righthand\":{\"rotation\":[0,90,50],\"translation\":[0,10,2.5],\"scale\":[2,2,2]},\"thirdperson_lefthand\":{\"rotation\":[0,-90,-50],\"translation\":[0,10,2.5],\"scale\":[2,2,2]},\"firstperson_righthand\":{\"rotation\":[0,90,35],\"translation\":[0,5,0],\"scale\":[1.25,1.25,1.25]},\"firstperson_lefthand\":{\"rotation\":[0,-90,-35],\"translation\":[0,5,0],\"scale\":[1.25,1.25,1.25]}}")
                .setStack(stack);
        return model;
    }
}
