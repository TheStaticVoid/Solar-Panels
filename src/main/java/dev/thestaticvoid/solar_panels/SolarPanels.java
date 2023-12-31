package dev.thestaticvoid.solar_panels;

import dev.thestaticvoid.solar_panels.block.ModBlocks;
import dev.thestaticvoid.solar_panels.block.entity.ModBlockEntities;
import dev.thestaticvoid.solar_panels.item.ModItems;
import dev.thestaticvoid.solar_panels.networking.ModMessages;
import dev.thestaticvoid.solar_panels.screen.ModScreens;
import dev.thestaticvoid.solar_panels.util.ResourceIdentifier;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolarPanels implements ModInitializer {
    public static final String MOD_ID = "solar_panels";
    public static final Logger LOGGER  = LoggerFactory.getLogger(MOD_ID);
    public static CreativeModeTab SOLAR_PANEL_GROUP = FabricItemGroupBuilder.build(new ResourceLocation(MOD_ID, "creative_tab"),
            () -> new ItemStack(ModBlocks.TIER_1_SOLAR_PANEL));

    public static final ResourceIdentifier TIER_1_SOLAR_PANEL = new ResourceIdentifier("tier_1_solar_panel");
    public static final ResourceIdentifier TIER_2_SOLAR_PANEL = new ResourceIdentifier("tier_2_solar_panel");
    public static final ResourceIdentifier TIER_3_SOLAR_PANEL = new ResourceIdentifier("tier_3_solar_panel");
    public static final ResourceIdentifier TIER_4_SOLAR_PANEL = new ResourceIdentifier("tier_4_solar_panel");
    public static final ResourceIdentifier TIER_5_SOLAR_PANEL = new ResourceIdentifier("tier_5_solar_panel");
    public static final ResourceIdentifier SOLAR_CASING = new ResourceIdentifier("solar_casing");
    public static final ResourceIdentifier SOLAR_BATTERY = new ResourceIdentifier("solar_battery");
    public static final ResourceIdentifier SOLAR_VOLTAIC_CELL = new ResourceIdentifier("solar_voltaic_cell");

    @Override
    public void onInitialize() {
        LOGGER.debug("Initialized mod: " + MOD_ID);

        ModMessages.initialize();
        ModBlocks.initialize();
        ModItems.initialize();
        ModBlockEntities.initialize();
        ModScreens.initialize();
    }
}
