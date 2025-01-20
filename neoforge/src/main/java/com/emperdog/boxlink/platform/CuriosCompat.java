package com.emperdog.boxlink.platform;

import com.emperdog.boxlink.BoxLinkCommon;
import com.emperdog.boxlink.platform.services.IAccessoryModHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.fml.ModList;
import top.theillusivec4.curios.api.CuriosApi;

public class CuriosCompat implements IAccessoryModHelper {
    @Override
    public boolean loaded() {
        return ModList.get().isLoaded("curios");
    }

    @Override
    public boolean hasItemAsAccessory(Player player, Item item) {
        if(CuriosApi.getCuriosInventory(player).isEmpty())
            return false;
        return !CuriosApi.getCuriosInventory(player).get().findCurios(BoxLinkCommon.BOX_LINK_ITEM).isEmpty();
    }
}
