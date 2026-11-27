package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.interactable.Button;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.utils.Clip;
import dev.jcm.arbor.components.utils.Sized;
import dev.jcm.arbor.components.utils.Text;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;
import dev.jcm.arbor.utils.Size;

public class TestClip implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(
                new Clip(
                        new Center(new Sized(Size.staticSize(200, 20), new Button.Builder()
                                .withOnPress(btn -> System.out.println("HALLO"))
                                .build(new Text.Builder("HelloAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")))),
                        new SimpleVec2i(0, 0),
                        Size.percentage(0.65f, 1f)));
    }
}
