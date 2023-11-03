package dev.thestaticvoid.solar_panels.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.thestaticvoid.solar_panels.util.ResourceIdentifier;
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
    protected void renderBg(PoseStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        blit(matrices, x, y, 0, 0, imageWidth, 132);

        // Draw power background
        renderEnergyBars(matrices, x, y);
    }

    private void renderEnergyBars(PoseStack matrices, int x, int y) {
        // The background bar
        blit(matrices, x + 19, y + 23, 0, 132, 138, 9);

        int fillAmount;
        if (menu.energyAmount == menu.energyCapacity) {
            fillAmount = 138;
        } else {
            fillAmount = (int)(Mth.clamp((menu.getFillPercentage() / (float)100) * 138, 0, 138));
        }
        // The overlay bar
        blit(matrices, x + 19, y + 23, 0, 141, fillAmount, 9);
    }

    @Override
    protected void renderLabels(PoseStack matrices, int mouseX, int mouseY) {
        super.renderLabels(matrices, mouseX, mouseY);
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
    private void renderEnergyComponentTooltips(PoseStack matrices, int pMouseX, int pMouseY, int x, int y) {
        if (isMouseAboveArea(pMouseX, pMouseY, x, y, 18, 22, 139, 10)) {
            renderTooltip(matrices, getTooltipText(menu.energyAmount, menu.energyCapacity),
                    Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }

    private List<Component> getTooltipText(long energyAmount, long energyCapacity) {
        return List.of(Component.literal(energyAmount + " / " + energyCapacity + " EU"));
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return (pMouseX >= (x + offsetX) && pMouseX <= (x + offsetX) + width) && (pMouseY >= (y + offsetY) && pMouseY <= (y + offsetY) + height);
    }
}
