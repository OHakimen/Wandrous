package com.hakimen.wandrous.common.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.*;

public class RaycastUtils {
    public static HitResult raycast(Entity entity, float dst, float pPartialTick) {
        return raycastRotated(entity, Vec3.ZERO, dst, pPartialTick);
    }

    public static HitResult pick(Entity entity, Vec3 offset, double pHitDistance, float pPartialTicks, boolean pHitFluids) {
        Vec3 vec3 = entity.getEyePosition(pPartialTicks);
        Vec3 vec31 = entity.getViewVector(pPartialTicks).xRot((float) offset.x).yRot((float) offset.y).zRot((float) offset.z);
        Vec3 vec32 = vec3.add(vec31.x * pHitDistance, vec31.y * pHitDistance, vec31.z * pHitDistance);
        return entity.level().clip(new ClipContext(vec3, vec32, ClipContext.Block.OUTLINE, pHitFluids ? ClipContext.Fluid.ANY : ClipContext.Fluid.NONE, entity));
    }

    public static HitResult pick(Entity entity, Vec3 pos, Vec3 offset, double pHitDistance, float pPartialTicks, boolean pHitFluids) {
        Vec3 vec3 = pos;
        Vec3 vec31 = entity.getViewVector(pPartialTicks).xRot((float) offset.x).yRot((float) offset.y).zRot((float) offset.z);
        Vec3 vec32 = vec3.add(vec31.x * pHitDistance, vec31.y * pHitDistance, vec31.z * pHitDistance);
        return entity.level().clip(new ClipContext(vec3, vec32, ClipContext.Block.OUTLINE, pHitFluids ? ClipContext.Fluid.ANY : ClipContext.Fluid.NONE, entity));
    }

    public static HitResult raycastRotated(Entity pEntity, Vec3 offset, float dst, float pPartialTick) {
        double actualDist = dst;
        double distanceSqrd = Mth.square(actualDist);
        Vec3 eyePosition = pEntity.getEyePosition(pPartialTick);
        HitResult hitresult = pick(pEntity, offset, actualDist, pPartialTick, false);
        double hitDistance = hitresult.getLocation().distanceToSqr(eyePosition);
        if (hitresult.getType() != HitResult.Type.MISS) {
            distanceSqrd = hitDistance;
            actualDist = Math.sqrt(hitDistance);
        }

        Vec3 viewVector = pEntity.getViewVector(pPartialTick);
        Vec3 increment = eyePosition.add(viewVector.x * actualDist, viewVector.y * actualDist, viewVector.z * actualDist);
        AABB aabb = pEntity.getBoundingBox().expandTowards(viewVector.scale(actualDist)).inflate(1.0, 1.0, 1.0);
        EntityHitResult entityhitresult = ProjectileUtil.getEntityHitResult(
                pEntity, eyePosition, increment, aabb, targeted -> !targeted.isSpectator() && targeted.isPickable(), distanceSqrd
        );
        return entityhitresult != null && entityhitresult.getLocation().distanceToSqr(eyePosition) < hitDistance
                ? filterHitResult(entityhitresult, eyePosition, dst)
                : filterHitResult(hitresult, eyePosition, dst);
    }

    public static HitResult raycastRotatedAtPos(Entity pEntity, Vec3 pos, Vec3 offset, float dst, float pPartialTick) {
        double actualDist = dst;
        double distanceSqrd = Mth.square(actualDist);
        Vec3 eyePosition = pos;
        HitResult hitresult = pick(pEntity, pos, offset, actualDist, pPartialTick, false);
        double hitDistance = hitresult.getLocation().distanceToSqr(eyePosition);
        if (hitresult.getType() != HitResult.Type.MISS) {
            distanceSqrd = hitDistance;
            actualDist = Math.sqrt(hitDistance);
        }

        Vec3 viewVector = pEntity.getViewVector(pPartialTick);
        Vec3 increment = eyePosition.add(viewVector.x * actualDist, viewVector.y * actualDist, viewVector.z * actualDist);
        AABB aabb = pEntity.getBoundingBox().expandTowards(viewVector.scale(actualDist)).inflate(1.0, 1.0, 1.0);
        EntityHitResult entityhitresult = ProjectileUtil.getEntityHitResult(
                pEntity, eyePosition, increment, aabb, targeted -> !targeted.isSpectator() && targeted.isPickable(), distanceSqrd
        );
        return entityhitresult != null && entityhitresult.getLocation().distanceToSqr(eyePosition) < hitDistance
                ? filterHitResult(entityhitresult, eyePosition, dst)
                : filterHitResult(hitresult, eyePosition, dst);
    }


    private static HitResult filterHitResult(HitResult pHitResult, Vec3 pPos, double pBlockInteractionRange) {
        Vec3 vec3 = pHitResult.getLocation();
        if (!vec3.closerThan(pPos, pBlockInteractionRange)) {
            Vec3 vec31 = pHitResult.getLocation();
            Direction direction = Direction.getNearest(vec31.x - pPos.x, vec31.y - pPos.y, vec31.z - pPos.z);
            return BlockHitResult.miss(vec31, direction, BlockPos.containing(vec31));
        } else {
            return pHitResult;
        }
    }
}
