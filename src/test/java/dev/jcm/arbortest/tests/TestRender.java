package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.render.Render;
import dev.jcm.arbor.components.utils.Sized;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.ItemRenderProvider;
import dev.jcm.arbor.utils.Size;
import net.minecraft.world.item.Items;

public class TestRender implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(new Sized(
                Size.staticSize(16, 16),
                new Render(new ItemRenderProvider(Items.GRASS_BLOCK))
        ));
    }
}
