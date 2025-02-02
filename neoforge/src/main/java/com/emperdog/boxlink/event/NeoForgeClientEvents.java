package com.emperdog.boxlink.event;

import com.emperdog.boxlink.BoxLinkClient;
import com.emperdog.boxlink.network.RequestOpenPCPacket;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class NeoForgeClientEvents {

    @SubscribeEvent
    public void onClientTick(final ClientTickEvent.Post event) {
        if(BoxLinkClient.openPCKey.consumeClick() && Minecraft.getInstance().isWindowActive())
            PacketDistributor.sendToServer(new RequestOpenPCPacket(Minecraft.getInstance().getUser().getProfileId()));
    }
}
