package dev.jcm.arbor.components.inventory;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.menu.MenuSlot;
import dev.jcm.arbor.menu.handlers.ItemSlotHandler;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;

public class ItemSlot implements PrimitiveUIComponent {

    // hard coded texture length for a slot in minecraft
    private static final SimpleVec2i SIZE = new SimpleVec2i(18, 18);
    private final ItemSlotHandler slotHandler;

    public ItemSlot(ItemSlotHandler slotHandler) {
        this.slotHandler = slotHandler;
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {
        SimpleVec2i pos = layout.getPosition(SIZE);

        context.renderables().add((pGuiGraphics, pMouseX, pMouseY, pPartialTick) -> theme.drawSlot(pGuiGraphics, pos.x, pos.y));
        context.slots().add(new MenuSlot<>(pos, slotHandler));

        return SIZE;
    }
}
