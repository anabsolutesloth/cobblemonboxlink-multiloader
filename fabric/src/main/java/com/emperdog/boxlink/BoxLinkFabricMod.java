package com.emperdog.boxlink;

import com.cobblemon.mod.common.item.group.CobblemonItemGroups;
import com.emperdog.boxlink.event.FabricClientEvents;
import com.emperdog.boxlink.event.FabricServerEvents;
import com.emperdog.boxlink.network.FabricPacketHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class BoxLinkFabricMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        //FabricBoxLinkConfig.load();

        Registry.register(BuiltInRegistries.ITEM,
                ResourceLocation.fromNamespaceAndPath(BoxLinkCommon.MOD_ID, "box_link"),
                BoxLinkCommon.BOX_LINK_ITEM
        );
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        FabricClientEvents.register();

        FabricServerEvents.register();

        FabricPacketHandler.registerPackets();

        ItemGroupEvents.modifyEntriesEvent(CobblemonItemGroups.getUTILITY_ITEMS_KEY())
                .register(entries -> entries.accept(BoxLinkCommon.BOX_LINK_ITEM));

        //BoxLinkCommon.initialize();
    }
}
