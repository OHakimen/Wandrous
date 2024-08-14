package com.hakimen.wandrous.common.recipe;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.custom.register.WandrousRegistries;
import com.hakimen.wandrous.common.data.Glyph;
import com.hakimen.wandrous.common.item.component.InscribedLensDataComponent;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ArcaneInscribingRecipe implements Recipe<ArcaneInscriberRecipeInput> {

    final ResourceLocation id;
    final NonNullList<ResourceLocation> requiredGlyphs;
    final Ingredient onSlate;
    final ItemStack output;
    final int tier;
    final int ticksNeeded;

    public ArcaneInscribingRecipe(ResourceLocation id, NonNullList<ResourceLocation> requiredGlyphs, Ingredient onSlate, ItemStack output, int tier, int ticksNeeded) {
        this.id = id;
        this.requiredGlyphs = requiredGlyphs;
        this.onSlate = onSlate;
        this.output = output;
        this.tier = tier;
        this.ticksNeeded = ticksNeeded;
    }

    public NonNullList<ResourceLocation> getRequiredGlyphs() {
        return requiredGlyphs;
    }

    public Ingredient getOnSlate() {
        return onSlate;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getTier() {
        return tier;
    }

    public int getTicksNeeded() {
        return ticksNeeded;
    }
    @Override
    public boolean matches(ArcaneInscriberRecipeInput input, Level level) {


        if (input.tier < tier) return false;

        if (!onSlate.test(input.getOnSlate())) return false;


        Map<ResourceLocation, Integer> recipeGlyphs = new HashMap<>();
        for (ResourceLocation requiredGlyph : requiredGlyphs) {
            Optional<Glyph> maybeGlyph = WandrousRegistries.GLYPH_REGISTER.getOptional(requiredGlyph);
            if (maybeGlyph.isEmpty()) {
                return false;
            }
            if(recipeGlyphs.containsKey(requiredGlyph)) {
                recipeGlyphs.put(requiredGlyph, recipeGlyphs.get(requiredGlyph) + 1);
            }else{
                recipeGlyphs.put(requiredGlyph, 1);
            }

        }

        for (ItemStack item : input.getContainer().getItems()) {
            if(item.has(DataComponentsRegister.GLYPH_COMPONENT.get())){
                InscribedLensDataComponent.GlyphData glyphData = item.get(DataComponentsRegister.GLYPH_COMPONENT.get());
                if (recipeGlyphs.containsKey(glyphData.getId())) {
                    if(recipeGlyphs.get(glyphData.getId()) > 0) {
                        recipeGlyphs.put(glyphData.getId(), recipeGlyphs.get(glyphData.getId()) - 1);
                        if(recipeGlyphs.get(glyphData.getId()) == 0) {
                            recipeGlyphs.remove(glyphData.getId());
                        }
                    }
                }else{
                    return false;
                }
            }
        }
        return recipeGlyphs.isEmpty();
    }

    @Override
    public ItemStack assemble(ArcaneInscriberRecipeInput input, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<ArcaneInscribingRecipe> {
        private Type() {
        }
        public static final String ID = "arcane_inscribing";
        public static final Type INSTANCE = new Type();

    }

    public static class Serializer implements RecipeSerializer<ArcaneInscribingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "arcane_inscribing");

        @Override
        public MapCodec<ArcaneInscribingRecipe> codec() {
            return RecordCodecBuilder.mapCodec(arcaneInscribingRecipeInstance ->
                    arcaneInscribingRecipeInstance.group(
                            ResourceLocation.CODEC.fieldOf("type").forGetter(recipe -> ID),
                            NonNullList.codecOf(ResourceLocation.CODEC).fieldOf("glyphs")
                                    .validate(resourceLocations -> resourceLocations.size() >= 16 ?
                                                    DataResult.error(() -> "Invalid amount of glyphs, expected to be in range 1..16, got" + resourceLocations.size()) :
                                                    DataResult.success(resourceLocations))
                                            .forGetter(ArcaneInscribingRecipe::getRequiredGlyphs),
                            Ingredient.CODEC.fieldOf("on_slate").forGetter(ArcaneInscribingRecipe::getOnSlate),
                            ItemStack.SINGLE_ITEM_CODEC.fieldOf("output").forGetter(ArcaneInscribingRecipe::getOutput),
                            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("tier").validate(integer -> integer <= 0 || integer > 4 ?
                                    DataResult.error(() -> "Tier value invalid, expected to be in range 1..4, got "+ integer) :
                                    DataResult.success(integer)).forGetter(ArcaneInscribingRecipe::getTier),
                            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("ticks").forGetter(ArcaneInscribingRecipe::getTicksNeeded)
                    ).apply(arcaneInscribingRecipeInstance, ArcaneInscribingRecipe::new)
            );
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ArcaneInscribingRecipe> streamCodec() {
            return new StreamCodec<>() {
                @Override
                public ArcaneInscribingRecipe decode(RegistryFriendlyByteBuf pBuffer) {
                    ResourceLocation id = pBuffer.readResourceLocation();
                    NonNullList<ResourceLocation> requiredGlyphs = NonNullList.create();
                    pBuffer.readCollection(value -> requiredGlyphs, ResourceLocation.STREAM_CODEC);
                    int ticks = pBuffer.readInt();
                    int tier = pBuffer.readInt();
                    Ingredient onSlate = Ingredient.CONTENTS_STREAM_CODEC.decode(pBuffer);
                    ItemStack output = ItemStack.STREAM_CODEC.decode(pBuffer);
                    return new ArcaneInscribingRecipe(id, requiredGlyphs, onSlate, output, tier, ticks);
                }

                @Override
                public void encode(RegistryFriendlyByteBuf pBuffer, ArcaneInscribingRecipe pValue) {
                    pBuffer.writeResourceLocation(pValue.id);
                    pBuffer.writeCollection(pValue.getRequiredGlyphs(), ResourceLocation.STREAM_CODEC);
                    pBuffer.writeInt(pValue.getTicksNeeded());
                    pBuffer.writeInt(pValue.getTier());
                    Ingredient.CONTENTS_STREAM_CODEC.encode(pBuffer, pValue.getOnSlate());
                    ItemStack.STREAM_CODEC.encode(pBuffer, pValue.getOutput());
                }
            };
        }
    }

}
