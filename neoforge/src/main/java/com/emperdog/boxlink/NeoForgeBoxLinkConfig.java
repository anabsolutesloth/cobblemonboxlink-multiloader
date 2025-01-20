package com.emperdog.boxlink;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeBoxLinkConfig implements BoxLinkConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue BOX_LINK_BIND_REQUIRES_ITEM = BUILDER
            .comment("Whether the 'Open PC Storage' Keybind requires having a Box Link item.")
            .comment("If false, it is effectively an innate ability.")
            .define("boxLinkBindRequiresItem", true);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean boxLinkBindRequiresItem;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent.Loading event) {
        assignConfig();
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent.Reloading event) {
        assignConfig();
    }

    private static void assignConfig() {
        boxLinkBindRequiresItem = BOX_LINK_BIND_REQUIRES_ITEM.get();
    }

    @Override
    public boolean boxLinkBindRequiresItem() {
        return boxLinkBindRequiresItem;
    }
}
