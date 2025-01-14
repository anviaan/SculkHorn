package net.anvian.sculkhornid.core.registry;

import net.anvian.sculkhornid.Constants;
import net.anvian.sculkhornid.core.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

    public static final RegistryObject<Item> SCULKHORN = ITEMS.register(Constants.SCULKHORN_ITEM_ID, () -> ModItems.SCULKHORN);
    public static final RegistryObject<Item> SCULKHORN_SONICBOOM = ITEMS.register(Constants.SCULKHORN_SONICBOOM_ITEM_ID, () -> ModItems.SCULKHORN_SONICBOOM);
}
