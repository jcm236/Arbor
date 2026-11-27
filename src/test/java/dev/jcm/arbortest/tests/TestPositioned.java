package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.utils.Positioned;
import dev.jcm.arbor.components.utils.Text;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;

public class TestPositioned implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Positioned(
                new SimpleVec2i(100, 20),
                new Text.Builder("Positioned")
        );
    }
}
