package net.anvian.sculkhornid.core.item;

import net.anvian.sculkhornid.CommonMod;
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
    private static final ModConfigs.SculkHornConfig config = CommonMod.configs.getConfig();

    public static final Item SCULKHORN = new SculkHornArea(config, new Item.Properties()
            .rarity(Rarity.EPIC)
            .stacksTo(1)
            .useCooldown((float) config.areaCooldown)
            .durability(config.areaDurability)
            .setId(key(Constants.SCULKHORN_ITEM_ID))
    );
    public static final Item SCULKHORN_SONICBOOM = new SculkHornDistance(config, new Item.Properties()
            .rarity(Rarity.EPIC)
            .stacksTo(1)
            .useCooldown((float) config.distanceCooldown)
            .durability(config.distanceDurability)
            .setId(key(Constants.SCULKHORN_SONICBOOM_ITEM_ID))
    );

    private static ResourceKey<Item> key(String name) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }
}
