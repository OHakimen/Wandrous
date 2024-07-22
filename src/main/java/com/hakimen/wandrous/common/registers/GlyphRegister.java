package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.custom.register.WandrousRegistries;
import com.hakimen.wandrous.common.data.Glyph;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GlyphRegister {

    public static float[] DEFAULT_COLOR = {0f,1f,0.68f, 1f};

    public static final DeferredRegister<Glyph> GLYPHS = DeferredRegister.create(WandrousRegistries.GLYPH_REGISTER, Wandrous.MODID);

    public static final DeferredHolder<Glyph,Glyph> BIND = GLYPHS.register("bind", (rl) -> new Glyph(rl, DEFAULT_COLOR));
    public static final DeferredHolder<Glyph,Glyph> CONTROL = GLYPHS.register("control", (rl) -> new Glyph(rl, DEFAULT_COLOR));
    public static final DeferredHolder<Glyph,Glyph> DESTINY = GLYPHS.register("destiny", (rl) -> new Glyph(rl, DEFAULT_COLOR));
    public static final DeferredHolder<Glyph,Glyph> FOCUS = GLYPHS.register("focus", (rl) -> new Glyph(rl, DEFAULT_COLOR));
    public static final DeferredHolder<Glyph,Glyph> GUIDANCE = GLYPHS.register("guidance", (rl) -> new Glyph(rl, DEFAULT_COLOR));
    public static final DeferredHolder<Glyph,Glyph> KNOWLEDGE = GLYPHS.register("knowledge", (rl) -> new Glyph(rl, DEFAULT_COLOR));
    public static final DeferredHolder<Glyph,Glyph> MIND = GLYPHS.register("mind", (rl) -> new Glyph(rl, DEFAULT_COLOR));
    public static final DeferredHolder<Glyph,Glyph> NEW = GLYPHS.register("new", (rl) -> new Glyph(rl, DEFAULT_COLOR));
    public static final DeferredHolder<Glyph,Glyph> POWER = GLYPHS.register("power", (rl) -> new Glyph(rl, DEFAULT_COLOR));
    public static final DeferredHolder<Glyph,Glyph> TINKER = GLYPHS.register("tinker", (rl) -> new Glyph(rl, DEFAULT_COLOR));
    public static final DeferredHolder<Glyph,Glyph> VITALITY = GLYPHS.register("vitality", (rl) -> new Glyph(rl, DEFAULT_COLOR));
    public static final DeferredHolder<Glyph,Glyph> WEAVE = GLYPHS.register("weave", (rl) -> new Glyph(rl, DEFAULT_COLOR));

    public static void register(IEventBus bus){
        GLYPHS.register(bus);
    }
}
