package net.anvian.sculkhornid;

import net.anvian.anvianslib.config.TelemetryConfigManager;
import net.anvian.anvianslib.util.LibUtil;
import net.anvian.sculkhornid.platform.Services;
import net.minecraft.SharedConstants;

public class CommonMod {
    public static void init() {
        Constants.LOG.info("Hello from " + Constants.MOD_ID);

        LibUtil.generateConfigPath(Constants.MOD_ID, Services.PLATFORM.getGameConfigDirectory());

        TelemetryConfigManager.initialize(Services.PLATFORM.getGameConfigDirectory().resolve(Constants.MOD_ID).toFile());
        if (TelemetryConfigManager.getConfig().enableTelemetry) {
            TelemetryConfigManager.sendTelemetryData(
                    Constants.MOD_ID,
                    "3.0",
                    SharedConstants.getCurrentVersion().getName(),
                    Services.PLATFORM.getPlatformName(),
                    !Services.PLATFORM.isDevelopmentEnvironment()
            );
        }
    }
}