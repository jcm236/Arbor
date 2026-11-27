package com.github.wintersteve25.tautest.tests;

import com.github.wintersteve25.tau.components.base.DynamicUIComponent;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.components.layout.Center;
import com.github.wintersteve25.tau.components.layout.Column;
import com.github.wintersteve25.tau.components.utils.Container;
import com.github.wintersteve25.tau.components.utils.Padding;
import com.github.wintersteve25.tau.components.utils.Text;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.FlexSizeBehaviour;
import com.github.wintersteve25.tau.utils.Pad;
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
