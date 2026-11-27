package dev.jcm.arbor.components.utils;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.layout.Axis;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;
import net.minecraft.client.gui.components.AbstractWidget;

public final class WidgetWrapper implements PrimitiveUIComponent {

    private final AbstractWidget child;

    public WidgetWrapper(AbstractWidget child) {
        this.child = child;
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {
        child.setWidth(layout.getWidth());
        child.setHeight(layout.getHeight());
        child.setX(layout.getPosition(Axis.HORIZONTAL, child.getWidth()));
        child.setY(layout.getPosition(Axis.VERTICAL, child.getHeight()));

        context.renderables().add(child);
        context.eventListeners().add(child);

        return layout.getSize();
    }
}
