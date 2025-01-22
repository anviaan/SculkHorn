package net.anvian.sculkhornid.core.item;

import net.anvian.sculkhornid.core.config.ModConfigs;
import net.anvian.sculkhornid.core.item.custom.SculkHornArea;
import net.anvian.sculkhornid.core.item.custom.SculkHornDistance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ModItems {
    public static final Item SCULKHORN = new SculkHornArea(new Item.Properties()
            .rarity(Rarity.EPIC)
            .stacksTo(1)
            .durability(ModConfigs.areaDurability));
    public static final Item SCULKHORN_SONICBOOM = new SculkHornDistance(new Item.Properties()
            .rarity(Rarity.EPIC)
            .stacksTo(1)
            .durability(ModConfigs.distanceDurability));
}
