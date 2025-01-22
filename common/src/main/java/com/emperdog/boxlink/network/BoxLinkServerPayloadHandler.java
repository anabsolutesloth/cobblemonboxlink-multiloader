package com.emperdog.boxlink.network;

import com.emperdog.boxlink.BoxLinkCommon;
import com.emperdog.boxlink.platform.Services;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.UUID;

import static java.util.Objects.isNull;

public class BoxLinkServerPayloadHandler {

    private static final HashMap<UUID, Integer> storedLinkIndexes = new HashMap<>();

    public static void handlePCPacket(final RequestOpenPCPacket data, ServerPlayer player) {
        if(player.getUUID().equals(data.uuid())) {
            Inventory playerInventory = player.getInventory();
            // skip search logic and immediately open PC if item is not required.
            if(Services.CONFIG.boxLinkBindRequiresItem()) {
                // check stored inventory index
                if (!isNull(storedLinkIndexes.get(player.getUUID()))
                        && playerInventory.getItem(storedLinkIndexes.get(player.getUUID())).getItem().equals(BoxLinkCommon.BOX_LINK_ITEM))
                    BoxLinkCommon.openPCStorage(player);
                    // check Curio slots
                else if (Services.ACCESSORY_MOD.loaded()
                        && Services.ACCESSORY_MOD.hasItemAsAccessory(player, BoxLinkCommon.BOX_LINK_ITEM)) {
                    BoxLinkCommon.openPCStorage(player);
                } else {
                    // otherwise search for Box Link
                    for (int index = 0; index < 36; index++) {
                        ItemStack item = playerInventory.getItem(index);
                        if (item.getItem().equals(BoxLinkCommon.BOX_LINK_ITEM)) {
                            storedLinkIndexes.put(player.getUUID(), index);
                            BoxLinkCommon.openPCStorage(player);
                            break;
                        }
                        if (index == 35)
                            player.displayClientMessage(
                                    Component.translatable("cobblemonboxlink.key_open_pc.no_box_link").withStyle(ChatFormatting.RED),
                                    true
                            );
                    }
                }

            } else BoxLinkCommon.openPCStorage(player);
        } else {
            throw new IllegalArgumentException("Player UUID "+ player.getUUID() +" does not match provided UUID "+ data.uuid());
        }
    }
}
