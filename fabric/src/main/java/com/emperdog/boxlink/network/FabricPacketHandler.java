package com.emperdog.boxlink.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class FabricPacketHandler {

    public static void handlePCPacket(final RequestOpenPCPacket data, ServerPlayNetworking.Context context) {
        BoxLinkServerPayloadHandler.handlePCPacket(data, context.player());
    }

    public static void registerPackets() {

        PayloadTypeRegistry.playC2S().register(RequestOpenPCPacket.TYPE, RequestOpenPCPacket.STREAM_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(RequestOpenPCPacket.TYPE, FabricPacketHandler::handlePCPacket);
    }
}
