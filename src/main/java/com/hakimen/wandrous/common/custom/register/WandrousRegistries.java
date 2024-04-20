package com.hakimen.wandrous.common.custom.register;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.spell.SpellEffect;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class WandrousRegistries {
    public static final ResourceKey<Registry<SpellEffect>> SPELLS_REGISTER_KEY = ResourceKey.createRegistryKey(new ResourceLocation(Wandrous.MODID, "spells"));
    public static final Registry<SpellEffect> SPELLS_REGISTER = new RegistryBuilder<>(SPELLS_REGISTER_KEY)
            .sync(true)
            .create();
}
