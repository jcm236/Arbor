package dev.jcm.arbor.components.layout;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.layout.Axis;
import dev.jcm.arbor.layout.LayoutSetting;
import dev.jcm.arbor.utils.FlexSizeBehaviour;

import java.util.Arrays;

public class Column extends Flex {
    public Column(int spacing, FlexSizeBehaviour sizeBehaviour, Iterable<UIComponent> children, LayoutSetting alignment) {
        super(Axis.VERTICAL, spacing, sizeBehaviour, children, alignment);
    }

    public static final class Builder {
        private int spacing;
        private FlexSizeBehaviour sizeBehaviour;
        private LayoutSetting alignment;

        public Builder() {
        }

        public Builder withSpacing(int spacing) {
            this.spacing = spacing;
            return this;
        }

        public Builder withSizeBehaviour(FlexSizeBehaviour sizeBehaviour) {
            this.sizeBehaviour = sizeBehaviour;
            return this;
        }

        public Builder withAlignment(LayoutSetting alignment) {
            this.alignment = alignment;
            return this;
        }

        public Column build(UIComponent... children) {
            return build(Arrays.asList(children));
        }

        public Column build(Iterable<UIComponent> children) {
            return new Column(spacing,
                    sizeBehaviour == null
                            ? FlexSizeBehaviour.MIN
                            : sizeBehaviour,
                    children,
                    alignment == null ? LayoutSetting.CENTER : alignment);
        }
    }
}
