package com.hakimen.wandrous.mixin.client;

import com.hakimen.wandrous.client.model.DynamicTextureModel;
import com.hakimen.wandrous.common.item.DynamicModelled;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemModelShaper.class)
public abstract class DynamicModelItemShaperMixin {

    @Shadow @Final private ModelManager modelManager;

    @Inject(method="getItemModel(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/client/resources/model/BakedModel;",
            at=@At("HEAD"), cancellable=true)
    private void getDynamicModel(ItemStack pItem, CallbackInfoReturnable<BakedModel> cir){
        if(pItem.getItem() instanceof DynamicModelled dynamicModelItem){
            DynamicTextureModel override = dynamicModelItem.makeModel(pItem);
            if(override == null) return;
            BakedModel model = override.getBakedModel(modelManager);
            if(model != null){
                cir.setReturnValue(model);
            }
        }
    }

}
