package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.utils.Text;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;

public class TestText implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(
                new Text.Builder("Wow hello a lot of textttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt")
                        .withOverflowBehaviour(Text.OverflowBehaviour.ELLIPSIS)
        );
    }
}
