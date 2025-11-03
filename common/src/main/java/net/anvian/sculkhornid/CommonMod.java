package net.anvian.sculkhornid;

import net.anvian.anvianslib.platform.Services;
import net.anvian.anvianslib.util.LibUtil;
import net.anvian.sculkhornid.core.config.ModConfigs;

public class CommonMod {
    public static ModConfigs configs = new ModConfigs(ModConfigs.SculkHornConfig.class, Constants.LOG);

    public static void init() {
        Constants.LOG.info("Hello from " + Constants.MOD_ID);

        configs.initialize(Services.PLATFORM.getConfigPath().resolve(Constants.MOD_ID).toFile(), Constants.MOD_ID);

        LibUtil.setupTelemetry(Constants.MOD_ID, "3.3");
    }
}