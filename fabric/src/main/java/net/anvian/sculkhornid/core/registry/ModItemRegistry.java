package net.anvian.sculkhornid.core.registry;

import net.anvian.anvianslib.util.RegistryUtil;
import net.anvian.sculkhornid.Constants;
import net.anvian.sculkhornid.core.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;

public class ModItemRegistry {
    public static void registerItems() {
        Constants.LOG.info("Registering items for " + Constants.MOD_NAME + "...");

        RegistryUtil.register(
                BuiltInRegistries.ITEM, Constants.MOD_ID, Constants.SCULKHORN_ITEM_ID, ModItems.SCULKHORN);
        RegistryUtil.register(
                BuiltInRegistries.ITEM,
                Constants.MOD_ID,
                Constants.SCULKHORN_SONICBOOM_ITEM_ID,
                ModItems.SCULKHORN_SONICBOOM);
    }
}
