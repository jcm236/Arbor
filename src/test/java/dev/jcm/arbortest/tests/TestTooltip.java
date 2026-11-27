package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.utils.Text;
import dev.jcm.arbor.components.utils.Tooltip;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import net.minecraft.network.chat.Component;

public class TestTooltip implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(
                new Tooltip.Builder()
                        .withComponent(Component.literal("Test"))
                        .build(new Text.Builder("HALLO!"))
        );
    }
}
