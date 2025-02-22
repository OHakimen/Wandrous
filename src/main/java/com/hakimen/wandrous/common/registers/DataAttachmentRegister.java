package com.hakimen.wandrous.common.registers;

import com.hakimen.wandrous.Wandrous;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class DataAttachmentRegister {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Wandrous.MODID);


    public static void register(IEventBus bus){
        ATTACHMENT_TYPES.register(bus);
    }
}
