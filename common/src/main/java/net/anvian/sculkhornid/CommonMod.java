package net.anvian.sculkhornid;

import net.anvian.anvianslib.util.LibUtil;

public class CommonMod {
    public static void init() {
        Constants.LOG.info("Hello from " + Constants.MOD_ID);

        LibUtil.setupTelemetry(Constants.MOD_ID, Constants.MOD_VERSION);
    }
}