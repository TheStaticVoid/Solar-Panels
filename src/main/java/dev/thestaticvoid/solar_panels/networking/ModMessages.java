package dev.thestaticvoid.solar_panels.networking;

import dev.thestaticvoid.solar_panels.SolarPanels;
import dev.thestaticvoid.solar_panels.util.ResourceIdentifier;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ModMessages {
    public static final ResourceIdentifier ENERGY_AMOUNT_SYNC = new ResourceIdentifier("energy_amount_sync");
    public static void initialize() {
        SolarPanels.LOGGER.debug("Initializing packets for " + SolarPanels.MOD_ID);

        registerC2SPackets();
        registerS2CPackets();
    }

    private static void registerC2SPackets() {

    }

    private static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(ENERGY_AMOUNT_SYNC, EnergyAmountSyncS2CPacket::receive);
    }
}
