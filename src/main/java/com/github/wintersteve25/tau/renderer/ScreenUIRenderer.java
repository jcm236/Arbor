package com.github.wintersteve25.tau.renderer;

import com.github.wintersteve25.tau.build.BuiltUI;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.MinecraftTheme;
import com.github.wintersteve25.tau.theme.Theme;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ScreenUIRenderer extends Screen {

    private final UIComponent uiComponent;
    private final BuiltUI ui = new BuiltUI((List<GuiEventListener>) this.children());
    private final boolean renderBackground;
    private final Theme theme;

    public ScreenUIRenderer(UIComponent uiComponent, boolean renderBackground, Theme theme) {
        super(Component.empty());
        this.uiComponent = uiComponent;
        this.renderBackground = renderBackground;
        this.theme = theme;
    }

    public ScreenUIRenderer(UIComponent uiComponent, boolean renderBackground) {
        this(uiComponent, renderBackground, MinecraftTheme.INSTANCE);
    }

    public ScreenUIRenderer(UIComponent uiComponent) {
        this(uiComponent, true);
    }

    @Override
    protected void init() {
        ui.build(new Layout(width, height), theme, uiComponent);
    }

    @Override
    public void tick() {
        ui.tick();
    }

    @Override
    public void removed() {
        ui.destroy();
        super.onClose();
    }

    @Override
    public void render(GuiGraphics graphics, int pMouseX, int pMouseY, float pPartialTicks) {
        if (renderBackground) {
            this.renderBackground(graphics);
        }

        ui.render(graphics, pMouseX, pMouseY, pPartialTicks);

        ui.renderTooltips(graphics, pMouseX, pMouseY, pPartialTicks);
    }
}
