package dev.jcm.arbor.components.inventory;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.menu.MenuSlot;
import dev.jcm.arbor.menu.handlers.PlayerInventoryHandler;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;
import dev.jcm.arbor.utils.Variable;

public class PlayerInventory implements PrimitiveUIComponent {

    private static final SimpleVec2i SIZE = new SimpleVec2i(162, 76); // 4 rows with 1 hotbar
    private final Variable<Boolean> enabled;

    public PlayerInventory(Variable<Boolean> enabled) {
        this.enabled = enabled;
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {

        SimpleVec2i pos = layout.getPosition(SIZE);

        context.slots().add(new MenuSlot<>(pos, new PlayerInventoryHandler(enabled)));
        context.renderables().add((pGuiGraphics, pMouseX, pMouseY, pPartialTick) -> {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    theme.drawSlot(pGuiGraphics, pos.x + j * 18, pos.y + i * 18);
                }
            }

            for (int i = 0; i < 9; i++) {
                theme.drawSlot(pGuiGraphics, pos.x + i * 18, pos.y + 58);
            }
        });

        return SIZE;
    }
}
