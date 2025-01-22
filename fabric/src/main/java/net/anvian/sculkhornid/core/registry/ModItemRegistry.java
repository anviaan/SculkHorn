package net.anvian.sculkhornid.core.registry;

import net.anvian.sculkhornid.Constants;
import net.anvian.sculkhornid.core.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class ModItemRegistry {
    public static void registerItems() {
        Constants.LOG.info("Registering items for " + Constants.MOD_NAME + "...");

        Registry.register(BuiltInRegistries.ITEM, id(Constants.SCULKHORN_ITEM_ID), ModItems.SCULKHORN);
        Registry.register(BuiltInRegistries.ITEM, id(Constants.SCULKHORN_SONICBOOM_ITEM_ID), ModItems.SCULKHORN_SONICBOOM);
    }

    private static ResourceLocation id(String name) {
        return new ResourceLocation(Constants.MOD_ID, name);
    }
}
