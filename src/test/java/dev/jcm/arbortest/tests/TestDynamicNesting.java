package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.DynamicUIComponent;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.layout.Column;
import dev.jcm.arbor.components.utils.Container;
import dev.jcm.arbor.components.utils.Padding;
import dev.jcm.arbor.components.utils.Text;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.FlexSizeBehaviour;
import dev.jcm.arbor.utils.Pad;
import net.minecraft.client.gui.components.events.GuiEventListener;

public class TestDynamicNesting implements UIComponent {

    private final Counter inContainer = new Counter("in container");
    private final Counter siblingA = new Counter("sibling A");
    private final Counter siblingB = new Counter("sibling B");

    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(new Column.Builder().withSpacing(4).build(
                new Container.Builder()
                        .withSizeBehaviour(FlexSizeBehaviour.MIN)
                        .withChild(new Padding(new Pad.Builder().all(5).build(), inContainer)),
                siblingA,
                siblingB));
    }

    private static final class Counter extends DynamicUIComponent implements GuiEventListener {
        private final String label;
        private int clicks;

        Counter(String label) {
            this.label = label;
        }

        @Override
        public UIComponent build(Layout layout, Theme theme) {
            UIComponent[] lines = new UIComponent[clicks + 1];
            for (int i = 0; i <= clicks; i++) lines[i] = new Text.Builder(label + " " + i);
            return new Column.Builder().build(lines);
        }

        @Override
        public boolean mouseClicked(double x, double y, int button) {
            clicks++;
            rebuild();
            return false; // false lets all three counters receive the click
        }

        @Override
        public void setFocused(boolean focused) {
        }

        @Override
        public boolean isFocused() {
            return false;
        }
    }
}
