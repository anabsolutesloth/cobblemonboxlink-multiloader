package com.emperdog.boxlink;

import com.emperdog.boxlink.event.BoxLinkServerEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class BoxLinkFabricMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        FabricBoxLinkConfig.load();

        Registry.register(BuiltInRegistries.ITEM,
                ResourceLocation.fromNamespaceAndPath(BoxLinkCommon.MOD_ID, "box_link"),
                BoxLinkCommon.BOX_LINK_ITEM
        );
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if(!(entity instanceof Player player))
                return;
            BoxLinkServerEvents.onPlayerLoggedIn(player);
        });


        //BoxLinkCommon.initialize();
    }
}
