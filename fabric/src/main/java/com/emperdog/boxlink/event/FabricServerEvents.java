package com.emperdog.boxlink.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.world.entity.player.Player;

public class FabricServerEvents {
    public static void register() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if(!(entity instanceof Player player))
                return;
            BoxLinkServerEvents.onPlayerLoggedIn(player);
        });
    }
}
