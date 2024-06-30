package com.hakimen.wandrous.common.datagen;

import com.hakimen.wandrous.Wandrous;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockModelProvider extends net.neoforged.neoforge.client.model.generators.BlockModelProvider {
    public BlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Wandrous.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        cubeAll("chiseled_chert_bricks_0", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID,"block/chiseled_chert_bricks_0"));
        cubeAll("chiseled_chert_bricks_1", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID,"block/chiseled_chert_bricks_1"));
        cubeAll("chiseled_chert_bricks_2", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID,"block/chiseled_chert_bricks_2"));
        cubeAll("chiseled_chert_bricks_3", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID,"block/chiseled_chert_bricks_3"));
        
    }
}
