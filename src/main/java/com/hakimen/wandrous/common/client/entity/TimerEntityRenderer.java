package com.hakimen.wandrous.common.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.entity.TimerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class TimerEntityRenderer extends EntityRenderer<TimerEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Wandrous.MODID, "");

    public TimerEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(TimerEntity timerEntity) {
        return TEXTURE;
    }
}
