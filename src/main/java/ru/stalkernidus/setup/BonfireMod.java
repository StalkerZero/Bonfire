package ru.stalkernidus.setup;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static ru.stalkernidus.setup.BonfireMod.MOD_ID;

@Mod(MOD_ID)
public class BonfireMod {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "dsbonfire";

    public BonfireMod() {
        Registration.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {}
}