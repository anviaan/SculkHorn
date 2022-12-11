package net.anvian.sculkhornid.item;

import net.anvian.sculkhornid.SculkHornMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup SCULKHORNGROUP =
            FabricItemGroup.builder(new Identifier(SculkHornMod.MOD_ID, "sculkhorngroup"))
            .icon(() -> new ItemStack(ModItems.SCULKHORN))
            .build();
}
