package com.hakimen.wandrous.common.events.payloads;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.utils.CameraHandler;
import com.hakimen.wandrous.client.utils.camera.PositionalCameraShakeAffector;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.joml.Vector3f;

public record PositionalScreenShakePacket(float intensity, int ticks, int maxTicks, Vector3f position,
                                          float radius) implements CustomPacketPayload {
    public static final Type<PositionalScreenShakePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "positional_screen_shake"));

    public static final StreamCodec<ByteBuf, PositionalScreenShakePacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT,
            PositionalScreenShakePacket::intensity,
            ByteBufCodecs.VAR_INT,
            PositionalScreenShakePacket::ticks,
            ByteBufCodecs.VAR_INT,
            PositionalScreenShakePacket::maxTicks,
            ByteBufCodecs.VECTOR3F,
            PositionalScreenShakePacket::position,
            ByteBufCodecs.FLOAT,
            PositionalScreenShakePacket::radius,
            PositionalScreenShakePacket::new
    );

    public static void clientHandler(PositionalScreenShakePacket packet, IPayloadContext context) {
        context.enqueueWork(
                () -> {
                    CameraHandler.SHAKERS.add(new PositionalCameraShakeAffector()
                            .setPosition(new Vec3(packet.position))
                            .setRadius(packet.radius)
                            .setIntensity(packet.intensity)
                            .setTicks(packet.ticks)
                            .setMaxTicks(packet.maxTicks));
                }
        );
    }

    public static void serverHandler(PositionalScreenShakePacket packet, IPayloadContext context) {

    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}