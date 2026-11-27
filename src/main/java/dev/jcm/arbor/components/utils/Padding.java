package dev.jcm.arbor.components.utils;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.build.UIBuilder;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.layout.Axis;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.Pad;
import dev.jcm.arbor.utils.SimpleVec2i;

public final class Padding implements PrimitiveUIComponent {

    private final Pad pad;
    private final UIComponent child;

    public Padding(Pad pad, UIComponent child) {
        this.pad = pad;
        this.child = child;
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {
        if (pad == null) {
            return UIBuilder.build(layout, theme, child, context);
        }

        if (pad.left() == 0 || pad.right() == 0) {
            layout.pushOffset(Axis.HORIZONTAL, pad.left() - pad.right());
        } else {
            layout.pushOffset(Axis.HORIZONTAL, pad.left());
            layout.pushSizeMod(Axis.HORIZONTAL, -pad.right() - pad.left());
        }

        if (pad.top() == 0 || pad.bottom() == 0) {
            layout.pushOffset(Axis.VERTICAL, pad.top() - pad.bottom());
        } else {
            layout.pushOffset(Axis.VERTICAL, pad.top());
            layout.pushSizeMod(Axis.VERTICAL, -pad.bottom() - pad.top());
        }

        SimpleVec2i size = UIBuilder.build(layout, theme, child, context);

        layout.popOffset(Axis.VERTICAL);
        layout.popOffset(Axis.HORIZONTAL);

        if (pad.left() != 0 && pad.right() != 0) {
            layout.popSizeMod(Axis.HORIZONTAL);
        }

        if (pad.top() != 0 && pad.bottom() != 0) {
            layout.popSizeMod(Axis.VERTICAL);
        }

        return size.addNew(pad.getSize());
    }
}
