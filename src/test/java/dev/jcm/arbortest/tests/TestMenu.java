package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.inventory.PlayerInventory;
import dev.jcm.arbor.components.utils.Container;
import dev.jcm.arbor.components.utils.Padding;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.menu.ArborContainerMenu;
import dev.jcm.arbor.menu.UIMenu;
import dev.jcm.arbor.menu.handlers.ISlotHandler;
import dev.jcm.arbor.menu.handlers.PlayerInventoryHandler;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.Pad;
import dev.jcm.arbor.utils.SimpleVec2i;
import dev.jcm.arbor.utils.Variable;
import net.minecraft.network.chat.Component;

import java.util.List;

public class TestMenu extends UIMenu {
    @Override
    public UIComponent build(Layout layout, Theme theme, ArborContainerMenu menu) {
        return new Container.Builder().withChild(
                new Padding(new Pad.Builder().all(7).build(), new PlayerInventory(new Variable<>(true))));
    }

    @Override
    public Component getTitle() {
        return Component.literal("Arbor test menu");
    }

    @Override
    public SimpleVec2i getSize() {
        return new SimpleVec2i(176, 90);
    }

    @Override
    public List<? extends ISlotHandler> getSlots(ArborContainerMenu menu) {
        return List.of(new PlayerInventoryHandler(new Variable<>(true)));
    }
}
