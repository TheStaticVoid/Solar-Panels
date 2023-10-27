package dev.thestaticvoid.solar_panels.block;

import dev.thestaticvoid.solar_panels.SolarPanels;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class ModBlocks {
    public static final SolarPanelBlock BASIC_SOLAR_PANEL = createSolarPanelBlock(16, 1024, 16384);

    public static void initialize() {
        SolarPanels.LOGGER.debug("Registering blocks for " + SolarPanels.MOD_ID);

        registerBlock(SolarPanels.BASIC_SOLAR_PANEL, BASIC_SOLAR_PANEL);
    }

    private static void registerBlock(ResourceLocation resourceLocation, Block block) {
        Registry.register(Registry.BLOCK, resourceLocation, block);
        Registry.register(Registry.ITEM, resourceLocation, new BlockItem(block, new FabricItemSettings().group(SolarPanels.SOLAR_PANEL_GROUP)));
    }

    private static SolarPanelBlock createSolarPanelBlock(int generationRate, int maxTransfer, int capacity) {
        return new SolarPanelBlock(
                FabricBlockSettings.of(Material.METAL).strength(4.0F).requiresTool(),
                generationRate,
                maxTransfer,
                capacity
        );
    }
}
