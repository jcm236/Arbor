package dev.jcm.arbor.components.utils;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.build.UIBuilder;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.layout.Axis;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.FlexSizeBehaviour;
import dev.jcm.arbor.utils.SimpleVec2i;

public final class Container implements PrimitiveUIComponent {

    private final UIComponent child;
    private final FlexSizeBehaviour sizeBehaviour;
    private final boolean drawBackground;

    public Container(UIComponent child, boolean drawBackground, FlexSizeBehaviour sizeBehaviour) {
        this.child = child;
        this.drawBackground = drawBackground;
        this.sizeBehaviour = sizeBehaviour;
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {
        if (child == null && !drawBackground) {
            return layout.getSize();
        }

        SimpleVec2i size = layout.getSize();
        BuildContext innerContext = new BuildContext();

        if (child != null) {
            if (sizeBehaviour == FlexSizeBehaviour.MIN) {
                size = UIBuilder.build(layout, theme, child, innerContext);
            } else {
                UIBuilder.build(layout, theme, child, innerContext);
            }
        }

        if (drawBackground) {
            int width = size.x();
            int height = size.y();
            int x = layout.getPosition(Axis.HORIZONTAL, width);
            int y = layout.getPosition(Axis.VERTICAL, height);
            context.renderables().add((graphics, pMouseX, pMouseY, pPartialTicks) -> theme.drawContainer(graphics, x, y, width, height, pPartialTicks, pMouseX, pMouseY));
        }

        context.addAll(innerContext);
        return size;
    }

    @Override
    public SimpleVec2i measure(Layout layout, Theme theme) {
        if (child == null || sizeBehaviour != FlexSizeBehaviour.MIN) return layout.getSize();
        return UIBuilder.measure(layout, theme, child);
    }

    public static final class Builder implements UIComponent {
        private UIComponent child;
        private boolean drawBackground = true;
        private FlexSizeBehaviour sizeBehaviour;

        public Builder() {
        }

        public Builder withChild(UIComponent child) {
            this.child = child;
            return this;
        }

        public Builder noBackground() {
            this.drawBackground = false;
            return this;
        }

        public Builder withSizeBehaviour(FlexSizeBehaviour sizeBehaviour) {
            this.sizeBehaviour = sizeBehaviour;
            return this;
        }

        @Override
        public UIComponent build(Layout layout, Theme theme) {
            return build();
        }

        public Container build() {
            return new Container(child, drawBackground, sizeBehaviour == null ? FlexSizeBehaviour.MAX : sizeBehaviour);
        }
    }
}
