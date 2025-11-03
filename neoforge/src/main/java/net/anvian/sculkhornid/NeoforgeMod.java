package net.anvian.sculkhornid;

import net.anvian.sculkhornid.core.ModTab;
import net.anvian.sculkhornid.core.registry.ModItemRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class NeoforgeMod {

    public NeoforgeMod(IEventBus eventBus) {
        Constants.LOG.info("Hello from " + Constants.MOD_ID + " (Neoforge)");
        CommonMod.init();

        Constants.LOG.info("Registering items for " + Constants.MOD_NAME + "...");
        ModItemRegistry.ITEMS.register(eventBus);

        Constants.LOG.info("Registering creative tab for " + Constants.MOD_ID);
        ModTab.TABS.register(eventBus);
    }
}