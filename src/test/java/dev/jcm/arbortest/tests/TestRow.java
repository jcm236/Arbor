package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.interactable.Button;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.layout.Row;
import dev.jcm.arbor.components.utils.Sized;
import dev.jcm.arbor.components.utils.Text;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.Size;

public class TestRow implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(new Row.Builder()
                .withSpacing(5)
                .build(
                        new Sized(
                                Size.staticSize(100, 20),
                                new Button.Builder().build(new Center(new Text.Builder("Hello")))
                        ),
                        new Text.Builder("Hello"),
                        new Text.Builder("This is a row")
                ));
    }
}
