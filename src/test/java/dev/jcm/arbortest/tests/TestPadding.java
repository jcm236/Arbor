package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.utils.Container;
import dev.jcm.arbor.components.utils.Padding;
import dev.jcm.arbor.components.utils.Sized;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.Pad;
import dev.jcm.arbor.utils.Size;

public class TestPadding implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(new Sized(
                Size.staticSize(200, 200),
                new Container.Builder()
                        .withChild(new Padding(
                                new Pad.Builder().all(5).build(),
                                new Container.Builder()
                        ))
        ));
    }
}
