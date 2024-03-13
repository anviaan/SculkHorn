package net.anvian.sculkhornid;

import net.anvian.sculkhornid.core.ModTab;
import net.anvian.sculkhornid.core.registry.ModItemRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class ForgeMod {
    public ForgeMod() {
        Constants.LOG.info("Hello from " + Constants.MOD_ID + " (Forge)");
        CommonMod.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Constants.LOG.info("Registering items for " + Constants.MOD_NAME + "...");
        ModItemRegistry.ITEMS.register(bus);
        Constants.LOG.info("Registering creative tab for " + Constants.MOD_ID);
        ModTab.TABS.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}