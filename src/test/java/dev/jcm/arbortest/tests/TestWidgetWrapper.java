package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.utils.Sized;
import dev.jcm.arbor.components.utils.WidgetWrapper;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.Size;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.widget.ForgeSlider;

public class TestWidgetWrapper implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(new Sized(
                Size.staticSize(200, 20),
                new WidgetWrapper(new ForgeSlider(
                        0, 0, 0, 0,
                        Component.literal("Prefix"),
                        Component.literal("Suffix"),
                        0, 1, 0, true
                ))
        ));
    }
}
