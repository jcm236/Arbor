package com.github.wintersteve25.tau.menu;

import com.github.wintersteve25.tau.build.BuildContext;
import com.github.wintersteve25.tau.build.UIBuilder;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.ArrayList;
import java.util.List;

public class TauMenuHelper {
    public static void registerMenuScreen(FMLClientSetupEvent event, TauMenuHolder holder) {
        event.enqueueWork(() -> MenuScreens.register(holder.get(),
                (menu, inv, title) -> holder.getMenu().createScreen(menu, inv, title)));
    }
    
    public static List<MenuSlot<?>> buildContainerOnClient(UIMenu uiMenu, Layout layout, Theme theme, TauContainerMenu menu) {
        UIComponent uiComponent = uiMenu.build(layout, theme, menu);
        List<MenuSlot<?>> slots = new ArrayList<>();
        UIBuilder.build(layout, theme, uiComponent, new BuildContext(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), slots));
        return slots;
    }
}
