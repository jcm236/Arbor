package dev.jcm.arbor.menu;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.build.UIBuilder;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.ArrayList;
import java.util.List;

public class ArborMenuHelper {
    public static void registerMenuScreen(FMLClientSetupEvent event, ArborMenuHolder holder) {
        event.enqueueWork(() -> MenuScreens.register(holder.get(),
                (MenuScreens.ScreenConstructor<ArborContainerMenu, ArborContainerScreen>)
                        (menu, inv, title) -> holder.getMenu().createScreen(menu, inv, title)));
    }

    public static List<MenuSlot<?>> buildContainerOnClient(UIMenu uiMenu, Layout layout, Theme theme, ArborContainerMenu menu) {
        UIComponent uiComponent = uiMenu.build(layout, theme, menu);
        List<MenuSlot<?>> slots = new ArrayList<>();
        UIBuilder.build(layout, theme, uiComponent, new BuildContext(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), slots));
        return slots;
    }
}
