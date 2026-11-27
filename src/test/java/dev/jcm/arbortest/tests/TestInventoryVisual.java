package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.inventory.PlayerInventory;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.utils.Container;
import dev.jcm.arbor.components.utils.Padding;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.FlexSizeBehaviour;
import dev.jcm.arbor.utils.Pad;
import dev.jcm.arbor.utils.Variable;

public class TestInventoryVisual implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(new Container.Builder()
                .withSizeBehaviour(FlexSizeBehaviour.MIN)
                .withChild(new Padding(new Pad.Builder().all(10).build(), new PlayerInventory(new Variable<>(true)))));
    }
}
