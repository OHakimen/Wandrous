package com.hakimen.wandrous.common.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

import java.util.Objects;

public class SpellCastsDataComponent{

    public static final SpellCastsData DEFAULT = new SpellCastsData(-1,-1);

    public static class SpellCastsData {
        private final int maxCasts;
        private final int remaining;

        public SpellCastsData(int maxCasts, int remaining) {
            this.maxCasts = maxCasts;
            this.remaining = remaining;
        }

        public int getMaxCasts() {
            return maxCasts;
        }

        public int getRemaining() {
            return remaining;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SpellCastsData that = (SpellCastsData) o;
            return maxCasts == that.maxCasts && remaining == that.remaining;
        }

        @Override
        public int hashCode() {
            return Objects.hash(maxCasts, remaining);
        }
    }

    public static class SpellCastsDataBuilder{
        private int maxCasts;
        private int remaining;

        public int getMaxCasts() {
            return maxCasts;
        }

        public SpellCastsDataBuilder setMaxCasts(int maxCasts) {
            this.maxCasts = maxCasts;
            return this;
        }

        public int getRemaining() {
            return remaining;
        }

        public SpellCastsDataBuilder setRemaining(int remaining) {
            this.remaining = remaining;
            return this;
        }

        public SpellCastsData build(){
            return new SpellCastsData(maxCasts, remaining);
        }

        public SpellCastsDataBuilder(SpellCastsData spellCastsData){
            this.maxCasts = spellCastsData.maxCasts;
            this.remaining = spellCastsData.remaining;
        }

        public SpellCastsDataBuilder() {
        }
    }

    public static final Codec<SpellCastsDataComponent.SpellCastsData> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("max_casts").forGetter(SpellCastsData::getMaxCasts),
                    Codec.INT.fieldOf("remaining").forGetter(SpellCastsData::getRemaining)
            ).apply(instance, SpellCastsData::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SpellCastsDataComponent.SpellCastsData> STREAM_CODEC = new StreamCodec<RegistryFriendlyByteBuf, SpellCastsDataComponent.SpellCastsData>() {
        @Override
        public SpellCastsDataComponent.SpellCastsData decode(RegistryFriendlyByteBuf pBuffer) {
            SpellCastsDataComponent.SpellCastsDataBuilder builder = new SpellCastsDataComponent.SpellCastsDataBuilder();
            builder.setMaxCasts(pBuffer.readInt());
            builder.setRemaining(pBuffer.readInt());

            return builder.build();
        }

        @Override
        public void encode(RegistryFriendlyByteBuf pBuffer, SpellCastsDataComponent.SpellCastsData pValue) {
            pBuffer.writeInt(pValue.getMaxCasts());
            pBuffer.writeInt(pValue.getRemaining());
        }
    };
}
