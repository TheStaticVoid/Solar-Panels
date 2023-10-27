package dev.thestaticvoid.solar_panels.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.thestaticvoid.solar_panels.screen.component.EnergyComponentArea;
import dev.thestaticvoid.solar_panels.util.ResourceIdentifier;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import java.util.Optional;

public class SolarPanelScreen extends AbstractContainerScreen<SolarPanelScreenHandler> {
    private static final ResourceIdentifier TEXTURE = new ResourceIdentifier("textures/gui/solar_panel.png");
    private EnergyComponentArea energyComponent;

    public SolarPanelScreen(SolarPanelScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, getGeneratingText(handler).orElse(title));
    }

    private static Optional<Component> getGeneratingText(SolarPanelScreenHandler handler) {
        if (handler != null) {
            return Optional.of(Component.translatable("message.solar_panels.generating").append(Component.literal(handler.energyRate + " EU/t")));
        } else {
            return Optional.empty();
        }
    }

    @Override
    protected void renderBg(PoseStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        blit(matrices, x, y, 0, 0, imageWidth, imageHeight);
        energyComponent.draw(matrices);
    }

    @Override
    protected void renderLabels(PoseStack matrices, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderEnergyComponentTooltips(matrices, mouseX, mouseY, x, y);
    }

    @Override
    public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        renderTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();

        titleLabelX = (imageWidth - font.width(title)) / 2;
        inventoryLabelY = inventoryLabelY - 34;

        assignEnergyComponent();
    }

    private void assignEnergyComponent() {
        energyComponent = new EnergyComponentArea(((width - imageWidth) / 2) + 10,
                ((height - imageHeight) / 2) + 13, menu.energyAmount, menu.energyCapacity);
    }

    private void renderEnergyComponentTooltips(PoseStack matrices, int pMouseX, int pMouseY, int x, int y) {
        if (isMouseAboveArea(pMouseX, pMouseY, x, y, 18, 22, 139, 10)) {
            renderTooltip(matrices, energyComponent.getTooltips(), Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return (pMouseX >= (x + offsetX) && pMouseX <= (x + offsetX) + width) && (pMouseY >= (y + offsetY) && pMouseY <= (y + offsetY) + height);
    }
}
