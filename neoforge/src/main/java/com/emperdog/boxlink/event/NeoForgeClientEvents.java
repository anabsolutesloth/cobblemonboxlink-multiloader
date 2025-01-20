package com.emperdog.boxlink.event;

import com.emperdog.boxlink.BoxLinkClient;
import com.emperdog.boxlink.BoxLinkCommon;
import com.emperdog.boxlink.network.RequestOpenPCPacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

public class NeoForgeClientEvents {

    public static KeyMapping openPCKey =
            new KeyMapping(BoxLinkCommon.OPEN_PC_KEY_NAME, GLFW.GLFW_KEY_BACKSLASH, "key.cobblemonboxlink.category");

    @SubscribeEvent
    public void onClientTick(final ClientTickEvent.Post event) {
        if(BoxLinkClient.openPCKey.consumeClick() && Minecraft.getInstance().isWindowActive())
            PacketDistributor.sendToServer(new RequestOpenPCPacket(Minecraft.getInstance().getUser().getProfileId()));
    }
}
