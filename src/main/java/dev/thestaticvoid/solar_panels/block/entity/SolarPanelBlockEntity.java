package dev.thestaticvoid.solar_panels.block.entity;

import dev.thestaticvoid.solar_panels.block.SolarPanelBlock;
import dev.thestaticvoid.solar_panels.screen.SolarPanelScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class SolarPanelBlockEntity extends BlockEntity implements BlockEntityTicker<SolarPanelBlockEntity>, ExtendedScreenHandlerFactory {
    public final SimpleEnergyStorage energyContainer;
    private final SolarPanelBlock solarPanelBlock;
    private boolean shouldGenerate;
    private boolean generating;

    public SolarPanelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BASIC_SOLAR_PANEL_ENTITY, pos, state);
        solarPanelBlock = (SolarPanelBlock) state.getBlock();
        shouldGenerate = false;
        generating = false;

        energyContainer = new SimpleEnergyStorage(solarPanelBlock.capacity, solarPanelBlock.maxTransfer, solarPanelBlock.maxTransfer) {
            @Override
            protected void onFinalCommit() {
                setChanged();
            }
        };
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        compoundTag.putLong("amount", getCurrentAmount());
        super.saveAdditional(compoundTag);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        energyContainer.amount = compoundTag.getLong("amount");
    }

    public long getCurrentAmount() {
        return energyContainer.amount;
    }

    public long getCapacity() {
        return solarPanelBlock.capacity;
    }

    public int getGenerationRate() {
        return solarPanelBlock.generationRate;
    }

    public boolean isGenerating() {
        return generating;
    }

    private void setIsGenerating(boolean isGenerating) {
        if (level != null) {
            if (isGenerating != isGenerating()) {
                level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).setValue(SolarPanelBlock.ACTIVE, isGenerating));
            }
        }

        this.generating = isGenerating;
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, SolarPanelBlockEntity blockEntity) {
        if (level != null && !level.isClientSide) {
            if (level.getGameTime() % 20L == 0L) {
                shouldGenerate = level.dimensionType().hasSkyLight() &&
                        level.canSeeSky(blockPos.above()) &&
                        level.isDay() &&
                        !level.isRaining() &&
                        !level.isThundering();
            }

            generate();
        }
    }

    public void setStored(long amount) {
        energyContainer.amount = Math.min(amount, getCapacity());
        setChanged();
    }

    public void generate() {
        setIsGenerating(this.shouldGenerate);

        setStored(getCurrentAmount() + solarPanelBlock.generationRate);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf) {
        CompoundTag nbt = new CompoundTag();
        nbt.putLong("amount", getCurrentAmount());
        nbt.putLong("capacity", getCapacity());
        nbt.putInt("rate", getGenerationRate());
        buf.writeNbt(nbt);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(solarPanelBlock.getDescriptionId());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new SolarPanelScreenHandler(i, inventory);
    }

    public void openMenu(Player player) {
        player.openMenu(this);
    }
}