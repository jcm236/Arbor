package dev.jcm.arbor.components.base;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;

/**
 * A UI Component with access to lower level content. For more can be used for more complicated components that need to be customly rendered
 */
public interface PrimitiveUIComponent extends UIComponent {
    SimpleVec2i build(Layout layout, Theme theme, BuildContext context);

    @Override
    default UIComponent build(Layout layout, Theme theme) {
        return null;
    }

    /**
     * Returns this component's size in the given layout
     */
    default SimpleVec2i measure(Layout layout, Theme theme) {
        return build(layout, theme, new BuildContext());
    }
}
