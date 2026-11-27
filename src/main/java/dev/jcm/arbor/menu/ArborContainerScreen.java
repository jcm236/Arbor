package dev.jcm.arbor.menu;

import dev.jcm.arbor.build.BuiltUI;
import dev.jcm.arbor.layout.Axis;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArborContainerScreen extends AbstractContainerScreen<ArborContainerMenu> implements MenuAccess<ArborContainerMenu> {

    private final UIMenu uiMenu;

    private final BuiltUI ui = new BuiltUI((List<GuiEventListener>) this.children());

    private final boolean renderBackground;
    private final Theme theme;

    public ArborContainerScreen(ArborContainerMenu pMenu, Inventory pPlayerInventory, UIMenu uiMenu, boolean renderBackground, Theme theme, Component title) {
        super(pMenu, pPlayerInventory, title);
        this.uiMenu = uiMenu;
        this.renderBackground = renderBackground;
        this.theme = theme;
    }

    @Override
    protected void init() {
        Layout layout = new Layout(uiMenu.getSize().x, uiMenu.getSize().y);
        leftPos = uiMenu.getLeftPos(layout, width, height);
        topPos = uiMenu.getTopPos(layout, width, height);

        layout.pushOffset(Axis.HORIZONTAL, leftPos);
        layout.pushOffset(Axis.VERTICAL, topPos);

        ui.build(layout, theme, uiMenu.build(layout, theme, getMenu()));
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics graphics, float pPartialTick, int pMouseX, int pMouseY) {
        if (renderBackground) {
            this.renderBackground(graphics);
        }

        ui.render(graphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);

        ui.renderTooltips(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    }

    @Override
    public void containerTick() {
        if (uiMenu.consumeRebuild()) init();
        ui.tick();
        uiMenu.tick(menu);
    }

    @Override
    public void removed() {
        ui.destroy();
        uiMenu.onClose();
        super.removed();
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (childDrag(mouseX, mouseY, button, dragX, dragY)) {
            return true;
        }

        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    private boolean childDrag(double mouseX, double mouseY, int button, double dragX, double dragY) {
        return this.getFocused() != null && this.isDragging() && button == 0 && this.getFocused().mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }
}
