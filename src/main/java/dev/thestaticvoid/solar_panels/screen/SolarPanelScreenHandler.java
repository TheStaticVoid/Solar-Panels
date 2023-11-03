package dev.thestaticvoid.solar_panels.screen;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SolarPanelScreenHandler extends AbstractContainerMenu {
    public long energyAmount;
    public long energyCapacity;
    public int energyRate;
    public BlockPos pos;
    private final ContainerData containerData;

    public SolarPanelScreenHandler(int syncId, Inventory inventory, FriendlyByteBuf buf ) {
        this(syncId, inventory, new SimpleContainerData(1));
        CompoundTag nbt = buf.readNbt();
        if (nbt != null) {
            this.energyAmount = nbt.getLong("amount");
            this.energyCapacity = nbt.getLong("capacity");
            this.energyRate = nbt.getInt("rate");
        }
        this.pos = buf.readBlockPos();
    }

    public SolarPanelScreenHandler(int syncId, Inventory inventory, ContainerData containerData) {
        super(ModScreens.SOLAR_PANEL_SCREEN_HANDLER, syncId);

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        this.energyAmount = 0;
        this.energyRate = 0;
        this.energyCapacity = 0;
        this.pos = BlockPos.ZERO;
        this.containerData = containerData;
        this.addDataSlots(containerData);
    }

    public int getFillPercentage() {
        return containerData.get(0);
    }

    public void setAmount(long amount) {
        this.energyAmount = amount;
    }

    private void addPlayerInventory(Inventory inventory) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, i + j * 9 + 9, 8 + j * 18, 50 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory inventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 108));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
