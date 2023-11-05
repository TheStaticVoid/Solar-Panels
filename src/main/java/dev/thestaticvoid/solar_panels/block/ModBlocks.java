package dev.thestaticvoid.solar_panels.block;

import dev.thestaticvoid.solar_panels.SolarPanels;
import dev.thestaticvoid.solar_panels.SolarPanelsConfig;
import dev.thestaticvoid.solar_panels.item.ModItems;
import dev.thestaticvoid.solar_panels.util.ResourceIdentifier;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

public class ModBlocks {
    private static final SolarPanelsConfig config = SolarPanelsConfig.getConfig();
    public static final SolarPanelBlock TIER_1_SOLAR_PANEL = createSolarPanelBlock(
            config.tier1GenerationRate,
            config.tier1TransferRate,
            config.tier1Capacity
    );
    public static final SolarPanelBlock TIER_2_SOLAR_PANEL = createSolarPanelBlock(
            config.tier2GenerationRate,
            config.tier2TransferRate,
            config.tier2Capacity
    );
    public static final SolarPanelBlock TIER_3_SOLAR_PANEL = createSolarPanelBlock(
            config.tier3GenerationRate,
            config.tier3TransferRate,
            config.tier3Capacity
    );
    public static final SolarPanelBlock TIER_4_SOLAR_PANEL = createSolarPanelBlock(
            config.tier4GenerationRate,
            config.tier4TransferRate,
            config.tier3Capacity
    );
    public static final SolarPanelBlock TIER_5_SOLAR_PANEL = createSolarPanelBlock(
            config.tier5GenerationRate,
            config.tier5TransferRate,
            config.tier5Capacity
    );

    public static final Block SOLAR_CASING = new Block(FabricBlockSettings.create().sound(SoundType.METAL).strength(4.0F).requiresCorrectToolForDrops());

    public static void initialize() {
        SolarPanels.LOGGER.debug("Registering blocks for " + SolarPanels.MOD_ID);

        registerSolarPanelBlock(SolarPanels.TIER_1_SOLAR_PANEL, TIER_1_SOLAR_PANEL);
        registerSolarPanelBlock(SolarPanels.TIER_2_SOLAR_PANEL, TIER_2_SOLAR_PANEL);
        registerSolarPanelBlock(SolarPanels.TIER_3_SOLAR_PANEL, TIER_3_SOLAR_PANEL);
        registerSolarPanelBlock(SolarPanels.TIER_4_SOLAR_PANEL, TIER_4_SOLAR_PANEL);
        registerSolarPanelBlock(SolarPanels.TIER_5_SOLAR_PANEL, TIER_5_SOLAR_PANEL);

        registerBlock(SolarPanels.SOLAR_CASING, SOLAR_CASING);
    }

    private static void registerSolarPanelBlock(ResourceIdentifier identifier, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, identifier, block);
        ModItems.registerSolarPanelItem(identifier, block);
    }

    private static void registerBlock(ResourceIdentifier identifier, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, identifier, block);
        ModItems.registerBlockItem(identifier, block);
    }

    private static SolarPanelBlock createSolarPanelBlock(int generationRate, int maxTransfer, int capacity) {
        return new SolarPanelBlock(
                FabricBlockSettings.create().sound(SoundType.METAL).strength(4.0F).requiresCorrectToolForDrops(),
                generationRate,
                maxTransfer,
                capacity
        );
    }
}
