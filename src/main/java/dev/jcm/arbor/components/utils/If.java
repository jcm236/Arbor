package dev.jcm.arbor.components.utils;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.build.UIBuilder;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;

public class If implements PrimitiveUIComponent {

    private final boolean condition;
    private final UIComponent child;

    public If(boolean condition, UIComponent child) {
        this.condition = condition;
        this.child = child;
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {
        if (condition) return UIBuilder.build(layout, theme, child, context);
        return SimpleVec2i.zero();
    }

    @Override
    public SimpleVec2i measure(Layout layout, Theme theme) {
        return condition ? UIBuilder.measure(layout, theme, child) : SimpleVec2i.zero();
    }
}
