package net.anvian.sculkhornid;

import net.anvian.sculkhornid.config.ModConfigs;
import net.anvian.sculkhornid.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SculkHornMod implements ModInitializer {
	public static final String MOD_ID = "sculkhornid";
	public static final Logger LOGGER = LoggerFactory.getLogger("sculkhornid");

	@Override
	public void onInitialize() {

		ModConfigs.registerConfigs();

		ModItems.registerModItems();

		LOGGER.info("Hello Fabric world!");
	}
}
