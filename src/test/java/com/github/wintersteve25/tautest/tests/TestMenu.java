package com.github.wintersteve25.tautest.tests;

import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.components.inventory.PlayerInventory;
import com.github.wintersteve25.tau.components.utils.Container;
import com.github.wintersteve25.tau.components.utils.Padding;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.menu.TauContainerMenu;
import com.github.wintersteve25.tau.menu.UIMenu;
import com.github.wintersteve25.tau.menu.handlers.ISlotHandler;
import com.github.wintersteve25.tau.menu.handlers.PlayerInventoryHandler;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.Pad;
import com.github.wintersteve25.tau.utils.SimpleVec2i;
import com.github.wintersteve25.tau.utils.Variable;
import net.minecraft.network.chat.Component;

import java.util.List;

public class TestMenu extends UIMenu {
    @Override
    public UIComponent build(Layout layout, Theme theme, TauContainerMenu menu) {
        return new Container.Builder().withChild(
                new Padding(new Pad.Builder().all(7).build(), new PlayerInventory(new Variable<>(true))));
    }

    @Override public SimpleVec2i getSize() { return new SimpleVec2i(176, 90); }
    @Override public Component getTitle() { return Component.literal("Tau test menu"); }

    @Override
    public List<? extends ISlotHandler> getSlots(TauContainerMenu menu) {
        return List.of(new PlayerInventoryHandler(new Variable<>(true)));
    }
}
