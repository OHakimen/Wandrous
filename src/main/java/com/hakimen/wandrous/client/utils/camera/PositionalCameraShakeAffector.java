package com.hakimen.wandrous.client.utils.camera;

import com.hakimen.wandrous.client.utils.EasingUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PositionalCameraShakeAffector extends CameraShakeAffector{
    Vec3 position;
    float radius;



    public Vec3 getPosition() {
        return position;
    }

    public PositionalCameraShakeAffector setPosition(Vec3 position) {
        this.position = position;
        return this;
    }

    public float getRadius() {
        return radius;
    }

    public PositionalCameraShakeAffector setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public float updateIntensity() {
        ticks++;
        float percent = (float) ticks / maxTicks;
        float easedValue = (float) (percent < 0.5f ? Mth.lerp(EasingUtils.easeInOutQuart(percent), 0, intensity) : Mth.lerp(EasingUtils.easeInOutQuart(percent), intensity, 0));

        Vec3 localPlayerPos = Minecraft.getInstance().player.getPosition(0);

        return (float) Mth.map(localPlayerPos.distanceTo(position) / radius, 0,1,1,0) * easedValue;
    }
}