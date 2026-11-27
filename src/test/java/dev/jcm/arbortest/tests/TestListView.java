package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.interactable.Button;
import dev.jcm.arbor.components.interactable.ListView;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.utils.Container;
import dev.jcm.arbor.components.utils.Sized;
import dev.jcm.arbor.components.utils.Text;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.layout.LayoutSetting;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;
import dev.jcm.arbor.utils.Size;

public class TestListView implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(
                new Sized(
                        Size.staticSize(new SimpleVec2i(200, 100)),
                        new Container.Builder()
                                .withChild(
                                        new ListView.Builder()
                                                .withAlignment(LayoutSetting.START)
                                                .build(
                                                        new Text.Builder("Hello"),
                                                        new Text.Builder("Hello Again"),
                                                        new Text.Builder("Hello Again"),
                                                        new Text.Builder("Hello Again"),
                                                        new Text.Builder("Hello Again"),
                                                        new Text.Builder("Hello Again"),
                                                        new Text.Builder("Hello Again"),
                                                        new Text.Builder("Hello Again"),
                                                        new Text.Builder("Hello Again"),
                                                        new Text.Builder("Hello Again"),
                                                        new Text.Builder("Hello Again"),
                                                        new Text.Builder("Hello Again"),
                                                        new Sized(
                                                                Size.staticSize(new SimpleVec2i(60, 20)),
                                                                new Button.Builder()
                                                                        .withOnPress((button) -> {
                                                                        })
                                                                        .build(new Center(new Text.Builder("A Button")))
                                                        )
                                                ))
                ));
    }
}
