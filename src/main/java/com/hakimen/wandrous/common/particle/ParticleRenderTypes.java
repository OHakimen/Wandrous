package com.hakimen.wandrous.common.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class ParticleRenderTypes {
    public static ParticleRenderType ADDITIVE = new ParticleRenderType() {
        @Nullable
        @Override
        public BufferBuilder begin(Tesselator tesselator, TextureManager textureManager) {
            RenderSystem.depthMask(true);
            RenderSystem.depthFunc(GL11.GL_NOTEQUAL);
            RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES);
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(
                    GlStateManager.SourceFactor.SRC_ALPHA,
                    GlStateManager.DestFactor.ONE,
                    GlStateManager.SourceFactor.ONE_MINUS_SRC_ALPHA,
                    GlStateManager.DestFactor.ZERO
            );
            return tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
        }
    } ;
}
