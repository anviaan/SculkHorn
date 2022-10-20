package net.anvian.sculkhornid.item;

import net.anvian.sculkhornid.SculkHornMod;
import net.anvian.sculkhornid.item.custom.SculkHorn;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SculkHornMod.MOD_ID);

    public static final RegistryObject<Item> SCULKHORN = ITEMS.register("sculkhorn",
            () -> new SculkHorn(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .stacksTo(1)
                    .durability(350)
                    .tab(ItemGroup.SCULKHORN)));

    public static final RegistryObject<Item> SCULKHORN_SONICBOOM = ITEMS.register("sculkhorn_sonicboom",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .stacksTo(1)
                    .durability(500)
                    .tab(ItemGroup.SCULKHORN)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
