package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.interactable.Button;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.utils.Sized;
import dev.jcm.arbor.components.utils.Text;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.Size;

public class TestButton implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(new Sized(
                Size.staticSize(100, 20),
                new Button.Builder()
                        .build(new Center(new Text.Builder("Button")))
        ));
    }
}
