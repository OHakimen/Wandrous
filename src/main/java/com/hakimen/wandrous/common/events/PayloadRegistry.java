package com.hakimen.wandrous.common.events;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.events.payloads.PositionalScreenShakePacket;
import com.hakimen.wandrous.common.events.payloads.SendScreenShakePacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;


@EventBusSubscriber(modid = Wandrous.MODID, bus = EventBusSubscriber.Bus.MOD)
public class PayloadRegistry {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playBidirectional(
                SendScreenShakePacket.TYPE,
                SendScreenShakePacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        SendScreenShakePacket::clientHandler,
                        SendScreenShakePacket::serverHandler
                )
        );

        registrar.playBidirectional(
                PositionalScreenShakePacket.TYPE,
                PositionalScreenShakePacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        PositionalScreenShakePacket::clientHandler,
                        PositionalScreenShakePacket::serverHandler
                )
        );
    }


}
