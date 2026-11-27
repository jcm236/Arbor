package com.github.wintersteve25.tau.menu;

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

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class TauMenuHolder {

    private final Supplier<UIMenu> menu;
    private final RegistryObject<MenuType<TauContainerMenu>> inner;

    public TauMenuHolder(DeferredRegister<MenuType<?>> register, Supplier<UIMenu> menu, String name) {
        this.menu = menu;
        this.inner = menu.get().registerMenuType(register, this, name);
    }

    public MenuType<TauContainerMenu> get() {
        return inner.get();
    }

    public UIMenu getMenu() {
        return menu.get();
    }

    public void openMenu(ServerPlayer player, BlockPos pos) {
        NetworkHooks.openScreen(player, new MenuProvider() {
            @Override
            public @NotNull Component getDisplayName() {
                return getMenu().getTitle();
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
                return getMenu().newMenu(TauMenuHolder.this, playerInventory, containerId, pos);
            }
        }, buf -> buf.writeBlockPos(pos));
    }
}