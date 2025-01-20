package com.emperdog.boxlink.network;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class NeoForgePacketHandler {
    public static void registerPayloadHandlers(final RegisterPayloadHandlersEvent event) {
        event.registrar("1.0").playToServer(
                RequestOpenPCPacket.TYPE,
                RequestOpenPCPacket.STREAM_CODEC,
                NeoForgePacketHandler::handlePCPacket
        );
    }

    public static void handlePCPacket(final RequestOpenPCPacket data, final IPayloadContext context) {
        BoxLinkServerPayloadHandler.handlePCPacket(data, (ServerPlayer) context.player());
    }
}
