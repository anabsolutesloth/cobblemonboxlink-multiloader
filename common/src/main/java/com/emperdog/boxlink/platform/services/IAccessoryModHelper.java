package com.emperdog.boxlink.platform.services;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public interface IAccessoryModHelper {
    boolean loaded();

    boolean hasItemAsAccessory(Player player, Item item);
}
