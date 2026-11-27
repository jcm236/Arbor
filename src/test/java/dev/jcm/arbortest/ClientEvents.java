package dev.jcm.arbortest;

import dev.jcm.arbor.renderer.ScreenUIRenderer;
import dev.jcm.arbortest.tests.TestAll;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArborTest.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvents {

    @SubscribeEvent
    public static void registerClientCommands(RegisterClientCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("arbortest").then(Commands.literal("tests").executes(ctx -> {
            Minecraft.getInstance().setScreen(new ScreenUIRenderer(new TestAll(), true));
            return 1;
        })));
    }
}
