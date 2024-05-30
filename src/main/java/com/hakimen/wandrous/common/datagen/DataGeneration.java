package com.hakimen.wandrous.common.datagen;

import com.hakimen.wandrous.Wandrous;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Wandrous.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneration {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(true, (DataProvider.Factory<BlockTagProvider>) (packOutput) -> new BlockTagProvider(packOutput, event.getLookupProvider(), existingFileHelper));
        gen.addProvider(true, (DataProvider.Factory<net.neoforged.neoforge.client.model.generators.BlockModelProvider>) (packOutput) -> new BlockModelProvider(packOutput, existingFileHelper));
        gen.addProvider(true, (DataProvider.Factory<net.neoforged.neoforge.client.model.generators.BlockStateProvider>) (packOutput) -> new BlockStateProvider(packOutput, existingFileHelper));
        gen.addProvider(true, (DataProvider.Factory<ItemModelProvider>) (packOutput) -> new ItemModelProvider(packOutput, existingFileHelper));

        gen.addProvider(true, (DataProvider.Factory<LootTableProvider>) LootProvider::create);

        gen.addProvider(true, (DataProvider.Factory<LangProvider>) LangProvider::new);
    }
}
