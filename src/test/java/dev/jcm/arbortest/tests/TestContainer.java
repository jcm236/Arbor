package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.utils.Container;
import dev.jcm.arbor.components.utils.Text;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;

public class TestContainer implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Container.Builder()
                .withChild(new Center(new Text.Builder("Blue!!")));
    }
}
