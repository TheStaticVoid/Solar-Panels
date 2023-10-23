package dev.thestaticvoid.solar_panels.block.entity;

import dev.thestaticvoid.solar_panels.SolarPanels;
import dev.thestaticvoid.solar_panels.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static final BlockEntityType<SolarPanelBlockEntity> BASIC_SOLAR_PANEL_ENTITY = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new ResourceLocation(SolarPanels.MOD_ID, "basic_solar_panel_entity"),
            FabricBlockEntityTypeBuilder.create(SolarPanelBlockEntity::new, ModBlocks.BASIC_SOLAR_PANEL).build());

    public static void initialize() {
        SolarPanels.LOGGER.debug("Registering block entities for " + SolarPanels.MOD_ID);

    }
}
