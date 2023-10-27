package dev.thestaticvoid.solar_panels.screen;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SolarPanelScreenHandler extends AbstractContainerMenu {
    private BlockPos pos;
    public SolarPanelScreenHandler(int syncId, Inventory inventory, FriendlyByteBuf buf ) {
        this(syncId, inventory);
        pos = buf.readBlockPos();
    }

    public SolarPanelScreenHandler(int syncId, Inventory inventory) {
        super(ModScreens.SOLAR_PANEL_SCREEN_HANDLER, syncId);

        int m;
        int l;
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(inventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(inventory, m, 8 + m * 18, 142));
        }
        pos = BlockPos.ZERO;
    }

    public BlockPos getPos() {
        return this.pos;
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
