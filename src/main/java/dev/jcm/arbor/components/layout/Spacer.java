package dev.jcm.arbor.components.layout;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;

public final class Spacer implements PrimitiveUIComponent {

    private final SimpleVec2i size;

    public Spacer(SimpleVec2i size) {
        this.size = size;
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {
        return size;
    }

    @Override
    public SimpleVec2i measure(Layout layout, Theme theme) {
        return size;
    }
}
