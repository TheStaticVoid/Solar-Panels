package dev.thestaticvoid.solar_panels.item;

import dev.thestaticvoid.solar_panels.SolarPanels;
import dev.thestaticvoid.solar_panels.util.ResourceIdentifier;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModItems {

    public static void initialize() {
        SolarPanels.LOGGER.debug("Registering items for " + SolarPanels.MOD_ID);

        registerItem(SolarPanels.SOLAR_VOLTAIC_CELL);
        registerItem(SolarPanels.SOLAR_BATTERY);
    }

    public static void registerSolarPanelItem(ResourceIdentifier identifier, Block block) {
        Registry.register(Registry.ITEM, identifier, new SolarPanelBlockItem(block, new FabricItemSettings().group(SolarPanels.SOLAR_PANEL_GROUP)));
    }

    public static void registerBlockItem(ResourceIdentifier identifier, Block block) {
        Registry.register(Registry.ITEM, identifier, new BlockItem(block, new FabricItemSettings().group(SolarPanels.SOLAR_PANEL_GROUP)));
    }

    public static void registerItem(ResourceIdentifier identifier) {
        Registry.register(Registry.ITEM, identifier, new Item(new FabricItemSettings().group(SolarPanels.SOLAR_PANEL_GROUP)));
    }
}
