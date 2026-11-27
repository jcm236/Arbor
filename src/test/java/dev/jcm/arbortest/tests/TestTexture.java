package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.utils.Texture;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;

public class TestTexture implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(new Texture.Builder(InventoryScreen.INVENTORY_LOCATION)
                .withUvSize(new SimpleVec2i(176, 166))
                .build());
    }
}
