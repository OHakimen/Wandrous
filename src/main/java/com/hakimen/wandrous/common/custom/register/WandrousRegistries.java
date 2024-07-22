package com.hakimen.wandrous.common.custom.register;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.data.Glyph;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.mover.ISpellMover;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class WandrousRegistries {
    public static final ResourceKey<Registry<SpellEffect>> SPELLS_REGISTER_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "spells"));
    public static final Registry<SpellEffect> SPELLS_REGISTER = new RegistryBuilder<>(SPELLS_REGISTER_KEY)
            .sync(true)
            .create();

    public static final ResourceKey<Registry<ISpellMover>> IMOVER_REGISTER_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "spell_mover"));
    public static final Registry<ISpellMover> IMOVER_REGISTER = new RegistryBuilder<>(IMOVER_REGISTER_KEY)
            .sync(true)
            .create();

    public static final ResourceKey<Registry<Glyph>> GLYPH_REGISTER_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "glyphs"));
    public static final Registry<Glyph> GLYPH_REGISTER = new RegistryBuilder<>(GLYPH_REGISTER_KEY)
            .sync(true)
            .create();

}
