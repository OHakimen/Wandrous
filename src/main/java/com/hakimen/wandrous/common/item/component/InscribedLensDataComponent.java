package com.hakimen.wandrous.common.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.Objects;

public class InscribedLensDataComponent {
    public static GlyphData DEFAULT = new GlyphData(ResourceLocation.fromNamespaceAndPath("minecraft", "empty"), 0, 0, 0, 0);

    public static class GlyphData {
        private final ResourceLocation textureName;
        private final float[] color = new float[4];

        public GlyphData(ResourceLocation textureName, float r, float g, float b, float a) {
            this.textureName = textureName;
            color[0] = r;
            color[1] = g;
            color[2] = b;
            color[3] = a;
        }

        public ResourceLocation getTextureName() {
            return textureName;
        }

        public float[] getColor() {
            return color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GlyphData glyphData = (GlyphData) o;
            return Objects.equals(textureName, glyphData.textureName) && Objects.deepEquals(color, glyphData.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(textureName, Arrays.hashCode(color));
        }
    }

    public static class GlyphDataBuilder {

        private ResourceLocation textureName;
        private float[] color = new float[4];

        public GlyphDataBuilder() {
        }

        public GlyphDataBuilder(GlyphData glyphData) {
            this.textureName = glyphData.textureName;
            this.color = glyphData.color;
        }


        public ResourceLocation getTextureName() {
            return textureName;
        }

        public GlyphDataBuilder setTextureName(ResourceLocation textureName) {
            this.textureName = textureName;
            return this;
        }

        public float[] getColor() {
            return color;
        }


        public GlyphDataBuilder setColor(float[] color) {
            this.color = color;
            return this;
        }


        public GlyphData build() {
            return new GlyphData(textureName, color[0], color[1], color[2], color[3]);
        }

    }

    public static Codec<GlyphData> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    ResourceLocation.CODEC.fieldOf("glyph").forGetter(GlyphData::getTextureName),
                    Codec.FLOAT.fieldOf("r").validate(val -> val >= 0 && val <= 1 ? DataResult.success(val) : DataResult.error(() -> "Invalid float range (0..1) for color channel r")).forGetter(glyphData -> glyphData.color[0]),
                    Codec.FLOAT.fieldOf("g").validate(val -> val >= 0 && val <= 1 ? DataResult.success(val) : DataResult.error(() -> "Invalid float range (0..1) for color channel g")).forGetter(glyphData -> glyphData.color[1]),
                    Codec.FLOAT.fieldOf("b").validate(val -> val >= 0 && val <= 1 ? DataResult.success(val) : DataResult.error(() -> "Invalid float range (0..1) for color channel b")).forGetter(glyphData -> glyphData.color[2]),
                    Codec.FLOAT.fieldOf("a").validate(val -> val >= 0 && val <= 1 ? DataResult.success(val) : DataResult.error(() -> "Invalid float range (0..1) for color channel a")).forGetter(glyphData -> glyphData.color[3])
            ).apply(instance, GlyphData::new)
    );

    public static StreamCodec<RegistryFriendlyByteBuf, GlyphData> STREAM_CODEC = new StreamCodec<RegistryFriendlyByteBuf, GlyphData>() {
        @Override
        public GlyphData decode(RegistryFriendlyByteBuf pBuffer) {
            GlyphDataBuilder builder = new GlyphDataBuilder();

            builder.setTextureName(pBuffer.readResourceLocation());
            float r = pBuffer.readFloat();
            float g = pBuffer.readFloat();
            float b = pBuffer.readFloat();
            float a = pBuffer.readFloat();
            builder.setColor(new float[]{r, g, b, a});

            return builder.build();
        }

        @Override
        public void encode(RegistryFriendlyByteBuf pBuffer, GlyphData pValue) {
            pBuffer.writeResourceLocation(pValue.getTextureName());
            pBuffer.writeFloat(pValue.getColor()[0]);
            pBuffer.writeFloat(pValue.getColor()[1]);
            pBuffer.writeFloat(pValue.getColor()[2]);
            pBuffer.writeFloat(pValue.getColor()[3]);
        }
    };
}
