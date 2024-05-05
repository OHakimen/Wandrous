package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.custom.register.WandrousRegistries;
import com.hakimen.wandrous.common.spell.mover.BoomerangMover;
import com.hakimen.wandrous.common.spell.mover.HomingMover;
import com.hakimen.wandrous.common.spell.mover.ISpellMover;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SpellMoverRegister {
    public static final DeferredRegister<ISpellMover> MOVERS = DeferredRegister.create(WandrousRegistries.IMOVER_REGISTER_KEY, Wandrous.MODID);

    public static final DeferredHolder<ISpellMover, ISpellMover> HOMING = MOVERS.register("homing", HomingMover::new);
    public static final DeferredHolder<ISpellMover, ISpellMover> BOOMERANG = MOVERS.register("boomerang", BoomerangMover::new);

    public static void register(IEventBus bus){
        MOVERS.register(bus);
    }
}
