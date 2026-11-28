package dev.jcm.arbor.components.utils;

import dev.jcm.arbor.Arbor;
import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.build.UIBuilder;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.layout.Axis;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;
import dev.jcm.arbor.utils.Size;

public final class Sized implements PrimitiveUIComponent {

    private final Size size;
    private final UIComponent child;

    public Sized(Size size, UIComponent child) {
        this.size = size;
        this.child = child;
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {
        SimpleVec2i componentSize = size.get(layout.getSize());

        if (componentSize.outside(layout.getSize())) {
            Arbor.LOGGER.error("Sized UIComponent has a size greater than the amount of size available");
            return layout.getSize();
        }

        Layout childLayout = new Layout(
                componentSize.x(),
                componentSize.y(),
                layout.getPosition(Axis.HORIZONTAL, componentSize.x()),
                layout.getPosition(Axis.VERTICAL, componentSize.y()));

        UIBuilder.build(childLayout, theme, child, context);
        return componentSize;
    }
}
