package dev.thestaticvoid.solar_panels;

import dev.thestaticvoid.solar_panels.block.ModBlocks;
import dev.thestaticvoid.solar_panels.block.entity.ModBlockEntities;
import dev.thestaticvoid.solar_panels.item.ModItems;
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
            () -> new ItemStack(ModBlocks.BASIC_SOLAR_PANEL));
    @Override
    public void onInitialize() {
        LOGGER.debug("Initialized mod: " + MOD_ID);

        ModBlocks.initialize();
        ModItems.initialize();
        ModBlockEntities.initialize();
    }
}
