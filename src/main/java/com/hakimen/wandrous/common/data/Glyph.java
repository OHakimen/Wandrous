package com.hakimen.wandrous.common.data;

import net.minecraft.resources.ResourceLocation;

public class Glyph {
    public ResourceLocation id;
    public ResourceLocation texture;
    public float[] color;

    public Glyph(ResourceLocation id, float[] color) {
        this.id = id;
        this.texture = id;
        this.color = color;
    }

    public Glyph() {
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public Glyph setTexture(ResourceLocation texture) {
        this.texture = texture;
        return this;
    }

    public float[] getColor() {
        return color;
    }

    public Glyph setColor(float[] color) {
        this.color = color;
        return this;
    }

    public Glyph(ResourceLocation id, ResourceLocation texture, float[] color) {
        this.id = id;
        this.texture = texture;
        this.color = color;
    }

    public ResourceLocation getId() {
        return id;
    }

    public Glyph setId(ResourceLocation id) {
        this.id = id;
        return this;
    }
}
