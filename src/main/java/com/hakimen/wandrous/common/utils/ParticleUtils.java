package com.hakimen.wandrous.common.utils;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.phys.Vec3;

public class ParticleUtils {
    public static Vec3 getBloodColorForEntity(Entity liv) {
        return switch (liv) {
            case EnderMan enderMan -> new Vec3(0.4, 0., 0.4);

            case Phantom phantom -> new Vec3(0.141, 1., 0.255);

            default -> new Vec3(0.2, 0, 0);
        };
    }
}
