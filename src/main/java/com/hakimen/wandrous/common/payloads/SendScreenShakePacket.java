package com.hakimen.wandrous.common.payloads;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.utils.CameraHandler;
import com.hakimen.wandrous.client.utils.camera.CameraShakeAffector;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SendScreenShakePacket(float intensity, int ticks, int maxTicks) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<SendScreenShakePacket> TYPE = new CustomPacketPayload.Type<SendScreenShakePacket>(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "screen_shake"));

        public static final StreamCodec<ByteBuf, SendScreenShakePacket> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.FLOAT,
                SendScreenShakePacket::intensity,
                ByteBufCodecs.VAR_INT,
                SendScreenShakePacket::ticks,
                ByteBufCodecs.VAR_INT,
                SendScreenShakePacket::maxTicks,
                SendScreenShakePacket::new
        );

        public static void clientHandler(SendScreenShakePacket packet, IPayloadContext context) {
            context.enqueueWork(
                    () -> {
                        CameraHandler.SHAKERS.add(new CameraShakeAffector().setIntensity(packet.intensity)
                                .setTicks(packet.ticks)
                                .setMaxTicks(packet.maxTicks));
                    }
            );
        }

        public static void serverHandler(SendScreenShakePacket packet, IPayloadContext context) {

        }

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }