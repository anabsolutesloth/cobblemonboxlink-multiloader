package com.emperdog.boxlink;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class BoxLinkClient {
    public static KeyMapping openPCKey =
            new KeyMapping(BoxLinkCommon.OPEN_PC_KEY_NAME, GLFW.GLFW_KEY_BACKSLASH, "key.cobblemonboxlink.category");
}
