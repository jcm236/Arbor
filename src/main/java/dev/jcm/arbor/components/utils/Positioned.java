package dev.jcm.arbor.components.utils;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.build.UIBuilder;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;

public final class Positioned implements PrimitiveUIComponent {
    private final SimpleVec2i position;
    private final UIComponent child;

    public Positioned(SimpleVec2i position, UIComponent child) {
        this.position = position;
        this.child = child;
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {
        Layout childLayout = new Layout(layout.getWidth(), layout.getHeight(), position.x(), position.y());
        return UIBuilder.build(childLayout, theme, child, context);
    }
}
