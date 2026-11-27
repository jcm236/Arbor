package com.github.wintersteve25.tautest;

import com.github.wintersteve25.tau.menu.TauMenuHelper;
import com.github.wintersteve25.tau.menu.TauMenuHolder;
import com.github.wintersteve25.tautest.tests.TestMenu;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(TauTest.MOD_ID)
public class TauTest {
    public static final String MOD_ID = "tautest";
    static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);
    public static final TauMenuHolder TEST_MENU = new TauMenuHolder(MENUS, TestMenu::new, "test_menu");

    public TauTest() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        MENUS.register(modBus);
        modBus.addListener((FMLClientSetupEvent e) -> TauMenuHelper.registerMenuScreen(e, TEST_MENU));
        MinecraftForge.EVENT_BUS.addListener(TauTest::registerCommands);
    }

    private static void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("tautest")
                .then(Commands.literal("menu").executes(ctx -> {
                    ServerPlayer player = ctx.getSource().getPlayerOrException();
                    TEST_MENU.openMenu(player);
                    return 1;
                }))
                .then(Commands.literal("block").executes(ctx -> {
                    ServerPlayer p = ctx.getSource().getPlayerOrException();
                    TEST_MENU.openMenu(p, p.blockPosition().below());
                    return 1;
                }))
        );
    }
}