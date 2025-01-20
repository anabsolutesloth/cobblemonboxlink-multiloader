package com.emperdog.boxlink.network;

import com.emperdog.boxlink.BoxLinkCommon;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record RequestOpenPCPacket(UUID uuid) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<RequestOpenPCPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(BoxLinkCommon.MOD_ID, "open_pc"));

    public static final StreamCodec<ByteBuf, RequestOpenPCPacket> STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC,
            RequestOpenPCPacket::uuid,
            RequestOpenPCPacket::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
