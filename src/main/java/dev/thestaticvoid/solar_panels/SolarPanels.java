package dev.thestaticvoid.solar_panels;

import dev.thestaticvoid.solar_panels.block.ModBlocks;
import dev.thestaticvoid.solar_panels.block.entity.ModBlockEntities;
import dev.thestaticvoid.solar_panels.item.ModItems;
import dev.thestaticvoid.solar_panels.networking.ModMessages;
import dev.thestaticvoid.solar_panels.screen.ModScreens;
import dev.thestaticvoid.solar_panels.util.ResourceIdentifier;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolarPanels implements ModInitializer {
    public static final String MOD_ID = "solar_panels";
    public static final Logger LOGGER  = LoggerFactory.getLogger(MOD_ID);
    public static ResourceKey<CreativeModeTab> SOLAR_PANEL_GROUP = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceIdentifier("creative_tab"));

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

        initCreativeTab();
        ModMessages.initialize();
        ModBlocks.initialize();
        ModItems.initialize();
        ModBlockEntities.initialize();
        ModScreens.initialize();
    }

    private void initCreativeTab() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SOLAR_PANEL_GROUP, FabricItemGroup.builder()
                .icon(() -> new ItemStack(ModBlocks.TIER_1_SOLAR_PANEL))
                .title(Component.translatable("itemGroup.solar_panels.creative_tab"))
                .build());
    }

    public static void registerItemToCreativeTab(ItemLike item) {
        ItemGroupEvents.modifyEntriesEvent(SOLAR_PANEL_GROUP).register(entries -> entries.accept(item));
    }
}
