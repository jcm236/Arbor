package dev.jcm.arbor.build;

import dev.jcm.arbor.components.base.DynamicUIComponent;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;
import net.minecraft.client.gui.components.events.GuiEventListener;

import java.util.List;

public class UIBuilder {
    /**
     * @param layout      The layout of this ui component. Used to position children components
     * @param uiComponent The ui component to build into a list of renderables
     * @param context     A build context containing the underlying renderables and such
     * @return The size of the component
     */
    public static SimpleVec2i build(Layout layout, Theme theme, UIComponent uiComponent, BuildContext context) {
        return build(layout, theme, uiComponent, context, SimpleVec2i.zero());
    }

    // param size is the accumulated size of this component branch
    private static SimpleVec2i build(Layout layout, Theme theme, UIComponent uiComponent, BuildContext context, SimpleVec2i size) {
        if (uiComponent instanceof DynamicUIComponent dynamic) {
            return size.addNew(dynamic.mount(layout, theme, context));
        }

        if (uiComponent instanceof GuiEventListener) {
            context.eventListeners().add((GuiEventListener) uiComponent);
        }

        if (uiComponent instanceof PrimitiveUIComponent primitiveUIComponent) {
            size = size.addNew(primitiveUIComponent.build(layout, theme, context));
        }

        UIComponent next = uiComponent.build(layout, theme);

        if (next == null) {
            return size;
        }

        return build(layout, theme, next, context, size);
    }

    /**
     * Rebuilds all dynamic components that requires rebuilding
     *
     * @param dynamicUIComponents All dynamic components
     * @return Whether relayout is needed (something changed size
     */
    public static boolean rebuildAndTickDynamicUIComponents(List<DynamicUIComponent> dynamicUIComponents) {
        boolean relayout = false;
        for (DynamicUIComponent component : List.copyOf(dynamicUIComponents)) {
            relayout |= component.tickTree();
        }
        return relayout;
    }

    /**
     * @return the size the component would take in this layout
     */
    public static SimpleVec2i measure(Layout layout, Theme theme, UIComponent component) {
        return build(layout.copy(), theme, component, new BuildContext());
    }
}
