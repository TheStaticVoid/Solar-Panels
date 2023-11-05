package dev.thestaticvoid.solar_panels.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.thestaticvoid.solar_panels.util.ResourceIdentifier;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;
import java.util.Optional;

public class SolarPanelScreen extends AbstractContainerScreen<SolarPanelScreenHandler> {
    private static final ResourceIdentifier TEXTURE = new ResourceIdentifier("textures/gui/solar_panel.png");

    public SolarPanelScreen(SolarPanelScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();

        titleLabelX = (imageWidth - font.width(title)) / 2;
        inventoryLabelY = inventoryLabelY - 34;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, 132);

        renderEnergyBars(guiGraphics, x, y);
    }

    private void renderEnergyBars(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(TEXTURE, x + 19, y + 23, 0, 132, 138, 9);

        int fillAmount = (menu.energyAmount == menu.energyCapacity) ? 138 : (int)(Mth.clamp((menu.getFillPercentage() / (float)100) * 138, 0, 138));

        guiGraphics.blit(TEXTURE, x + 19, y + 23, 0, 141, fillAmount, 9);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderLabels(guiGraphics, mouseX, mouseY);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        renderEnergyComponentTooltips(guiGraphics, mouseX, mouseY, x, y);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private void renderEnergyComponentTooltips(GuiGraphics guiGraphics, int pMouseX, int pMouseY, int x, int y) {
        if (isMouseAboveArea(pMouseX, pMouseY, x, y, 18, 22, 139, 10)) {
            guiGraphics.renderTooltip(font, getTooltipText(menu.energyAmount, menu.energyCapacity),
                    Optional.empty(), pMouseX - x, pMouseY - y);
            renderTooltip(guiGraphics, pMouseX - x, pMouseY - y);
        }
    }

    private List<Component> getTooltipText(long energyAmount, long energyCapacity) {
        return List.of(Component.literal(energyAmount + " / " + energyCapacity + " EU"));
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return (pMouseX >= (x + offsetX) && pMouseX <= (x + offsetX) + width) && (pMouseY >= (y + offsetY) && pMouseY <= (y + offsetY) + height);
    }
}
