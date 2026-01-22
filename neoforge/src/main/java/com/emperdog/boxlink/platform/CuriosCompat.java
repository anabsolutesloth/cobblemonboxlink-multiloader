package com.emperdog.boxlink.platform;

import com.emperdog.boxlink.platform.services.IAccessoryModHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.fml.ModList;
import top.theillusivec4.curios.api.CuriosApi;

public class CuriosCompat implements IAccessoryModHelper {

    public static final String MOD_ID = "curios";

    @Override
    public boolean loaded() {
        return ModList.get().isLoaded(MOD_ID);
    }

    @Override
    public boolean hasItemAsAccessory(Player player, Item item) {
        if(CuriosApi.getCuriosInventory(player).isEmpty())
            return false;
        return !CuriosApi.getCuriosInventory(player).get().findCurios(item).isEmpty();
    }
}
