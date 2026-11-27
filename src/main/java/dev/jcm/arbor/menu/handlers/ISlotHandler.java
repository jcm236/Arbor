package dev.jcm.arbor.menu.handlers;

import dev.jcm.arbor.menu.ArborContainerMenu;
import net.minecraft.world.entity.player.Inventory;

public interface ISlotHandler {
    void setupSync(ArborContainerMenu menu, Inventory playerInv, int x, int y);
}
