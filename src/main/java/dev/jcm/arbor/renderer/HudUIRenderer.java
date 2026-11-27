package dev.jcm.arbor.renderer;

import com.mojang.blaze3d.platform.Window;
import dev.jcm.arbor.build.BuiltUI;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.MinecraftTheme;
import dev.jcm.arbor.theme.Theme;
import net.minecraft.client.gui.GuiGraphics;

public class HudUIRenderer {
    private final UIComponent uiComponent;

    private final Theme theme;
    private final BuiltUI ui = new BuiltUI();
    private int screenWidth;
    private int screenHeight;

    public HudUIRenderer(UIComponent uiComponent) {
        this(uiComponent, MinecraftTheme.INSTANCE);
    }

    public HudUIRenderer(UIComponent uiComponent, Theme theme) {
        this.uiComponent = uiComponent;
        this.theme = theme;
    }

    public void tick() {
        ui.tick();
    }

    public void render(Window mainWindow, GuiGraphics graphics, float pPartialTicks) {
        int width = mainWindow.getGuiScaledWidth();
        int height = mainWindow.getGuiScaledHeight();

        if (width != screenWidth || height != screenHeight) {
            screenWidth = width;
            screenHeight = height;
            init();
        }

        ui.render(graphics, 0, 0, pPartialTicks);
    }

    private void init() {
        Layout layout = new Layout(screenWidth, screenHeight);

        ui.build(layout, this.theme, this.uiComponent);
    }
}
