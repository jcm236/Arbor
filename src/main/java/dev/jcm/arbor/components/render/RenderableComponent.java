package dev.jcm.arbor.components.render;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;
import net.minecraft.client.gui.components.Renderable;

public final class RenderableComponent implements PrimitiveUIComponent {

    private final Renderable renderable;

    public RenderableComponent(Renderable renderable) {
        this.renderable = renderable;
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {
        context.renderables().add(renderable);
        return layout.getSize();
    }
}
