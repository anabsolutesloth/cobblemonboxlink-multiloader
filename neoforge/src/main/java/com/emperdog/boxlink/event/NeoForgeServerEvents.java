package com.emperdog.boxlink.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class NeoForgeServerEvents {
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        BoxLinkServerEvents.onPlayerLoggedIn(event.getEntity());
    }
}
