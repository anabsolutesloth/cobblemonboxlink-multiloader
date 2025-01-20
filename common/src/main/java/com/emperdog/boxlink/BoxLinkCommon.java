package com.emperdog.boxlink;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.CobblemonSounds;
import com.cobblemon.mod.common.api.storage.pc.PCStore;
import com.cobblemon.mod.common.api.storage.pc.link.PCLink;
import com.cobblemon.mod.common.api.storage.pc.link.PCLinkManager;
import com.cobblemon.mod.common.net.messages.client.storage.pc.OpenPCPacket;
import com.cobblemon.mod.common.util.PlayerExtensionsKt;
import com.emperdog.boxlink.item.BoxLinkItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class BoxLinkCommon {

    public static final String MOD_ID = "cobblemonboxlink";
    public static final String MOD_NAME = "Cobblemon Box Link";

    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static final String OPEN_PC_KEY_NAME = "key.cobblemonboxlink.open_pc.desc";

    public static final Item BOX_LINK_ITEM = new BoxLinkItem(new Item.Properties());

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void initialize() {

    }

    // Function to open the Player's PC Storage and allow them to modify it.
    public static void openPCStorage(ServerPlayer player) {
        if(PlayerExtensionsKt.isInBattle(player)) {
            player.sendSystemMessage(Component.translatable("cobblemon.pc.inbattle").withStyle(ChatFormatting.RED));
            return;
        }

        PCStore pc = Cobblemon.INSTANCE.getStorage().getPC(player);
        PCLinkManager.INSTANCE.addLink(new PCLink(pc, player.getUUID()));
        player.level().playSound(null, player.blockPosition(), CobblemonSounds.PC_ON, SoundSource.NEUTRAL, 0.5f, 1.0f);
        new OpenPCPacket(pc.getUuid()).sendToPlayer(player);
    }
}