package net.anvian.sculkhornid;

import net.fabricmc.api.ModInitializer;

public class FabricMod implements ModInitializer {
    @Override
    public void onInitialize() {
        Constants.LOG.info("Hello from " + Constants.MOD_ID + " (Fabric)");
        CommonMod.init();
    }
}
