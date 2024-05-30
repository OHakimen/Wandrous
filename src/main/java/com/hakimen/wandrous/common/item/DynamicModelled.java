package com.hakimen.wandrous.common.item;

import com.hakimen.wandrous.client.model.DynamicTextureModel;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public interface DynamicModelled {

    @OnlyIn(Dist.CLIENT)
    DynamicTextureModel makeModel(ItemStack stack);
}
