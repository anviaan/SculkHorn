package net.anvian.sculkhornid.core.item;

import net.anvian.sculkhornid.CommonMod;
import net.anvian.sculkhornid.core.config.ModConfigs;
import net.anvian.sculkhornid.core.item.custom.SculkHornArea;
import net.anvian.sculkhornid.core.item.custom.SculkHornDistance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ModItems {
    private static final ModConfigs.SculkHornConfig config = CommonMod.configs.getConfig();

    public static final Item SCULKHORN = new SculkHornArea(config, new Item.Properties()
            .rarity(Rarity.EPIC)
            .stacksTo(1)
            .durability(config.areaDurability));
    public static final Item SCULKHORN_SONICBOOM = new SculkHornDistance(config, new Item.Properties()
            .rarity(Rarity.EPIC)
            .stacksTo(1)
            .durability(config.distanceDurability));
}
