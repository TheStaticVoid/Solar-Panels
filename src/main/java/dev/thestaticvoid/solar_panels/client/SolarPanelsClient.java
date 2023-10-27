package dev.thestaticvoid.solar_panels.client;

import dev.thestaticvoid.solar_panels.screen.ModScreens;
import dev.thestaticvoid.solar_panels.screen.SolarPanelScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;

@Environment(EnvType.CLIENT)
public class SolarPanelsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModScreens.SOLAR_PANEL_SCREEN_HANDLER, SolarPanelScreen::new);
    }
}
