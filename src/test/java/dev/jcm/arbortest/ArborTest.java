package dev.jcm.arbortest;

import dev.jcm.arbor.menu.ArborMenuHelper;
import dev.jcm.arbor.menu.ArborMenuHolder;
import dev.jcm.arbortest.tests.TestMenu;
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

@Mod(ArborTest.MOD_ID)
public class ArborTest {
    public static final String MOD_ID = "arbortest";
    static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);
    public static final ArborMenuHolder TEST_MENU = new ArborMenuHolder(MENUS, TestMenu::new, "test_menu");

    public ArborTest() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        MENUS.register(modBus);
        modBus.addListener((FMLClientSetupEvent e) -> ArborMenuHelper.registerMenuScreen(e, TEST_MENU));
        MinecraftForge.EVENT_BUS.addListener(ArborTest::registerCommands);
    }

    private static void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("arbortest")
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