package net.anvian.sculkhornid;

import net.anvian.sculkhornid.core.ModTab;
import net.anvian.sculkhornid.core.registry.ModItemRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class ForgeMod {
    public ForgeMod() {
        Constants.LOG.info("Hello from " + Constants.MOD_ID + " (Forge)");
        CommonMod.init();

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModTab.CREATIVE_MODE_TAB.register(eventBus);
        ModItemRegistry.ITEMS.register(eventBus);
    }
}