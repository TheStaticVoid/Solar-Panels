package dev.thestaticvoid.solar_panels.item;

import dev.thestaticvoid.solar_panels.block.SolarPanelBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SolarPanelBlockItem extends BlockItem {
    SolarPanelBlock solarBlock;

    public SolarPanelBlockItem(Block block, Properties properties) {
        super(block, properties);
        this.solarBlock = (SolarPanelBlock) block;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);

        list.add(Component.translatable("message.solar_panels.generation_rate").append(solarBlock.generationRate + " EU/t").withStyle(ChatFormatting.AQUA));
        list.add(Component.translatable("message.solar_panels.capacity").append(solarBlock.capacity + " EU").withStyle(ChatFormatting.GREEN));
        list.add(Component.translatable("message.solar_panels.max_transfer").append(solarBlock.maxTransfer + " EU/t").withStyle(ChatFormatting.YELLOW));
    }
}
