package com.emperdog.boxlink.platform;

import com.emperdog.boxlink.platform.services.IAccessoryModHelper;
import io.wispforest.accessories.api.AccessoriesCapability;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.Objects;

public class AccessoriesCompat implements IAccessoryModHelper {

    public static final String MOD_ID = "accessories";

    public static boolean MOD_LOADED = Services.PLATFORM.isModLoaded(MOD_ID);

    @Override
    public boolean loaded() {
        return MOD_LOADED;
    }

    @Override
    public boolean hasItemAsAccessory(Player player, Item item) {
        return Objects.requireNonNull(AccessoriesCapability.get(player)).isEquipped(item);
    }
}
