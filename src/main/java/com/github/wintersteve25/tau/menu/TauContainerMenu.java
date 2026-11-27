package com.github.wintersteve25.tau.menu;

import com.github.wintersteve25.tau.Tau;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TauContainerMenu extends AbstractContainerMenu {

    public final Level level;
    public final MenuContext context;

    private final Map<String, IndexedDataSlot> dataSlots;
    private final UIMenu menu;

    public TauContainerMenu(TauMenuHolder holder, Inventory playerInventory, int pContainerId, MenuContext context) {
        super(holder.get(), pContainerId);

        this.level = playerInventory.player.level();
        this.context = context;
        this.dataSlots = new HashMap<>();
        this.menu = holder.getMenu();
    }

    public @NotNull Slot addSlot(@NotNull Slot pSlot) {
        return super.addSlot(pSlot);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return menu.quickMoveStack(this, pPlayer, pIndex);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return menu.stillValid(this, pPlayer);
    }

    @Override
    public boolean moveItemStackTo(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
        return super.moveItemStackTo(stack, startIndex, endIndex, reverseDirection);
    }

    public void addDataSlot(String name, Supplier<Integer> getter, Consumer<Integer> setter) {
        addDataSlot(name, new DataSlot() {
            @Override
            public int get() {
                return getter.get();
            }

            @Override
            public void set(int value) {
                setter.accept(value);
            }
        });
    }

    public void addDataSlot(String name, DataSlot slot) {
        if (dataSlots.containsKey(name)) {
            Tau.LOGGER.error("Duplicated data slot key: " + name);
            return;
        }

        dataSlots.put(name, new IndexedDataSlot(dataSlots.size(), slot));
        addDataSlot(slot);
    }

    public Optional<Supplier<Integer>> getGetterForDataSlot(String name) {
        if (!dataSlots.containsKey(name)) {
            Tau.LOGGER.error("Unknown data slot key: " + name);
            return Optional.empty();
        }

        return Optional.of(dataSlots.get(name).dataSlot()::get);
    }

    public <T extends BlockEntity> Optional<T> getBlockEntity(Class<T> type) {
        return getBlockPos().map(level::getBlockEntity).filter(type::isInstance).map(type::cast);
    }

    public Optional<BlockPos> getBlockPos() {
        return context instanceof MenuContext.Block b ? Optional.of(b.pos()) : Optional.empty();
    }

    public Optional<Entity> getEntity() {
        if (context instanceof MenuContext.EntityTarget e) {
            return Optional.ofNullable(level.getEntity(e.entityId()));
        }
        return Optional.empty();
    }

    private record IndexedDataSlot(int index, DataSlot dataSlot) {
    }
}
