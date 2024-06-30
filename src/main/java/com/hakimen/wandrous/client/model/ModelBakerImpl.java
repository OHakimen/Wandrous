package com.hakimen.wandrous.client.model;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class ModelBakerImpl implements ModelBaker {
    private final Function<Material, TextureAtlasSprite> modelTextureGetter;

    ModelBakerImpl(Function<Material, TextureAtlasSprite>  getter) {
        this.modelTextureGetter = getter;
    }

    @Override
    public UnbakedModel getModel(ResourceLocation pLocation) {
        return null;
    }

    @Override
    public Function<Material, TextureAtlasSprite> getModelTextureGetter() {
        return this.modelTextureGetter;
    }

    @Override
    public BakedModel bake(ResourceLocation pLocation, ModelState pTransform) {
        return bake(pLocation, pTransform, this.modelTextureGetter);
    }

    @Override
    public @org.jetbrains.annotations.Nullable UnbakedModel getTopLevelModel(ModelResourceLocation location) {
        return getModel(location.id());
    }

    @Override
    public BakedModel bake(ResourceLocation pLocation, ModelState pTransform, Function<Material, TextureAtlasSprite> sprites) {
        UnbakedModel unbakedmodel = this.getModel(pLocation);
        BakedModel bakedmodel1 = this.bakeUncached(unbakedmodel, pTransform, sprites);
        return bakedmodel1;
    }

    public BakedModel bakeUncached(UnbakedModel p_352386_, ModelState p_352194_) {
        return bakeUncached(p_352386_, p_352194_, this.modelTextureGetter);
    }

    @Nullable
    public  BakedModel bakeUncached(UnbakedModel p_352386_, ModelState p_352194_, Function<Material, TextureAtlasSprite> sprites) {
        if (p_352386_ instanceof BlockModel blockmodel && blockmodel.getRootModel() == ModelBakery.GENERATION_MARKER) {
            return new ItemModelGenerator()
                    .generateBlockModel(sprites, blockmodel)
                    .bake(this, blockmodel, sprites, p_352194_, false);
        }

        return p_352386_.bake(this, sprites, p_352194_);
    }
}