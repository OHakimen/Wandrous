package com.hakimen.wandrous.mixin.client;

import com.hakimen.wandrous.client.utils.CameraHandler;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin {


    @Shadow @Deprecated protected abstract void setRotation(float pYRot, float pXRot);

    @Shadow public abstract Entity getEntity();

    @Inject(at = @At("RETURN"), method = "setup", cancellable = true)
    public void setup(BlockGetter pLevel, Entity pEntity, boolean pDetached, boolean pThirdPersonReverse, float pPartialTick, CallbackInfo ci) {
        CameraHandler.cameraTick(((Camera)(Object)this), getEntity().getRandom());
    }

    @Inject(at = @At("RETURN"), method = "tick", cancellable = true)
    public void tick(CallbackInfo ci) {
        CameraHandler.tick();
    }
}
