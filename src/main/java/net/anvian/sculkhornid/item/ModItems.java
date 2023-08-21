package net.anvian.sculkhornid.item;

import net.anvian.sculkhornid.SculkHornMod;
import net.anvian.sculkhornid.config.ModConfigs;
import net.anvian.sculkhornid.item.custom.SculkHornArea;
import net.anvian.sculkhornid.item.custom.SculkHornDistance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SculkHornMod.MOD_ID);

    public static final RegistryObject<Item> SCULKHORN = ITEMS.register("sculkhorn",
            () -> new SculkHornArea(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .stacksTo(1)
                    .durability(ModConfigs.AREA_DURABILITY.get())
                   ));

    public static final RegistryObject<Item> SCULKHORN_SONICBOOM = ITEMS.register("sculkhorn_sonicboom",
            () -> new SculkHornDistance(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .stacksTo(1)
                    .durability(ModConfigs.DISTANCE_DURABILITY.get())
                    ));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
