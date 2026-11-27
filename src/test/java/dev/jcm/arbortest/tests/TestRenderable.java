package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.render.RenderableComponent;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.Color;

public class TestRenderable implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new RenderableComponent(((graphics, pMouseX, pMouseY, pPartialTicks) -> {
            graphics.fill(0, 0, pMouseX, pMouseY, Color.WHITE.getAARRGGBB());
        }));
    }
}
