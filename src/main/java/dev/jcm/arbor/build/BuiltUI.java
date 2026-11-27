package dev.jcm.arbor.build;

import dev.jcm.arbor.components.base.DynamicUIComponent;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;

import java.util.ArrayList;
import java.util.List;

public final class BuiltUI {
    private final BuildContext context;
    private boolean built;

    private Layout layout;
    private Theme theme;
    private UIComponent root;

    public BuiltUI() {
        this(new ArrayList<>());
    }

    public BuiltUI(List<GuiEventListener> listenerSink) {
        this.context = new BuildContext(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), listenerSink, new ArrayList<>());
    }

    public void build(Layout layout, Theme theme, UIComponent root) {
        this.layout = layout.copy();
        this.theme = theme;
        this.root = root;
        layoutAll();
    }

    private void layoutAll() {
        destroy();
        context.clear();
        UIBuilder.build(layout.copy(), theme, root, context);
        built = true;
    }

    public void destroy() {
        for (DynamicUIComponent c : context.dynamicUIComponents()) c.destroyTree();
    }

    public void tick() {
        if (built && UIBuilder.rebuildAndTickDynamicUIComponents(context.dynamicUIComponents())) {
            this.layoutAll();
        }
    }

    public void render(GuiGraphics g, int mx, int my, float pt) {
        for (Renderable r : context.renderables()) r.render(g, mx, my, pt);
    }

    public void renderTooltips(GuiGraphics g, int mx, int my, float pt) {
        for (Renderable r : context.tooltips()) r.render(g, mx, my, pt);
    }
}
