package com.emperdog.boxlink.platform;

import com.emperdog.boxlink.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    /*
    @Override
    public boolean boxLinkBindRequiresItem() {
        return FabricBoxLinkConfig.boxLinkBindRequiresItem;
    }
     */
}
