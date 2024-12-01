package com.hakimen.wandrous.client.renderTypes;

import com.hakimen.wandrous.Wandrous;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

import java.io.IOException;
import java.util.function.Function;

public class WandrousRenderTypes {
    public static RenderType triangle(ResourceLocation texture) {
        return RenderTypes.TRIANGLE.apply(texture);
    }


    @EventBusSubscriber(value = Dist.CLIENT, modid = Wandrous.MODID, bus = EventBusSubscriber.Bus.MOD)
    public static class ModClientEvents
    {
        @SubscribeEvent
        public static void shaderRegistry(RegisterShadersEvent event) throws IOException
        {
            // Adds a shader to the list, the callback runs when loading is complete.
            event.registerShader(new ShaderInstance(event.getResourceProvider(), ResourceLocation.fromNamespaceAndPath(Wandrous.MODID,"rendertype_triangle"), DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP), shaderInstance -> {
                RenderTypes.triangle = shaderInstance;
            });

        }
    }

    private static class RenderTypes extends RenderType
    {
        // Holds the object loaded via RegisterShadersEvent
        private static ShaderInstance triangle;


        // Shader state for use in the render type, the supplier ensures it updates automatically with resource reloads
        private static final ShaderStateShard RENDERTYPE_TRIANGLE = new ShaderStateShard(() -> triangle);

        // Dummy constructor needed to make java happy
        private RenderTypes(String s, VertexFormat v, VertexFormat.Mode m, int i, boolean b, boolean b2, Runnable r, Runnable r2)
        {
            super(s, v, m, i, b, b2, r, r2);
            throw new IllegalStateException("This class is not meant to be constructed!");
        }

        // The memoize caches the output value for each input, meaning the expensive registration process doesn't have to rerun
        public static Function<ResourceLocation, RenderType> TRIANGLE = Util.memoize(RenderTypes::triangle);
        // Defines the RenderType. Make sure the name is unique by including your MODID in the name.
        private static RenderType triangle(ResourceLocation locationIn)
        {
            RenderType.CompositeState rendertype$state = RenderType.CompositeState.builder()
                    .setShaderState(RENDERTYPE_TRIANGLE)
                    .setTextureState(new RenderStateShard.TextureStateShard(locationIn, true, false))
                    .setLightmapState(RenderStateShard.NO_LIGHTMAP)
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setCullState(RenderStateShard.NO_CULL)
                    .createCompositeState(false);
            return create("wandrous_triangle", DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP, VertexFormat.Mode.TRIANGLE_STRIP, 1536, false, true, rendertype$state);
        }
    }

}
