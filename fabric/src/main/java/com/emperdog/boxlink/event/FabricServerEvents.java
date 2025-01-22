package com.emperdog.boxlink.event;

import com.emperdog.boxlink.FabricBoxLinkConfig;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.world.entity.player.Player;

public class FabricServerEvents {
    public static void register() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if(!(entity instanceof Player player))
                return;
            BoxLinkServerEvents.onPlayerLoggedIn(player);
        });

        ServerLifecycleEvents.START_DATA_PACK_RELOAD.register((server, resourceManager) -> {
            FabricBoxLinkConfig.load();
        });

        ServerWorldEvents.LOAD.register((server, world) -> {
            FabricBoxLinkConfig.load();
        });
    }
}
