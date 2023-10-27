package dev.thestaticvoid.solar_panels.util;

import dev.thestaticvoid.solar_panels.SolarPanels;
import net.minecraft.resources.ResourceLocation;

/*
    I hate ResourceLocation mojang
    I hate ResourceLocation mojang
    I hate ResourceLocation mojang
    I hate ResourceLocation mojang
    I hate ResourceLocation mojang
    I hate ResourceLocation mojang
 */

public class ResourceIdentifier extends ResourceLocation {
    public ResourceIdentifier(String path) {
        super(SolarPanels.MOD_ID, path);
    }
}
