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

        crystal("small_tealestite_bud", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID,"block/small_tealestite_bud"));
        crystal("medium_tealestite_bud", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID,"block/medium_tealestite_bud"));
        crystal("big_tealestite_bud", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID,"block/big_tealestite_bud"));
        crystal("tealestite_cluster", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID,"block/tealestite_cluster"));


        withExistingParent("glyph_projector", "wandrous:block/cube_bottom_top_no_cull")
                .texture("top", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/projectors/projector_top"))
                .texture("side", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/projectors/projector_side"))
                .texture("bottom", ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/projectors/projector_bottom"))
                .renderType("translucent");

        orientableVertical("arcane_dispenser_vertical",
                ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/arcane_dispenser/arcane_dispenser_top"),
                ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/arcane_dispenser/arcane_dispenser_vertical_facing"));

        orientable("arcane_dispenser",
                ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/arcane_dispenser/arcane_dispenser_side"),
                ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/arcane_dispenser/arcane_dispenser_front"),
                ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "block/arcane_dispenser/arcane_dispenser_top")
        );
    }

    private void crystal(String name, ResourceLocation resourceLocation){
        withExistingParent(name, "minecraft:block/cross")
                .texture("cross", resourceLocation)
                .renderType("cutout");
    }
}
