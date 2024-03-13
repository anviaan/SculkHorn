package net.anvian.sculkhornid.core.registry;

import net.anvian.sculkhornid.Constants;
import net.anvian.sculkhornid.core.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class ModItemRegistry {
    public static void registerItems() {
        Constants.LOG.info("Registering items for " + Constants.MOD_NAME + "...");

        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "sculkhorn"), ModItems.SCULKHORN);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "sculkhorn_sonicboom"), ModItems.SCULKHORN_SONICBOOM);
    }
}
