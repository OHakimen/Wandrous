package com.hakimen.wandrous.common.particle;

import com.hakimen.wandrous.common.registers.ParticleRegister;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class ArcaneKnowledgeParticle extends TextureSheetParticle {

    private final SpriteSet sprites;

    public ArcaneKnowledgeParticle(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet sprites, double vX, double vY, double vZ) {
        super(pLevel, pX, pY, pZ);

        this.xd = vX;
        this.yd = vY;
        this.zd = vZ;
        this.quadSize *= 0.85f;

        this.gravity = 0;
        this.lifetime = 50;

        this.sprites = sprites;
        this.sprite = sprites.get(pLevel.random);
    }

    @Override
    protected int getLightColor(float pPartialTick) {
        return 0xf000f0;
    }

    @Override
    public void tick() {
        super.tick();
        alpha = 1 - ((float) age / lifetime);
    }


    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class ArcaneKnowledgeParticleType extends ParticleType<ArcaneKnowledgeParticleOptions> {
        public ArcaneKnowledgeParticleType() {
            super(true);
        }

        @Override
        public MapCodec<ArcaneKnowledgeParticleOptions> codec() {
            return RecordCodecBuilder.mapCodec(arcaneKnowledgeParticleOptionsInstance -> arcaneKnowledgeParticleOptionsInstance.group(
                    Codec.FLOAT.fieldOf("r").validate(val -> val >= 0 && val <= 1 ? DataResult.success(val) : DataResult.error(() -> "Invalid float range (0..1) for color channel r")).forGetter(particle -> particle.rgb[0]),
                    Codec.FLOAT.fieldOf("g").validate(val -> val >= 0 && val <= 1 ? DataResult.success(val) : DataResult.error(() -> "Invalid float range (0..1) for color channel g")).forGetter(particle -> particle.rgb[1]),
                    Codec.FLOAT.fieldOf("b").validate(val -> val >= 0 && val <= 1 ? DataResult.success(val) : DataResult.error(() -> "Invalid float range (0..1) for color channel b")).forGetter(particle -> particle.rgb[2])
                    ).apply(arcaneKnowledgeParticleOptionsInstance, ArcaneKnowledgeParticleOptions::new));
        }

        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, ArcaneKnowledgeParticleOptions> streamCodec() {
            return new StreamCodec<RegistryFriendlyByteBuf, ArcaneKnowledgeParticleOptions>() {
                @Override
                public ArcaneKnowledgeParticleOptions decode(RegistryFriendlyByteBuf pBuffer) {
                    float r = pBuffer.readFloat();
                    float g = pBuffer.readFloat();
                    float b = pBuffer.readFloat();
                    return new ArcaneKnowledgeParticleOptions(r, g, b);
                }

                @Override
                public void encode(RegistryFriendlyByteBuf pBuffer, ArcaneKnowledgeParticleOptions pValue) {
                    pBuffer.writeFloat(pValue.rgb[0]);
                    pBuffer.writeFloat(pValue.rgb[1]);
                    pBuffer.writeFloat(pValue.rgb[2]);
                }
            };
        }
    }

    public static class ArcaneKnowledgeParticleOptions implements ParticleOptions {

        float[] rgb;

        public ArcaneKnowledgeParticleOptions(float r,float g,float b) {
            this.rgb = new float[]{r,g,b};
        }

        @Override
        public ParticleType<?> getType() {
            return ParticleRegister.KNOWLEDGE.get();
        }
    }


    @OnlyIn(Dist.CLIENT)
    public static class ArcaneKnowledgeParticleProvider implements ParticleProvider<ArcaneKnowledgeParticleOptions> {
        private final SpriteSet sprites;

        public ArcaneKnowledgeParticleProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(ArcaneKnowledgeParticleOptions simpleParticleType, ClientLevel clientLevel, double x, double y, double z, double vX, double vY, double vZ) {

            ArcaneKnowledgeParticle particle = new ArcaneKnowledgeParticle(clientLevel, x, y, z, sprites, vX, vY + 0.4, vZ);

            particle.setColor(simpleParticleType.rgb[0], simpleParticleType.rgb[1], simpleParticleType.rgb[2]);

            particle.xd *= 0.1f;
            particle.yd *= 0.1f;
            particle.zd *= 0.1f;
            return particle;
        }

    }
}
