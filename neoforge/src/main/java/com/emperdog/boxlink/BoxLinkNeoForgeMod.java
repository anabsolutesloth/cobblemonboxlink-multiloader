package com.emperdog.boxlink;


import com.cobblemon.mod.common.item.group.CobblemonItemGroups;
import com.emperdog.boxlink.event.NeoForgeClientEvents;
import com.emperdog.boxlink.event.NeoForgeServerEvents;
import com.emperdog.boxlink.network.NeoForgePacketHandler;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.emperdog.boxlink.event.NeoForgeClientEvents.openPCKey;

@Mod(BoxLinkCommon.MOD_ID)
public class BoxLinkNeoForgeMod {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BoxLinkCommon.MOD_ID);

    public static final DeferredItem<Item> BOX_LINK = ITEMS.register("box_link",
            () -> BoxLinkCommon.BOX_LINK_ITEM);

    public BoxLinkNeoForgeMod(IEventBus eventBus, ModContainer modContainer) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        ITEMS.register(eventBus);

        eventBus.addListener(NeoForgePacketHandler::registerPayloadHandlers);

        eventBus.addListener(this::commonSetup);

        eventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.SERVER, NeoForgeBoxLinkConfig.SPEC);

        //BoxLinkCommon.initialize();
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CobblemonItemGroups.getUTILITY_ITEMS_KEY())
            event.accept(BoxLinkCommon.BOX_LINK_ITEM);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        NeoForge.EVENT_BUS.register(new NeoForgeServerEvents());
    }

    @EventBusSubscriber(modid = BoxLinkCommon.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class ClientProxy {
        @SubscribeEvent
        public static void registerKeybinds(final RegisterKeyMappingsEvent event) {
            event.register(openPCKey);
        }

        @SubscribeEvent
        public static void setupClient(FMLClientSetupEvent event) {
            NeoForge.EVENT_BUS.register(new NeoForgeClientEvents());
        }
    }
}