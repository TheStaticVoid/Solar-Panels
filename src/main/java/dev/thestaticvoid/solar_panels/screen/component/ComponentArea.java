package dev.thestaticvoid.solar_panels.screen.component;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.Rect2i;

public abstract class ComponentArea extends GuiComponent {
    protected final Rect2i area;

    protected ComponentArea(Rect2i area) {
        this.area = area;
    }

    public abstract void draw(PoseStack stack);
}
