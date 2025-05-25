package net.anvian.sculkhornid.core.item;

import net.anvian.sculkhornid.Constants;
import net.anvian.sculkhornid.core.config.ModConfigs;
import net.anvian.sculkhornid.core.item.custom.SculkHornArea;
import net.anvian.sculkhornid.core.item.custom.SculkHornDistance;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ModItems {
    public static final Item SCULKHORN = new SculkHornArea(new Item.Properties()
            .rarity(Rarity.EPIC)
            .stacksTo(1)
            .useCooldown((float) ModConfigs.areaCooldown)
            .durability(ModConfigs.areaDurability)
            .setId(key(Constants.SCULKHORN_ITEM_ID))
    );
    public static final Item SCULKHORN_SONICBOOM = new SculkHornDistance(new Item.Properties()
            .rarity(Rarity.EPIC)
            .stacksTo(1)
            .useCooldown((float) ModConfigs.distanceCooldown)
            .durability(ModConfigs.distanceDurability)
            .setId(key(Constants.SCULKHORN_SONICBOOM_ITEM_ID))
    );

    private static ResourceKey<Item> key(String name) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }
}
