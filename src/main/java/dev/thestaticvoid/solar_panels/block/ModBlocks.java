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
    public static final SolarPanelBlock BASIC_SOLAR_PANEL = new SolarPanelBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool(),
            1000000, 1000, 8);

    public static void initialize() {
        SolarPanels.LOGGER.debug("Registering blocks for " + SolarPanels.MOD_ID);

        registerBlock(new ResourceLocation(SolarPanels.MOD_ID, "basic_solar_panel"), BASIC_SOLAR_PANEL);
    }

    private static void registerBlock(ResourceLocation resourceLocation, Block block) {
        Registry.register(Registry.BLOCK, resourceLocation, block);
        Registry.register(Registry.ITEM, resourceLocation, new BlockItem(block, new FabricItemSettings().group(SolarPanels.SOLAR_PANEL_GROUP)));
    }
}
