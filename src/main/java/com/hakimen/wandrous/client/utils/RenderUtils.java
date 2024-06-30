package com.hakimen.wandrous.client.utils;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class RenderUtils {
    public static Vector3f sphereWithUVFromRadius(float u, float v, float r) {
        return new Vector3f(Mth.cos(u) * Mth.sin(v) * r, Mth.cos(v) * r, Mth.sin(u) * Mth.sin(v) * r);
    }


    public static void makeVertexPosColorUVLight(VertexConsumer vertexConsumer, Matrix4f last, float x, float y, float z, float r, float g, float b, float a, float u, float v, int light) {
        vertexConsumer.addVertex(last, x, y, z).setColor(r, g, b, a).setUv(u, v).setUv2(light,light).setOverlay(0).setNormal(0,1,0);
    }

    public static void makeVertexPosUVLightNormal(VertexConsumer vertexConsumer, Matrix4f last,  float x, float y, float z, float u, float v, int light, Matrix3f pNormal){
        vertexConsumer.addVertex(last, x, y, z).setColor(255,255,255,255).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(light,light).setNormal( 1,1,1);
    }
    public static void makeVertexPosUVLightNormalColor(VertexConsumer vertexConsumer, Matrix4f last,  float x, float y, float z, float u, float v, int light, Matrix3f pNormal,float r, float g, float b, float a){
        vertexConsumer.addVertex(last, x, y, z).setColor(r,g,b,a).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(light,light).setNormal(1,1,1);
    }
}
