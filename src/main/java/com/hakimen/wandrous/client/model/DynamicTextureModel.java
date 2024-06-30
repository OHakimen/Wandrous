package com.hakimen.wandrous.client.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hakimen.wandrous.common.item.component.WandDataComponent;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import com.mojang.math.Transformation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.model.SimpleModelState;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@OnlyIn(Dist.CLIENT)
public class DynamicTextureModel {

    private String display = "";
    private ItemStack stack = ItemStack.EMPTY;
    private boolean fromJson;
    private ResourceLocation id;
    private List<ResourceLocation> textures;
    public static ItemModelGenerator ITEM_MODEL_GENERATOR = new ItemModelGenerator();
    private DynamicTextureModel fallback = null;

    private DynamicTextureModel(ResourceLocation id, boolean fromJson, List<ResourceLocation> textures) {
        this.fromJson = fromJson;
        this.id = id;
        this.textures = textures;
    }

    public static DynamicTextureModel fromJsonModel(ResourceLocation simpleId) {
        return new DynamicTextureModel(simpleId, true, new ArrayList<>());
    }

    public static DynamicTextureModel fromTextures(List<ResourceLocation> textureIds, ResourceLocation modelId) {
        return new DynamicTextureModel(modelId, false, textureIds);
    }

    public DynamicTextureModel withFallback(DynamicTextureModel fallback) {
        this.fallback = fallback;
        return this;
    }

    public DynamicTextureModel setDisplay(String display) {
        this.display = display;
        return this;
    }

    public DynamicTextureModel setStack(ItemStack stack) {
        this.stack = stack;
        return this;
    }

    private static Map<WandDataComponent.WandStat, BakedModel> bakedModelsFromTextures = new HashMap<>();

    @Nullable
    public BakedModel getBakedModel(ModelManager modelManager) {
        return getBakedModel(modelManager, 0);
    }

    @Nullable
    private BakedModel getBakedModel(ModelManager modelManager, int nests) {
        WandDataComponent.WandStat actualData = stack.get(DataComponentsRegister.WAND_COMPONENT.get());
        WandDataComponent.WandStat stat = new WandDataComponent.WandStatBuilder(WandDataComponent.DEFAULT_STAT).setWand(actualData.getWand()).setGem(actualData.getGem()).build();
        if (nests > 10) {
            return null;
        }
        if (!resourcesExist()) {
            if (fallback != null) {
                return fallback.getBakedModel(modelManager, nests + 1);
            }
            return null;
        }

        if(bakedModelsFromTextures.containsKey(stat)){
            return bakedModelsFromTextures.get(stat);
        }

        if (fromJson) {
            BakedModel model = modelManager.getModel(ModelResourceLocation.inventory(ResourceLocation.fromNamespaceAndPath(id.getNamespace(), id.getPath())));
            if (model != modelManager.getMissingModel()) {
                return model;
            }
        } else {
            BlockModel jsonModel = makeUnbakedModel();

            ModelBakerImpl impl = new ModelBakerImpl(DynamicTextureModel::spriteLoader);

            BakedModel model = impl.bakeUncached(jsonModel, new SimpleModelState(
                    Transformation.identity()
            ));

            bakedModelsFromTextures.put(stat,model);
            return model;
        }

        return null;
    }

    private BlockModel makeUnbakedModel() {
        JsonObject modelJson = new JsonObject();
        modelJson.addProperty("parent", "builtin/generated");
        JsonObject textureList = new JsonObject();
        int count = 0;
        for (ResourceLocation texture : textures) {
            textureList.addProperty("layer" + count, texture.toString());
            count++;
        }
        modelJson.add("textures", textureList);

        modelJson.addProperty("gui_light", "front");

        JsonElement displayObj;
        if(!display.isEmpty()){
            displayObj = JsonParser.parseString(display);
        }else{
            displayObj = JsonParser.parseString("{\"ground\": {\"rotation\": [ 0, 0, 0 ],\"translation\": [ 0, 2, 0],\"scale\":[ 0.5, 0.5, 0.5 ]},\"head\": {\"rotation\": [ 0, 180, 0 ],\"translation\": [ 0, 13, 7],\"scale\":[ 1, 1, 1]},\"thirdperson_righthand\": {\"rotation\": [ 0, 0, 0 ],\"translation\": [ 0, 3, 1 ],\"scale\": [ 0.55, 0.55, 0.55 ]},\"firstperson_righthand\": {\"rotation\": [ 0, -90, 25 ],\"translation\": [ 1.13, 3.2, 1.13],\"scale\": [ 0.68, 0.68, 0.68 ]},\"fixed\": {\"rotation\": [ 0, 180, 0 ],\"scale\": [ 1, 1, 1 ]}}");
        }
        modelJson.add("display", displayObj);
        BlockModel model = BlockModel.fromString(modelJson.toString());

        model.resolveParents((id) -> {
            return ModelBakery.GENERATION_MARKER;
        });

        model = ITEM_MODEL_GENERATOR.generateBlockModel(DynamicTextureModel::spriteLoader, model);

        return model;
    }

    public boolean resourcesExist() {
        if (fromJson) {
            return hasModel(id);
        } else {
            for (ResourceLocation textureId : textures) {
                if (!hasTexture(textureId)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static TextureAtlasSprite spriteLoader(Material spriteId) {
        TextureAtlasSprite sprite = spriteId.sprite();
        return sprite;
    }

    public static boolean hasTexture(ResourceLocation textureId) {
        ResourceLocation resourceId = ResourceLocation.fromNamespaceAndPath(textureId.getNamespace(), "textures/" + textureId.getPath() + ".png");
        Optional<Resource> maybeTexture = Minecraft.getInstance().getResourceManager().getResource(resourceId);
        return maybeTexture.isPresent();
    }

    public static boolean hasModel(ResourceLocation modelId) {
        ResourceLocation resourceId = ResourceLocation.fromNamespaceAndPath(modelId.getNamespace(), "models/" + modelId.getPath() + ".json");
        Optional<Resource> maybeModel = Minecraft.getInstance().getResourceManager().getResource(resourceId);
        return maybeModel.isPresent();
    }

    public static void clearCache() {
        bakedModelsFromTextures.clear();
    }


    public static CompletableFuture<Void> reload(PreparableReloadListener.PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller profilerFiller, ProfilerFiller profilerFiller1, Executor executor, Executor executor1) {
        clearCache();
        return preparationBarrier.wait(null);
    }

}