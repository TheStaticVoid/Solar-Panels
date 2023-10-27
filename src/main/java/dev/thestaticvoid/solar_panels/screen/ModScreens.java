package dev.thestaticvoid.solar_panels.screen;

import dev.thestaticvoid.solar_panels.SolarPanels;
import dev.thestaticvoid.solar_panels.util.ResourceIdentifier;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.world.inventory.MenuType;

public class ModScreens {
    public static final MenuType<SolarPanelScreenHandler> SOLAR_PANEL_SCREEN_HANDLER = ScreenHandlerRegistry.registerExtended(
            new ResourceIdentifier("solar_panel_screen"), SolarPanelScreenHandler::new);

    public static void initialize() {
        SolarPanels.LOGGER.debug("Initializing screens for: " + SolarPanels.MOD_ID);
    }
}
