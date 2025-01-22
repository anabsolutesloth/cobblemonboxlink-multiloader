package com.emperdog.boxlink.event;

import com.emperdog.boxlink.BoxLinkClient;
import com.emperdog.boxlink.network.RequestOpenPCPacket;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;

public class FabricClientEvents {
    public static void register() {
        ClientTickEvents.START_CLIENT_TICK.register((minecraft) -> {
            if(BoxLinkClient.openPCKey.consumeClick() && Minecraft.getInstance().isWindowActive())
                ClientPlayNetworking.send(new RequestOpenPCPacket(Minecraft.getInstance().getUser().getProfileId()));
        });
    }
}
