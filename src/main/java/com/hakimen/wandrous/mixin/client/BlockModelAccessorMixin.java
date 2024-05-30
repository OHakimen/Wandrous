package com.hakimen.wandrous.mixin.client;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;



@Mixin(BlockModel.class)
public interface BlockModelAccessorMixin {
    @Accessor("parentLocation")
    ResourceLocation getParentLocation();

    @Accessor("parent")
    BlockModel getParent();

}
