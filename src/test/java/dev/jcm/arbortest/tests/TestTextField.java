package dev.jcm.arbortest.tests;

import dev.jcm.arbor.Arbor;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.interactable.TextField;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.utils.Sized;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.Size;
import net.minecraft.network.chat.Component;

public class TestTextField implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(
                new Sized(
                        Size.staticSize(200, 20),
                        new TextField.Builder()
                                .withHintText(Component.literal("Hint Text"))
                                .withOnChange(Arbor.LOGGER::info)
                )
        );
    }
}
