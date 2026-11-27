package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.layout.Column;
import dev.jcm.arbor.components.utils.Text;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;

public class TestColumn implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(
                new Column.Builder().build(
                        new Text.Builder("Hello1"),
                        new Text.Builder("Hello2"),
                        new Text.Builder("Hello3"),
                        new Text.Builder("Hello4")
                ));
    }
}
