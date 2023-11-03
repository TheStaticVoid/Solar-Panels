package dev.thestaticvoid.solar_panels.block;

import dev.thestaticvoid.solar_panels.SolarPanels;
import dev.thestaticvoid.solar_panels.item.ModItems;
import dev.thestaticvoid.solar_panels.util.ResourceIdentifier;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public class ModBlocks {
    public static final SolarPanelBlock TIER_1_SOLAR_PANEL = createSolarPanelBlock(1, 512, 8192);
    public static final SolarPanelBlock TIER_2_SOLAR_PANEL = createSolarPanelBlock(4, 512, 8192);

    public static void initialize() {
        SolarPanels.LOGGER.debug("Registering blocks for " + SolarPanels.MOD_ID);

        registerBlock(SolarPanels.TIER_1_SOLAR_PANEL, TIER_1_SOLAR_PANEL);
        registerBlock(SolarPanels.TIER_2_SOLAR_PANEL, TIER_2_SOLAR_PANEL);
    }

    private static void registerBlock(ResourceIdentifier identifier, Block block) {
        Registry.register(Registry.BLOCK, identifier, block);
        ModItems.registerItem(identifier, block);
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
