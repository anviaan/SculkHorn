package net.anvian.sculkhornid;

import net.anvian.sculkhornid.platform.Services;

public class CommonMod {
    public static void init() {
        if (Services.PLATFORM.isModLoaded(Constants.MOD_ID)) {
            Constants.LOG.info("Hello from " + Constants.MOD_ID);
        }
    }
}