package com.github.wintersteve25.tautest;

import com.github.wintersteve25.tau.renderer.ScreenUIRenderer;
import com.github.wintersteve25.tautest.tests.TestAll;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TauTest.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvents {

    @SubscribeEvent
    public static void registerClientCommands(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("tautest").then(Commands.literal("tests").executes(ctx -> {
            Minecraft.getInstance().setScreen(new ScreenUIRenderer(new TestAll(), true));
            return 1;
        })));
    }
}
