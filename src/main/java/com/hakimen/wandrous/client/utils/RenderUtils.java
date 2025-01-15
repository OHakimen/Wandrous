package com.hakimen.wandrous.client.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class RenderUtils {
    public static Vector3f sphereWithUVFromRadius(float u, float v, float r) {
        return new Vector3f(Mth.cos(u) * Mth.sin(v) * r, Mth.cos(v) * r, Mth.sin(u) * Mth.sin(v) * r);
    }


    public static void makeVertexPosColorUVLight(VertexConsumer vertexConsumer, Matrix4f last, float x, float y, float z, float r, float g, float b, float a, float u, float v, int light) {
        vertexConsumer.addVertex(last, x, y, z).setColor(r, g, b, a).setUv(u, v).setUv2(light,light).setOverlay(0);
    }

    public static void makeVertexPosUVLightNormal(VertexConsumer vertexConsumer, Matrix4f last,  float x, float y, float z, float u, float v, int light, Matrix3f pNormal){
        vertexConsumer.addVertex(last, x, y, z).setColor(255,255,255,255).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(light,light).setNormal( 1,1,1);
    }
    public static void makeVertexPosUVLightNormalColor(VertexConsumer vertexConsumer, Matrix4f last,  float x, float y, float z, float u, float v, int light, Matrix3f pNormal,float r, float g, float b, float a){
        vertexConsumer.addVertex(last, x, y, z).setColor(r,g,b,a).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(light,light).setNormal(1,1,1);
    }

    public static void guiQuad(VertexConsumer consumer, Matrix4f mat, PoseStack.Pose norm, Vector3f pos, Vector3f size, Vector2f uvPos, Vector2f uvSize, int light, float[] color) {
        guiVertex(consumer, mat, norm, new Vector3f(pos), uvPos, light, color);
        guiVertex(consumer, mat, norm, new Vector3f(pos).add(0, size.y, 0), new Vector2f(uvPos).add(0, uvSize.y), light, color);
        guiVertex(consumer, mat, norm, new Vector3f(pos).add(size.x, size.y, 0), new Vector2f(uvPos).add(uvSize), light, color);
        guiVertex(consumer, mat, norm, new Vector3f(pos).add(size.x, 0, 0), new Vector2f(uvPos).add(uvSize.x, 0), light, color);
    }

    public static void guiVertex(VertexConsumer consumer, Matrix4f mat, PoseStack.Pose norm, Vector3f pos, Vector2f uv, int light, float[] color) {
        consumer
                .addVertex(mat, pos.x, pos.y, pos.z)
                .setColor(color[0], color[1], color[2], color[3])
                .setUv(uv.x, uv.y)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv2(light, light)
                .setNormal(norm, -1, -1, -1);
    }
}
