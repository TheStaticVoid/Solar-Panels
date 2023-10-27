package dev.thestaticvoid.solar_panels.screen.component;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;

import java.util.List;

public class EnergyComponentArea extends ComponentArea {
    private final long energyAmount;
    private final long energyCapacity;

    public EnergyComponentArea(int xMin, int yMin, long energyAmount, long energyCapacity) {
        this(xMin, yMin, energyAmount, energyCapacity, 137, 8);
    }

    public EnergyComponentArea(int xMin, int yMin, long energyAmount, long energyCapacity, int width, int height) {
        super (new Rect2i(xMin, yMin, width, height));
        this.energyAmount = energyAmount;
        this.energyCapacity = energyCapacity;
    }

    public List<Component> getTooltips() {
        return List.of(Component.literal(energyAmount + " / " + energyCapacity + " EU"));
    }

    @Override
    public void draw(PoseStack stack) {
        final int width = area.getWidth();
        int stored = (int)(width*(energyAmount/(float)energyCapacity));
        fill(stack, area.getX(), area.getY(), area.getX() + (area.getWidth() - stored), area.getY() + area.getHeight(), 0xc8ff0000);
    }
}
