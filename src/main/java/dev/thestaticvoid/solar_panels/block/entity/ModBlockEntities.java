package dev.thestaticvoid.solar_panels.block.entity;

import dev.thestaticvoid.solar_panels.SolarPanels;
import dev.thestaticvoid.solar_panels.block.ModBlocks;
import dev.thestaticvoid.solar_panels.block.SolarPanelBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static final BlockEntityType<SolarPanelBlockEntity> BASIC_SOLAR_PANEL_ENTITY =
            registerSolar("basic_solar_panel_entity", ModBlocks.BASIC_SOLAR_PANEL);

    public static void initialize() {
        SolarPanels.LOGGER.debug("Registering block entities for " + SolarPanels.MOD_ID);
    }

    public static BlockEntityType<SolarPanelBlockEntity> registerSolar(String id, SolarPanelBlock block) {
        return Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                new ResourceLocation(SolarPanels.MOD_ID, id),
                FabricBlockEntityTypeBuilder.create(SolarPanelBlockEntity::new, block).build()
        );
    }
}
