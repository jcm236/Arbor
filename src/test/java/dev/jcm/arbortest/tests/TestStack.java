package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.interactable.TextField;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.layout.Stack;
import dev.jcm.arbor.components.utils.Container;
import dev.jcm.arbor.components.utils.Sized;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.FlexSizeBehaviour;
import dev.jcm.arbor.utils.SimpleVec2i;
import dev.jcm.arbor.utils.Size;
import net.minecraft.network.chat.Component;

public class TestStack implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Stack(
                FlexSizeBehaviour.MAX,
                new Container.Builder(),
                new Center(new Sized(
                        Size.staticSize(new SimpleVec2i(100, 20)),
                        new TextField.Builder()
                                .withMessage(Component.literal("Hello"))
                                .withHintText(Component.literal("Hello!")))
                ));
    }
}
