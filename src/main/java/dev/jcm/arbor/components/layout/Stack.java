package dev.jcm.arbor.components.layout;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.build.UIBuilder;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.FlexSizeBehaviour;
import dev.jcm.arbor.utils.SimpleVec2i;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class Stack implements PrimitiveUIComponent {
    private final Iterable<UIComponent> children;
    private final FlexSizeBehaviour sizeBehaviour;

    public Stack(FlexSizeBehaviour sizeBehaviour, Iterable<UIComponent> children) {
        this.sizeBehaviour = sizeBehaviour;
        this.children = children;
    }

    public Stack(FlexSizeBehaviour sizeBehaviour, UIComponent... children) {
        this.sizeBehaviour = sizeBehaviour;
        this.children = Arrays.stream(children).collect(Collectors.toList());
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {
        if (sizeBehaviour == FlexSizeBehaviour.MAX) {
            for (UIComponent child : children) {
                UIBuilder.build(layout, theme, child, context);
            }

            return layout.getSize();
        }

        BuildContext childContext = new BuildContext();
        SimpleVec2i size = SimpleVec2i.zero();

        for (UIComponent child : children) {
            SimpleVec2i cSize = UIBuilder.build(layout, theme, child, childContext);
            size = size.max(cSize);
        }

        context.addAll(childContext);
        return size;
    }

    @Override
    public SimpleVec2i measure(Layout layout, Theme theme) {
        if (sizeBehaviour == FlexSizeBehaviour.MAX) return layout.getSize();
        SimpleVec2i size = SimpleVec2i.zero();
        for (UIComponent child : children) size = size.max(UIBuilder.measure(layout, theme, child));
        return size;
    }

    public static class Builder {
        private Iterable<UIComponent> children;
        private FlexSizeBehaviour sizeBehaviour;

        public Builder() {
        }

        public Builder withSizeBehaviour(FlexSizeBehaviour sizeBehaviour) {
            this.sizeBehaviour = sizeBehaviour;
            return this;
        }

        public Stack build(UIComponent... children) {
            return new Stack(sizeBehaviour == null ? FlexSizeBehaviour.MAX : sizeBehaviour, children);
        }
    }
}
