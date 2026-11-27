package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.DynamicUIComponent;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.utils.Container;
import dev.jcm.arbor.components.utils.Sized;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.Size;
import net.minecraft.client.gui.components.events.GuiEventListener;

public class TestDynamic extends DynamicUIComponent implements GuiEventListener {
    private boolean clicked;

    @Override
    public UIComponent build(Layout layout, Theme theme) {
        if (clicked) {
            return new Container.Builder();
        }

        return new Center(new Sized(
                Size.staticSize(200, 200),
                new Container.Builder()
        ));
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        clicked = !clicked;
        rebuild();
        return GuiEventListener.super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public void setFocused(boolean pFocused) {
    }

    @Override
    public boolean isFocused() {
        return false;
    }
}
