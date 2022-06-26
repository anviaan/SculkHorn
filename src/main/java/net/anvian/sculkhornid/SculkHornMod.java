package net.anvian.sculkhornid;

import net.anvian.sculkhornid.config.ModConfigs;
import net.anvian.sculkhornid.item.ModItems;
import net.fabricmc.api.ModInitializer;

public class SculkHornMod implements ModInitializer {
	public static final String MOD_ID = "sculkhornid";

	@Override
	public void onInitialize() {

		ModConfigs.registerConfigs();

		ModItems.registerModItems();
	}
}
