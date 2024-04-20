package com.hakimen.wandrous.common.mixin.server;


import net.minecraft.world.entity.projectile.LargeFireball;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LargeFireball.class)
public interface FireballExplosionPowerAccessorMixin {
    @Accessor
    int getExplosionPower();

    @Accessor
    void setExplosionPower(int power);
}
