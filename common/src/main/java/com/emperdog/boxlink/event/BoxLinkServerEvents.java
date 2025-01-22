package com.emperdog.boxlink.event;

import com.emperdog.boxlink.BoxLinkCommon;
import com.emperdog.boxlink.platform.Services;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.UUID;

public class BoxLinkServerEvents {

    public static ArrayList<UUID> LOGGED_IN = new ArrayList<>();

    public static void onPlayerLoggedIn(Player player) {
        UUID playerUUID = player.getUUID();
        if(Services.PLATFORM.boxLinkBindRequiresItem()
                || LOGGED_IN.contains(playerUUID))
            return;

        player.sendSystemMessage(Component.translatable("cobblemonboxlink.message.box_link_not_required",
                        Component.keybind(BoxLinkCommon.OPEN_PC_KEY_NAME)
                                .withStyle(ChatFormatting.AQUA)
                                .withStyle(ChatFormatting.BOLD))
                .withStyle(ChatFormatting.YELLOW));

        LOGGED_IN.add(playerUUID);
    }
}
