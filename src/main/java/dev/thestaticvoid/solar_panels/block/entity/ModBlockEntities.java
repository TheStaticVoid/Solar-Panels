package dev.thestaticvoid.solar_panels.block.entity;

import dev.thestaticvoid.solar_panels.SolarPanels;
import dev.thestaticvoid.solar_panels.block.ModBlocks;
import dev.thestaticvoid.solar_panels.util.ResourceIdentifier;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import team.reborn.energy.api.EnergyStorage;

public class ModBlockEntities {
    public static final BlockEntityType<SolarPanelBlockEntity> SOLAR_BLOCK_ENTITY =
            registerSolar(new ResourceIdentifier("solar_block_entity"));

    public static void initialize() {
        SolarPanels.LOGGER.debug("Registering block entities for " + SolarPanels.MOD_ID);

        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyContainer, SOLAR_BLOCK_ENTITY);
    }

    public static BlockEntityType<SolarPanelBlockEntity> registerSolar(ResourceIdentifier resourceIdentifier) {
        return Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                resourceIdentifier,
                FabricBlockEntityTypeBuilder
                        .create(SolarPanelBlockEntity::new,
                                ModBlocks.TIER_1_SOLAR_PANEL,
                                ModBlocks.TIER_2_SOLAR_PANEL,
                                ModBlocks.TIER_3_SOLAR_PANEL,
                                ModBlocks.TIER_4_SOLAR_PANEL,
                                ModBlocks.TIER_5_SOLAR_PANEL
                        ).build()
        );
    }
}
