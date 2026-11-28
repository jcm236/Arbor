package dev.jcm.arbor.components.layout;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.build.UIBuilder;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.layout.Axis;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.layout.LayoutSetting;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;

public final class Align implements PrimitiveUIComponent {

    private final UIComponent child;
    private final LayoutSetting horizontal;
    private final LayoutSetting vertical;

    public Align(UIComponent child, LayoutSetting horizontal, LayoutSetting vertical) {
        this.child = child;
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {
        if (horizontal != null) {
            layout.pushLayoutSetting(Axis.HORIZONTAL, horizontal);
        }

        if (vertical != null) {
            layout.pushLayoutSetting(Axis.VERTICAL, vertical);
        }

        SimpleVec2i size = UIBuilder.build(layout, theme, child, context);

        if (horizontal != null) {
            layout.popLayoutSetting(Axis.HORIZONTAL);
        }

        if (vertical != null) {
            layout.popLayoutSetting(Axis.VERTICAL);
        }

        return size;
    }

    @Override
    public SimpleVec2i measure(Layout layout, Theme theme) {
        return UIBuilder.measure(layout, theme, child);
    }


    public static final class Builder {
        private LayoutSetting horizontal;
        private LayoutSetting vertical;

        public Builder() {
        }

        public Builder withHorizontal(LayoutSetting horizontal) {
            this.horizontal = horizontal;
            return this;
        }

        public Builder withVertical(LayoutSetting vertical) {
            this.vertical = vertical;
            return this;
        }

        public Align build(UIComponent child) {
            return new Align(child, horizontal, vertical);
        }
    }
}
