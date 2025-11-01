package net.anvian.sculkhornid;

import net.anvian.sculkhornid.core.ModTab;
import net.anvian.sculkhornid.core.registry.ModItemRegistry;
import net.fabricmc.api.ModInitializer;

public class FabricMod implements ModInitializer {
    @Override
    public void onInitialize() {
        Constants.LOG.info("Hello from " + Constants.MOD_ID + " (Fabric)");
        CommonMod.init();

        Constants.LOG.info("Registering items for " + Constants.MOD_NAME + "...");
        ModItemRegistry.registerItems();

        Constants.LOG.info("Registering creative tab for " + Constants.MOD_ID);
        ModTab.registerTab();
    }
}
