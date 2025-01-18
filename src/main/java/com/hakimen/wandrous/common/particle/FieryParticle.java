package com.hakimen.wandrous.common.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class FieryParticle extends TextureSheetParticle {


    private final SpriteSet sprites;

    public FieryParticle(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet sprites, double vX, double vY, double vZ) {
        super(pLevel, pX, pY, pZ);

        this.xd = vX;
        this.yd = vY;
        this.zd = vZ;
        this.quadSize *= 4f;

        this.gravity = 0;
        this.lifetime = 40;

        this.sprites = sprites;
        this.setSprite(sprites.get(this.random));
    }

    @Override
    public void tick() {
        super.tick();
        this.setAlpha(1 - ((float) this.age /this.lifetime));
        this.quadSize = Math.max(0f, this.quadSize * 0.9f);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderTypes.ADDITIVE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class FieryProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public FieryProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double x, double y, double z, double vX, double vY, double vZ) {
            Random r = new Random();

            FieryParticle particle =  new FieryParticle(clientLevel, x, y, z, sprites, vX * 0.1, vY * 0.1, vZ * 0.1);
            if(r.nextFloat() > 0.95) {
                particle.setColor(1f,1f,0.5f);
            }
            else if(r.nextFloat() > 0.75){
                particle.setColor(1f, 0.3f, 0);
            } else if(r.nextFloat() > 0.45){
                particle.setColor(1f, 0.2f, 0);
            }else {
                particle.setColor(1f, 0.1f, 0);
            }
            return particle;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class FreezingGazeProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public FreezingGazeProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double x, double y, double z, double vX, double vY, double vZ) {
            Random r = new Random();

            FieryParticle particle = new FieryParticle(clientLevel, x, y, z, sprites, vX * 0.1, vY * 0.1, vZ * 0.1);
            if (r.nextFloat() > 0.95) {
                particle.setColor(1f, 1f, 1);
            } else if (r.nextFloat() > 0.75) {
                particle.setColor(0f, 1f, 1);
            } else if (r.nextFloat() > 0.45) {
                particle.setColor(0, 0.8f, 1f);
            } else {
                particle.setColor(0, 0.6f, 1f);
            }
            return particle;
        }
    }
}
