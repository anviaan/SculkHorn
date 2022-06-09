package net.anvian.sculkhornid.item;

import net.anvian.sculkhornid.SculkHornMod;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup SCULKHORNGROUP = FabricItemGroupBuilder.build
            (new Identifier(SculkHornMod.MOD_ID, "sculkhorngroup"),
                    () -> new ItemStack(ModItems.SCULKHORN));
}
