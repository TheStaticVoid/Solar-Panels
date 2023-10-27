package dev.thestaticvoid.solar_panels.block.entity;

import dev.thestaticvoid.solar_panels.SolarPanels;
import dev.thestaticvoid.solar_panels.block.ModBlocks;
import dev.thestaticvoid.solar_panels.block.SolarPanelBlock;
import dev.thestaticvoid.solar_panels.util.ResourceIdentifier;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import team.reborn.energy.api.EnergyStorage;

public class ModBlockEntities {
    public static final BlockEntityType<SolarPanelBlockEntity> BASIC_SOLAR_PANEL_ENTITY =
            registerSolar(SolarPanels.BASIC_SOLAR_PANEL, ModBlocks.BASIC_SOLAR_PANEL);

    public static void initialize() {
        SolarPanels.LOGGER.debug("Registering block entities for " + SolarPanels.MOD_ID);

        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyContainer, BASIC_SOLAR_PANEL_ENTITY);
    }

    public static BlockEntityType<SolarPanelBlockEntity> registerSolar(ResourceIdentifier resourceIdentifier, SolarPanelBlock block) {
        return Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                resourceIdentifier,
                FabricBlockEntityTypeBuilder.create(SolarPanelBlockEntity::new, block).build()
        );
    }
}
