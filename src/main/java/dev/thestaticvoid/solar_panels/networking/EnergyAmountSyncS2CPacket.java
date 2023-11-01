package dev.thestaticvoid.solar_panels.networking;

import dev.thestaticvoid.solar_panels.block.entity.SolarPanelBlockEntity;
import dev.thestaticvoid.solar_panels.screen.SolarPanelScreenHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class EnergyAmountSyncS2CPacket {
    public static void receive(Minecraft client, ClientPacketListener handler,
                               FriendlyByteBuf buf, PacketSender packetSender) {
        long energy = buf.readLong();
        BlockPos pos = buf.readBlockPos();

        if (client.level != null && client.level.getBlockEntity(pos) instanceof SolarPanelBlockEntity blockEntity) {
            blockEntity.setStored(energy);

            if (client.player.containerMenu instanceof SolarPanelScreenHandler screenHandler && screenHandler.pos.equals(pos)) {
                screenHandler.setAmount(energy);
            }
        }
    }
}
