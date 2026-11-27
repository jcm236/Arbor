package com.github.wintersteve25.tau.build;

import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;

import java.util.ArrayList;
import java.util.List;

public final class BuiltUI {
    private final BuildContext context;
    private boolean built;

    public BuiltUI(List<GuiEventListener> listenerSink) {
        this.context = new BuildContext(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), listenerSink, new ArrayList<>());
    }

    public BuiltUI() {
        this(new ArrayList<>());
    }

    public void build(Layout layout, Theme theme, UIComponent root) {
        destroy();
        context.clear();
        UIBuilder.build(layout, theme, root, context);
        built = true;
    }

    public void tick() {
        if (built) UIBuilder.rebuildAndTickDynamicUIComponents(context.dynamicUIComponents());
    }

    public void render(GuiGraphics g, int mx, int my, float pt) {
        for (Renderable r : context.renderables()) r.render(g, mx, my, pt);
    }

    public void renderTooltips(GuiGraphics g, int mx, int my, float pt) {
        for (Renderable r : context.tooltips()) r.render(g, mx, my, pt);
    }

    public void destroy() {
        for (DynamicUIComponent c : context.dynamicUIComponents()) c.destroyTree();
    }
}
