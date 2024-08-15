package com.hakimen.wandrous.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {
    static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue RECHARGE_CRYSTAL = BUILDER
            .comment("The reload time for the recharge crystal")
            .defineInRange("recharge_crystal", 60, 0, 200);

    public static final ModConfigSpec.IntValue GREATER_RECHARGE_CRYSTAL = BUILDER
            .comment("The reload time for the greater recharge crystal")
            .defineInRange("greater_recharge_crystal", 40, 0, 200);

    public static final ModConfigSpec SPEC = BUILDER.build();

}
