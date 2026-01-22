package com.emperdog.boxlink.platform;

import com.emperdog.boxlink.platform.services.IAccessoryModHelper;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class TrinketsCompat implements IAccessoryModHelper {

    public static final String MOD_ID = "trinkets";

    @Override
    public boolean loaded() {
        return FabricLoader.getInstance().isModLoaded(MOD_ID);
    }

    @Override
    public boolean hasItemAsAccessory(Player player, Item item) {
        if(TrinketsApi.getTrinketComponent(player).isEmpty())
            return false;
        return TrinketsApi.getTrinketComponent(player).get().isEquipped(item);
    }
}
