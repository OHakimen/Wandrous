package com.hakimen.wandrous.common.effects;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ScaleEffect extends MobEffect {

    boolean reverse = false;

    public ScaleEffect() {
        super(MobEffectCategory.NEUTRAL,0x720000);
    }

    public ScaleEffect(boolean reverse) {
        this();
        this.reverse = reverse;
    }

    @Override
    public void addAttributeModifiers(AttributeMap pAttributeMap, int pAmplifier) {
        pAttributeMap.getInstance(Attributes.SCALE).addOrUpdateTransientModifier(
                new AttributeModifier(ResourceLocation.withDefaultNamespace("generic.scale"),
                        ((pAmplifier/2f + 1)/4f) * (reverse ? -1 : 1),
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        );
        pAttributeMap.getInstance(Attributes.MOVEMENT_SPEED).addOrUpdateTransientModifier(
                new AttributeModifier(ResourceLocation.withDefaultNamespace("generic.movement_speed"),
                        ((pAmplifier/4f + 1)/4f) * (reverse ? -1 : 1),
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        );

        pAttributeMap.getInstance(Attributes.MAX_HEALTH).addOrUpdateTransientModifier(
                new AttributeModifier(ResourceLocation.withDefaultNamespace("generic.max_health"),
                        ((pAmplifier/4f + 1)/4f) * (reverse ? -1 : 1),
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        );
    }

    @Override
    public void removeAttributeModifiers(AttributeMap pAttributeMap) {
        pAttributeMap.getInstance(Attributes.SCALE).removeModifiers();
        pAttributeMap.getInstance(Attributes.MOVEMENT_SPEED).removeModifiers();
        pAttributeMap.getInstance(Attributes.MAX_HEALTH).removeModifiers();
    }
}
