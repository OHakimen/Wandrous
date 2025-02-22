package com.hakimen.wandrous.common.events;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.payloads.PositionalScreenShakePacket;
import com.hakimen.wandrous.common.payloads.SendScreenShakePacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;


@EventBusSubscriber(modid = Wandrous.MODID, bus = EventBusSubscriber.Bus.MOD)
public class PayloadRegistry {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playToClient(
                SendScreenShakePacket.TYPE,
                SendScreenShakePacket.STREAM_CODEC,
                SendScreenShakePacket::clientHandler
        );

        registrar.playToClient(
                PositionalScreenShakePacket.TYPE,
                PositionalScreenShakePacket.STREAM_CODEC,
                PositionalScreenShakePacket::clientHandler
        );

    }


}
