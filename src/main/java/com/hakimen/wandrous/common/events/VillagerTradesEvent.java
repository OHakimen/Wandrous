package com.hakimen.wandrous.common.events;

import com.hakimen.wandrous.Wandrous;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@EventBusSubscriber(bus= EventBusSubscriber.Bus.GAME, modid = Wandrous.MODID)
public class VillagerTradesEvent {

    @SubscribeEvent
    public static void onVillagerTradesEvent(net.neoforged.neoforge.event.village.VillagerTradesEvent event) {
        if(event.getType().equals(VillagerProfession.CARTOGRAPHER)){
            event.getTrades().put(3, List.of(
               new TreasureMapForEmeralds(
                       20,
                       TagKey.create(Registries.STRUCTURE,
                       ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "dungeon/dungeon")),
                       "wandrous.filled_map.dungeon",
                               MapDecorationTypes.TARGET_X, 12, 5
               )
            ));
        }
    }

    static class TreasureMapForEmeralds implements VillagerTrades.ItemListing {
        private final int emeraldCost;
        private final TagKey<Structure> destination;
        private final String displayName;
        private final Holder<MapDecorationType> destinationType;
        private final int maxUses;
        private final int villagerXp;

        public TreasureMapForEmeralds(
                int pEmeraldCost, TagKey<Structure> pDestination, String pDisplayName, Holder<MapDecorationType> pDestinationType, int pMaxUses, int pVillagerXp
        ) {
            this.emeraldCost = pEmeraldCost;
            this.destination = pDestination;
            this.displayName = pDisplayName;
            this.destinationType = pDestinationType;
            this.maxUses = pMaxUses;
            this.villagerXp = pVillagerXp;
        }

        @Nullable
        @Override
        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            if (!(pTrader.level() instanceof ServerLevel)) {
                return null;
            } else {
                ServerLevel serverlevel = (ServerLevel)pTrader.level();
                BlockPos blockpos = serverlevel.findNearestMapStructure(this.destination, pTrader.blockPosition(), 100, true);
                if (blockpos != null) {
                    ItemStack itemstack = MapItem.create(serverlevel, blockpos.getX(), blockpos.getZ(), (byte)2, true, true);
                    MapItem.renderBiomePreviewMap(serverlevel, itemstack);
                    MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
                    itemstack.set(DataComponents.ITEM_NAME, Component.translatable(this.displayName));
                    itemstack.set(DataComponents.LORE, new ItemLore(List.of(
                            Component.translatable("wandrous.map.dungeon.lore")
                    )));
                    return new MerchantOffer(
                            new ItemCost(Items.EMERALD, this.emeraldCost), Optional.of(new ItemCost(Items.COMPASS)), itemstack, this.maxUses, this.villagerXp, 0.2F
                    );
                } else {
                    return null;
                }
            }
        }
    }
}
