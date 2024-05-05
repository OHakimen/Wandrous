package com.hakimen.wandrous.common.registers;

import net.neoforged.bus.api.IEventBus;

public class Registration {

    public static void register(IEventBus bus){

        SoundRegister.register(bus);

        EffectRegister.register(bus);
        ItemRegister.register(bus);
        BlockRegister.register(bus);
        ContainerRegister.register(bus);
        EntityRegister.register(bus);
        BlockEntityRegister.register(bus);

        ParticleRegister.register(bus);

        SpellMoverRegister.register(bus);
        SpellRegister.register(bus);

    }
}
