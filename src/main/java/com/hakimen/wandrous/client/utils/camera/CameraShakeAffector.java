package com.hakimen.wandrous.client.utils.camera;

import com.hakimen.wandrous.client.utils.EasingUtils;
import net.minecraft.util.Mth;

public class CameraShakeAffector {
    int ticks;
    int maxTicks;
    float intensity;

    public int getTicks() {
        return ticks;
    }

    public CameraShakeAffector setTicks(int ticks) {
        this.ticks = ticks;
        return this;
    }

    public int getMaxTicks() {
        return maxTicks;
    }

    public CameraShakeAffector setMaxTicks(int maxTicks) {
        this.maxTicks = maxTicks;
        return this;
    }

    public float getIntensity() {
        return intensity;
    }

    public CameraShakeAffector setIntensity(float intensity) {
        this.intensity = intensity;
        return this;
    }

    public float updateIntensity() {
        ticks++;
        float percent = (float) ticks / maxTicks;
        return (float) (percent < 0.5f ? Mth.lerp(EasingUtils.easeInOutQuart(percent), 0, intensity) : Mth.lerp(EasingUtils.easeInOutQuart(percent), intensity, 0));
    }
}