package net.anvian.sculkhornid.core.registry;

import net.anvian.sculkhornid.Constants;
import net.anvian.sculkhornid.core.item.ModItems;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Constants.MOD_ID);

    public static final DeferredItem<Item> SCULKHORN = ITEMS.register(Constants.SCULKHORN_ITEM_ID, () -> ModItems.SCULKHORN);
    public static final DeferredItem<Item> SCULKHORN_SONICBOOM = ITEMS.register(Constants.SCULKHORN_SONICBOOM_ITEM_ID, () -> ModItems.SCULKHORN_SONICBOOM);
}
