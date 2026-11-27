package com.github.wintersteve25.tau.components.base;

import com.github.wintersteve25.tau.build.BuildContext;
import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.SimpleVec2i;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.AbstractContainerEventHandler;
import net.minecraft.client.gui.components.events.GuiEventListener;

import java.util.List;

/**
 * A UI component that can be rebuilt on demand
 */
public abstract class DynamicUIComponent implements UIComponent {

    private final BuildContext own = new BuildContext();
    private final AbstractContainerEventHandler listeners = new AbstractContainerEventHandler() {
        @Override
        public List<? extends GuiEventListener> children() {
            return own.eventListeners();
        }
    };
    private Layout layout;
    private Theme theme;
    private boolean dirty;

    public void tick() {}
    public void destroy() {}

    protected final void rebuild() {
        dirty = true;
    }

    /**
     * Builds this component's contents into its own context and links them into the parent.
     */
    public final SimpleVec2i mount(Layout layout, Theme theme, BuildContext parent) {
        this.layout = layout.copy();
        this.theme = theme;
        SimpleVec2i size = buildContent();

        if (this instanceof GuiEventListener self) parent.eventListeners().add(self);
        parent.eventListeners().add(listeners);
        parent.renderables().add((g, mx, my, pt) -> { for (Renderable r : own.renderables()) r.render(g, mx, my, pt); });
        parent.tooltips().add((g, mx, my, pt) -> { for (Renderable r : own.tooltips()) r.render(g, mx, my, pt); });
        parent.dynamicUIComponents().add(this);
        parent.slots().addAll(own.slots()); // container slots are fixed once the menu opens
        return size;
    }

    public final void tickTree() {
        tick();
        for (DynamicUIComponent child : List.copyOf(own.dynamicUIComponents())) child.tickTree();
        if (dirty) {
            dirty = false;
            buildContent();
        }
    }

    public final void destroyTree() {
        for (DynamicUIComponent child : own.dynamicUIComponents()) child.destroyTree();
        destroy();
    }

    private SimpleVec2i buildContent() {
        for (DynamicUIComponent child : own.dynamicUIComponents()) child.destroyTree();
        own.clear();
        if (this instanceof PrimitiveUIComponent primitive) {
            return primitive.build(layout.copy(), theme, own);
        }
        UIComponent content = build(layout.copy(), theme);
        return content == null ? SimpleVec2i.zero() : UIBuilder.build(layout.copy(), theme, content, own);
    }
}
