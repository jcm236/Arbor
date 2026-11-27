package dev.jcm.arbor.components.render;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.layout.Axis;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.RenderProvider;
import dev.jcm.arbor.utils.SimpleVec2i;

public final class Render implements PrimitiveUIComponent {
    private final RenderProvider renderer;

    public Render(RenderProvider renderer) {
        this.renderer = renderer;
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {
        int width = layout.getWidth();
        int height = layout.getHeight();
        int x = layout.getPosition(Axis.HORIZONTAL, width);
        int y = layout.getPosition(Axis.VERTICAL, height);
        context.renderables().add((guiGraphics, pMouseX, pMouseY, pPartialTicks) -> renderer.render(guiGraphics, pMouseX, pMouseY, pPartialTicks, x, y, width, height));
        return layout.getSize();
    }
}
