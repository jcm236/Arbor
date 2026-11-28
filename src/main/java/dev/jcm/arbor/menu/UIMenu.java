package dev.jcm.arbor.menu;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.menu.handlers.ISlotHandler;
import dev.jcm.arbor.theme.MinecraftTheme;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public abstract class UIMenu {

    private boolean needsRebuild;

    public abstract UIComponent build(Layout layout, Theme theme, ArborContainerMenu containerMenu);

    public abstract Component getTitle();

    public void onClose() {
    }

    protected void rebuild() {
        needsRebuild = true;
    }

    public boolean consumeRebuild() {
        boolean result = needsRebuild;
        needsRebuild = false;
        return result;
    }

    public int getTopPos(Layout layout, int width, int height) {
        return (height - layout.getHeight()) / 2;
    }

    public int getLeftPos(Layout layout, int width, int height) {
        return (width - layout.getWidth()) / 2;
    }

    public void tick(ArborContainerMenu menu) {
    }

    public ItemStack quickMoveStack(ArborContainerMenu menu, Player player, int index) {
        return ItemStack.EMPTY;
    }

    public boolean stillValid(ArborContainerMenu menu, Player player) {
        if (menu.context instanceof MenuContext.Block b) {
            return !menu.level.getBlockState(b.pos()).isAir() && player.distanceToSqr(Vec3.atCenterOf(b.pos())) <= 64.0;
        }
        if (menu.context instanceof MenuContext.EntityTarget) {
            return menu.getEntity().filter(Entity::isAlive).filter(e -> player.distanceToSqr(e) <= 64.0).isPresent();
        }
        if (menu.context instanceof MenuContext.HeldItem h) {
            return !player.getItemInHand(h.hand()).isEmpty();
        }
        return true;
    }

    public ArborContainerScreen createScreen(ArborContainerMenu menu, Inventory inv, Component title) {
        return new ArborContainerScreen(menu, inv, UIMenu.this, UIMenu.this.shouldRenderBackground(), UIMenu.this.getTheme(), title);
    }

    public boolean shouldRenderBackground() {
        return true;
    }

    public Theme getTheme() {
        return MinecraftTheme.INSTANCE;
    }

    public RegistryObject<MenuType<ArborContainerMenu>> registerMenuType(DeferredRegister<MenuType<?>> register, ArborMenuHolder menu, String name) {
        return register.register(name, () -> IForgeMenuType.create((cid, inv, data) -> newMenu(menu, inv, cid, MenuContext.read(data))));
    }

    public ArborContainerMenu newMenu(ArborMenuHolder menuHolder, Inventory playerInv, int containerId, MenuContext menuContext) {
        SimpleVec2i size = getSize();
        Layout layout = new Layout(size.x(), size.y());
        List<? extends MenuSlot<? extends ISlotHandler>> s;

        ArborContainerMenu menu = createMenu(menuHolder, playerInv, containerId, menuContext);
        if (menu.level.isClientSide()) {
            s = ArborMenuHelper.buildContainerOnClient(this, layout, getTheme(), menu);
        } else {
            s = getSlots(menu).stream().map(sl -> new MenuSlot<>(SimpleVec2i.zero(), sl)).toList();
        }

        for (MenuSlot<?> slot : s) {
            slot.handler().setupSync(menu, playerInv, slot.pos().x(), slot.pos().y());
        }

        addDataSlots(menu);
        return menu;
    }

    public abstract SimpleVec2i getSize();

    protected ArborContainerMenu createMenu(ArborMenuHolder menuHolder, Inventory playerInv, int containerId, MenuContext menuContext) {
        return new ArborContainerMenu(menuHolder, playerInv, containerId, menuContext);
    }

    public List<? extends ISlotHandler> getSlots(ArborContainerMenu menu) {
        return List.of();
    }

    protected void addDataSlots(ArborContainerMenu menu) {
    }
}
