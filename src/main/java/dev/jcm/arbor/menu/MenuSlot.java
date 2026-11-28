package dev.jcm.arbor.menu;

import dev.jcm.arbor.menu.handlers.ISlotHandler;
import dev.jcm.arbor.utils.SimpleVec2i;

public record MenuSlot<T extends ISlotHandler>(SimpleVec2i pos, T handler) {
    public MenuSlot<T> withPos(SimpleVec2i newPos) {
        return new MenuSlot<>(newPos, handler);
    }
}
