package com.hakimen.wandrous.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfig {
    static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue SCREEN_SHAKE_THRESHOLD = BUILDER
            .comment("The maximun intensity of screen shakes (0 to disable)")
            .defineInRange("screen_shake_threshold", 20, 0, 100);

    public static final ModConfigSpec SPEC = BUILDER.build();


}
