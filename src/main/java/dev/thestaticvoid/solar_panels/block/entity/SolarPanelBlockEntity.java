package dev.thestaticvoid.solar_panels.block.entity;

import dev.thestaticvoid.solar_panels.SolarPanels;
import dev.thestaticvoid.solar_panels.block.SolarPanelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class SolarPanelBlockEntity extends BlockEntity implements BlockEntityTicker<SolarPanelBlockEntity> {
    private SimpleEnergyStorage energyStorage;
    private boolean canGenerate = false;
    private SolarPanelBlock block;

    public SolarPanelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BASIC_SOLAR_PANEL_ENTITY, pos, state);

        block = (SolarPanelBlock)state.getBlock();
        energyStorage = new SimpleEnergyStorage(block.capacity, block.transferSpeed, block.transferSpeed);
    }

    public long getStoredAmount() {
        return this.energyStorage.amount;
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.putLong("amount", energyStorage.amount);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        energyStorage.amount = compoundTag.getLong("amount");
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state, SolarPanelBlockEntity be) {
        if (!level.isClientSide) {
            if (level.getGameTime() % 20L == 0L) {
                if (level.dimensionType().hasSkyLight() && level.canSeeSky(pos)) {
                    canGenerate = true;
                    level.getBlockState(pos).setValue(SolarPanelBlock.ACTIVE, true);
                } else {
                    level.getBlockState(pos).setValue(SolarPanelBlock.ACTIVE, false);
                }
            }

            if (canGenerate) {
                generatePower();
            }
        }
    }

    private void generatePower() {
        energyStorage.amount = Mth.clamp(energyStorage.amount + block.generationRate, 0, block.capacity);
    }
}
