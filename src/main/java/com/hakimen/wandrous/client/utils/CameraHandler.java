package com.hakimen.wandrous.client.utils;

import com.hakimen.wandrous.client.utils.camera.CameraShakeAffector;
import net.minecraft.client.Camera;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class CameraHandler {
    public static List<CameraShakeAffector> SHAKERS = new ArrayList<>();

    static float totalIntensity = 0;
    static float offX;
    static float offY;


    public static void cameraTick(Camera camera, RandomSource randomSource){
        if(totalIntensity > 0.01f){
            offX = Mth.nextFloat(randomSource, -totalIntensity * 2, totalIntensity * 2);
            offY = Mth.nextFloat(randomSource, -totalIntensity * 2, totalIntensity * 2);

            camera.setRotation(camera.getYRot() + offY,camera.getXRot() + offX);
        }
    }

    public static void tick(){
        float intensity = 0;
        for (CameraShakeAffector shaker : SHAKERS) {
            intensity += shaker.updateIntensity();
        }
        totalIntensity = intensity;

        totalIntensity = Math.clamp(totalIntensity, 0, 10);
        SHAKERS.removeIf(shaker -> shaker.getTicks() >= shaker.getMaxTicks());
    }

}
