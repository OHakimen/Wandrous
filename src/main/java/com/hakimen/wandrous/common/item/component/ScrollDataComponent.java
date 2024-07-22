package com.hakimen.wandrous.common.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;
import java.util.Objects;

public class ScrollDataComponent {

    public static final ScrollData DEFAULT = new ScrollData(List.of(), "");

    public static class ScrollDataBuilder{

        List<String> spells;
        String name;

        public ScrollDataBuilder() {
        }

        public ScrollDataBuilder(ScrollData data) {
            this.name = data.name;
            this.spells = data.spells;
        }

        public List<String> getSpells() {
            return spells;
        }

        public ScrollDataBuilder setSpells(List<String> spells) {
            this.spells = spells;
            return this;
        }

        public String getName() {
            return name;
        }

        public ScrollDataBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ScrollData build() {
            return new ScrollData(
                    this.spells,
                    this.name
            );
        }
    }

    public static class ScrollData {
        final List<String> spells;
        final String name;

        public ScrollData(List<String> spells, String name) {
            this.spells = spells;
            this.name = name;
        }

        public List<String> getSpells() {
            return spells;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ScrollData that = (ScrollData) o;
            return Objects.equals(spells, that.spells) && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(spells, name);
        }
    }

    public static final Codec<ScrollData> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.list(Codec.STRING).fieldOf("spells").forGetter(scrollData -> scrollData.spells),
                    Codec.STRING.fieldOf("name").forGetter(scrollData -> scrollData.name)
            ).apply(instance, ScrollData::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, ScrollData> STREAM_CODEC = new StreamCodec<RegistryFriendlyByteBuf, ScrollData>() {
        @Override
        public ScrollData decode(RegistryFriendlyByteBuf pBuffer) {
            ScrollDataBuilder builder = new ScrollDataBuilder();
            builder.setName(pBuffer.readUtf());
            builder.setSpells(pBuffer.readList(FriendlyByteBuf::readUtf));

            return builder.build();
        }

        @Override
        public void encode(RegistryFriendlyByteBuf pBuffer, ScrollData pValue) {
            pBuffer.writeUtf(pValue.name);
            pBuffer.writeCollection(pValue.spells, FriendlyByteBuf::writeUtf);
        }
    };
}
