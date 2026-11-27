package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.layout.Align;
import dev.jcm.arbor.components.utils.Text;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.layout.LayoutSetting;
import dev.jcm.arbor.theme.Theme;

public class TestAlign implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Align.Builder()
                .withVertical(LayoutSetting.END)
                .withHorizontal(LayoutSetting.CENTER)
                .build(new Text.Builder("Whats up?"));
    }
}
