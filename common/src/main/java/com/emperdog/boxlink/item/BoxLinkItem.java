package com.emperdog.boxlink.item;

import com.emperdog.boxlink.BoxLinkCommon;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BoxLinkItem extends Item {
    public BoxLinkItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        ItemStack itemstack = player.getItemInHand(usedHand);
        if(!level.isClientSide) {
            if(player instanceof ServerPlayer serverPlayer) {
                BoxLinkCommon.openPCStorage(serverPlayer);

                return InteractionResultHolder.success(itemstack);
            }
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.cobblemonboxlink.box_link.desc").withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.translatable("item.cobblemonboxlink.box_link.desc2",
                        Component.keybind("key.cobblemonboxlink.open_pc.desc"))
                .withStyle(ChatFormatting.YELLOW));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
