package com.github.wintersteve25.tau.menu;

import com.google.common.base.Suppliers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class TauMenuHolder {

    private final Supplier<UIMenu> menu;
    private final RegistryObject<MenuType<TauContainerMenu>> inner;

    public TauMenuHolder(DeferredRegister<MenuType<?>> register, Supplier<UIMenu> menu, String name) {
        this.menu = Suppliers.memoize(menu::get);
        this.inner = menu.get().registerMenuType(register, this, name);
    }

    public MenuType<TauContainerMenu> get() {
        return inner.get();
    }

    public void openMenu(ServerPlayer player) {
        openMenu(player, MenuContext.NONE);
    }

    public void openMenu(ServerPlayer player, MenuContext context) {
        NetworkHooks.openScreen(player, new MenuProvider() {
            @Override
            public @NotNull Component getDisplayName() {
                return getMenu().getTitle();
            }

            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inv, Player p) {
                return getMenu().newMenu(TauMenuHolder.this, inv, id, context);
            }
        }, context::write);
    }

    public UIMenu getMenu() {
        return menu.get();
    }

    public void openMenu(ServerPlayer player, BlockPos pos) {
        openMenu(player, MenuContext.block(pos));
    }
}