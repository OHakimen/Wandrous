package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;

public class DamageTypeRegister {
    public static final ResourceKey<DamageType> SLICE_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "slice"));

    public static final ResourceKey<DamageType> NUKE =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "nuke"));

    public static final ResourceKey<DamageType> BEAM =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "beam"));


    public static DamageSource sliceDamage(Entity causer) {
        return new DamageSource(
                causer.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(SLICE_DAMAGE),
                causer);
    }

    public static DamageSource nuke(Entity causer) {
        return new DamageSource(
                causer.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(NUKE),
                causer);
    }

    public static DamageSource beam(Entity causer) {
        return new DamageSource(
                causer.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(BEAM),
                causer);
    }

    public static void register(){
        //We just bootstrap this :3
    }
}
    