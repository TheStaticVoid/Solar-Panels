package dev.thestaticvoid.solar_panels.block;

import net.minecraft.world.level.block.Block;

public class SolarPanelBlock extends Block {
    private boolean isGenerating = false;
    private float generationRate = 1.0f;

    public SolarPanelBlock(Properties properties) {
        super(properties);
    }
}
