package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.interactable.Button;
import dev.jcm.arbor.components.interactable.ListView;
import dev.jcm.arbor.components.layout.Align;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.layout.Stack;
import dev.jcm.arbor.components.utils.Sized;
import dev.jcm.arbor.components.utils.Text;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.layout.LayoutSetting;
import dev.jcm.arbor.renderer.ScreenUIRenderer;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.FlexSizeBehaviour;
import dev.jcm.arbor.utils.Size;
import net.minecraft.client.Minecraft;

public class TestAll implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new ListView.Builder()
                .withSpacing(2)
                .build(
                        new TestButton(new TestAlign()),
                        new TestButton(new dev.jcm.arbortest.tests.TestButton()),
                        new TestButton(new TestCenter()),
                        new TestButton(new TestClip()),
                        new TestButton(new TestColumn()),
                        new TestButton(new TestContainer()),
                        new TestButton(new TestDynamic()),
                        new TestButton(new TestListView()),
                        new TestButton(new TestPadding()),
                        new TestButton(new TestPositioned()),
                        new TestButton(new TestRender()),
                        new TestButton(new TestRenderable()),
                        new TestButton(new TestRow()),
                        new TestButton(new TestSized()),
                        new TestButton(new TestSlider()),
                        new TestButton(new TestStack()),
                        new TestButton(new TestText()),
                        new TestButton(new TestTextField()),
                        new TestButton(new TestTexture()),
                        new TestButton(new TestTooltip()),
                        new TestButton(new TestTransform()),
                        new TestButton(new TestWidgetWrapper()),
                        new TestButton(new TestInventoryVisual()),
                        new TestButton(new TestDynamicNesting())
                );
    }

    private record TestButton(UIComponent screen) implements UIComponent {

        @Override
        public UIComponent build(Layout layout, Theme theme) {
            return new Sized(
                    Size.staticSize(200, 20),
                    new Button.Builder()
                            .withOnPress((button) -> Minecraft.getInstance().setScreen(new ScreenUIRenderer(new TestScreen(screen), true)))
                            .build(new Center(new Text.Builder(screen.getClass().getSimpleName())))
            );
        }
    }

    private record TestScreen(UIComponent screen) implements UIComponent {

        @Override
        public UIComponent build(Layout layout, Theme theme) {
            return new Stack(
                    FlexSizeBehaviour.MAX,
                    screen,
                    new Align.Builder()
                            .withHorizontal(LayoutSetting.START)
                            .withVertical(LayoutSetting.START)
                            .build(new Sized(
                                    Size.staticSize(60, 20),
                                    new Button.Builder()
                                            .withOnPress((button) -> Minecraft.getInstance().setScreen(new ScreenUIRenderer(new TestAll(), true)))
                                            .build(new Center(new Text.Builder("Back"))))
                            )
            );
        }
    }
}
