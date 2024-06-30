package com.hakimen.wandrous;

import com.hakimen.wandrous.common.registers.Registration;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Wandrous.MODID)
public class Wandrous
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "wandrous";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public Wandrous(IEventBus bus) {
        Registration.register(bus);

    }
}
