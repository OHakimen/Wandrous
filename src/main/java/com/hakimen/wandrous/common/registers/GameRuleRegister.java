package com.hakimen.wandrous.common.registers;

import net.minecraft.world.level.GameRules;

public class GameRuleRegister {

    public static final GameRules.Key<GameRules.BooleanValue> RULE_CREATIVE_CASTING =  GameRules.register("wandrous:creative_casting", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));

    public static void registerGameRules() {
    }
}
