package com.hakimen.wandrous.common.particle;

import com.hakimen.wandrous.client.utils.EasingUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

public class ShockwaveParticle extends TextureSheetParticle {

    SpriteSet sprites;

    public ShockwaveParticle(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet sprites, double vX, double vY, double vZ) {
        super(pLevel, pX, pY, pZ);

        this.xd = vX;
        this.yd = vY;
        this.zd = vZ;

        this.gravity = 0;
        this.lifetime = 320;

        this.sprites = sprites;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
        this.setAlpha((float) (1 - EasingUtils.easeInOutQuart(((float) this.age / this.lifetime))));
    }

    @Override
    public void render(VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {

        Quaternionf quaternionf = new Quaternionf();
        this.getFacingCameraMode().setRotation(quaternionf, pRenderInfo, pPartialTicks);

        if (this.roll != 0.0F) {
            quaternionf.rotateZ(Mth.lerp(pPartialTicks, this.oRoll, this.roll));
        }

        this.quadSize += ((this.age + pPartialTicks) / this.lifetime) * 0.24f;

        quaternionf.rotationXYZ((float) -(Math.PI * .5), 0, 0);

        this.renderRotatedQuad(pBuffer, pRenderInfo, quaternionf, pPartialTicks);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return new ParticleRenderType() {
            @Nullable
            @Override
            public BufferBuilder begin(Tesselator p_350949_, TextureManager p_107437_) {
                RenderSystem.depthMask(true);
                RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES);
                RenderSystem.enableBlend();
                RenderSystem.disableCull();
                RenderSystem.defaultBlendFunc();
                return p_350949_.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
            }
        };
    }

    @OnlyIn(Dist.CLIENT)
    public static class ShockwaveProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public ShockwaveProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double x, double y, double z, double vX, double vY, double vZ) {
            ShockwaveParticle particle =  new ShockwaveParticle(clientLevel, x, y, z, sprites, 0,0,0);

            return particle;
        }
    }
}
