package dev.thestaticvoid.solar_panels.block.entity;

import dev.thestaticvoid.solar_panels.SolarPanels;
import dev.thestaticvoid.solar_panels.block.SolarPanelBlock;
import dev.thestaticvoid.solar_panels.networking.ModMessages;
import dev.thestaticvoid.solar_panels.screen.SolarPanelScreenHandler;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
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
    private int fillPercent;

    private final ContainerData containerData = new ContainerData() {
        @Override
        public int get(int index) {
            return fillPercent;
        }

        @Override
        public void set(int index, int value) {
            fillPercent = value;
        }

        @Override
        public int getCount() {
            return 1;
        }
    };

    public SolarPanelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SOLAR_BLOCK_ENTITY, pos, state);
        solarPanelBlock = (SolarPanelBlock) state.getBlock();
        shouldGenerate = false;
        generating = false;

        energyContainer = new SimpleEnergyStorage(solarPanelBlock.capacity, solarPanelBlock.maxTransfer, solarPanelBlock.maxTransfer) {
            @Override
            protected void onFinalCommit() {
                setChanged();

                if (!level.isClientSide) {
                    fillPercent = (int) ((getCurrentAmount() / (float)getCapacity()) * 100);

                    FriendlyByteBuf buf = PacketByteBufs.create();
                    buf.writeLong(getCurrentAmount());
                    buf.writeBlockPos(getBlockPos());

                    for (ServerPlayer player : PlayerLookup.tracking((ServerLevel) level, getBlockPos())) {
                        ServerPlayNetworking.send(player, ModMessages.ENERGY_AMOUNT_SYNC, buf);
                    }
                }
            }
        };
        fillPercent = 0;
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        compoundTag.putLong("amount", getCurrentAmount());
        super.saveAdditional(compoundTag);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        setStored(compoundTag.getLong("amount"));
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
                setChanged();
                this.generating = isGenerating;
                level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).setValue(SolarPanelBlock.ACTIVE, isGenerating));
            }
        }
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, SolarPanelBlockEntity blockEntity) {
        if (level != null && !level.isClientSide) {
            if (level.getGameTime() % 20L == 0L) {
                shouldGenerate = level.dimensionType().hasSkyLight() &&
                        level.canSeeSky(blockPos.above()) &&
                        level.isDay() &&
                        !level.isRaining() &&
                        !level.isThundering() &&
                        notFull();
            }

            generate();
        }
    }

    private boolean notFull() {
        return getCurrentAmount() != getCapacity();
    }

    public void setStored(long amount) {
        energyContainer.amount = Math.max(Math.min(amount, getCapacity()), 0);
        setChanged();
    }

    public void generate() {
        setIsGenerating(this.shouldGenerate);

        if (this.shouldGenerate) {
            try(Transaction transaction = Transaction.openOuter()) {
                energyContainer.insert(solarPanelBlock.generationRate, transaction);
                transaction.commit();
            } catch (Exception e) {
                SolarPanels.LOGGER.info("ERROR: " + e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void writeScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf) {
        CompoundTag nbt = new CompoundTag();
        nbt.putLong("amount", getCurrentAmount());
        nbt.putLong("capacity", getCapacity());
        nbt.putInt("rate", getGenerationRate());
        buf.writeNbt(nbt);
        buf.writeBlockPos(getBlockPos());
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(solarPanelBlock.getDescriptionId());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new SolarPanelScreenHandler(i, inventory, this.containerData);
    }

    public void openMenu(Player player) {
        player.openMenu(this);
    }
}