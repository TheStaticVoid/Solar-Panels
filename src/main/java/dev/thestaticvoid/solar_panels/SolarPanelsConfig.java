package dev.thestaticvoid.solar_panels;

import dev.thestaticvoid.solar_panels.util.EnglishTranslation;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

@Config(name = SolarPanelsConfig.NAME)
public class SolarPanelsConfig implements ConfigData {
    @ConfigEntry.Gui.Excluded
    public static final transient String NAME = SolarPanels.MOD_ID;

    @ConfigEntry.Gui.RequiresRestart
    @EnglishTranslation(value = "Tier 1 Generator Generation Rate")
    public int tier1GenerationRate = 2;
    @ConfigEntry.Gui.RequiresRestart
    @EnglishTranslation(value = "Tier 1 Generator Max Transfer Rate")
    public int tier1TransferRate = 512;
    @ConfigEntry.Gui.RequiresRestart
    @EnglishTranslation(value = "Tier 1 Generator Max Capacity")
    public int tier1Capacity = 8192;

    @ConfigEntry.Gui.RequiresRestart
    @EnglishTranslation(value = "Tier 2 Generator Generation Rate")
    public int tier2GenerationRate = 4;
    @ConfigEntry.Gui.RequiresRestart
    @EnglishTranslation(value = "Tier 2 Generator Max Transfer Rate")
    public int tier2TransferRate = 512;
    @ConfigEntry.Gui.RequiresRestart
    @EnglishTranslation(value = "Tier 2 Generator Max Capacity")
    public int tier2Capacity = 8192;

    @ConfigEntry.Gui.RequiresRestart
    @EnglishTranslation(value = "Tier 3 Generator Generation Rate")
    public int tier3GenerationRate = 8;
    @ConfigEntry.Gui.RequiresRestart
    @EnglishTranslation(value = "Tier 3 Generator Max Transfer Rate")
    public int tier3TransferRate = 512;
    @ConfigEntry.Gui.RequiresRestart
    @EnglishTranslation(value = "Tier 3 Generator Max Capacity")
    public int tier3Capacity = 8192;

    @ConfigEntry.Gui.RequiresRestart
    @EnglishTranslation(value = "Tier 4 Generator Generation Rate")
    public int tier4GenerationRate = 32;
    @ConfigEntry.Gui.RequiresRestart
    @EnglishTranslation(value = "Tier 4 Generator Max Transfer Rate")
    public int tier4TransferRate = 1024;
    @ConfigEntry.Gui.RequiresRestart
    @EnglishTranslation(value = "Tier 4 Generator Max Capacity")
    public int tier4Capacity = 16384;

    @ConfigEntry.Gui.RequiresRestart
    @EnglishTranslation(value = "Tier 5 Generator Generation Rate")
    public int tier5GenerationRate = 96;
    @ConfigEntry.Gui.RequiresRestart
    @EnglishTranslation(value = "Tier 5 Generator Max Transfer Rate")
    public int tier5TransferRate = 1024;
    @ConfigEntry.Gui.RequiresRestart
    @EnglishTranslation(value = "Tier 5 Generator Max Capacity")
    public int tier5Capacity = 16384;


    @ConfigEntry.Gui.Excluded
    private transient static boolean registered = false;
    public static synchronized SolarPanelsConfig getConfig() {
        if (!registered) {
            AutoConfig.register(SolarPanelsConfig.class, Toml4jConfigSerializer::new);
            registered = true;
        }

        return AutoConfig.getConfigHolder(SolarPanelsConfig.class).getConfig();
    }
}
