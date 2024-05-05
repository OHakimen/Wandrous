package com.hakimen.wandrous.common.particle;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class GlimmeringBoltParticle extends TextureSheetParticle {

    private final SpriteSet sprites;

    public GlimmeringBoltParticle(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet sprites, double vX, double vY, double vZ) {
        super(pLevel, pX, pY, pZ);

        this.xd = vX;
        this.yd = vY;
        this.zd = vZ;
        this.quadSize *= 0.85f;

        this.gravity = 0;
        this.lifetime = 50;

        this.sprites = sprites;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
        this.setAlpha(1 - ((float) this.age /this.lifetime));
    }


    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }


    @OnlyIn(Dist.CLIENT)
    public static class GlimmeringProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public GlimmeringProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double x, double y, double z, double vX, double vY, double vZ) {
            Random r = new Random();

            GlimmeringBoltParticle particle =  new GlimmeringBoltParticle(clientLevel, x, y, z, sprites, vX + r.nextFloat(), vY + r.nextFloat(), vZ + r.nextFloat());

            float brightness = r.nextFloat(0.25f,0.5f);
            particle.setColor(1.0f,brightness,0.992f);

            particle.xd *= 0.1f;
            particle.yd *= 0.1f;
            particle.zd *= 0.1f;
            return particle;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class GlimmeringHitProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public GlimmeringHitProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double x, double y, double z, double vX, double vY, double vZ) {
            Random r = new Random();

            GlimmeringBoltParticle particle =  new GlimmeringBoltParticle(clientLevel, x, y, z, sprites, vX + r.nextFloat(), vY + r.nextFloat(), vZ + r.nextFloat());

            float brightness = r.nextFloat(0.15f,0.25f);
            particle.setColor(0.475f,0.f+brightness,0.678f);

            particle.xd *= 0.05f;
            particle.yd *= 0.05f;
            particle.zd *= 0.05f;
            return particle;
        }
    }
}
