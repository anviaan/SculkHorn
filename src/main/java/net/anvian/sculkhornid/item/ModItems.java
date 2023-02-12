package net.anvian.sculkhornid.item;

import net.anvian.sculkhornid.SculkHornMod;
import net.anvian.sculkhornid.item.custom.SculkHornArea;
import net.anvian.sculkhornid.item.custom.SculkHornDistance;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item SCULKHORN =registerItem("sculkhorn", new SculkHornArea(
            new FabricItemSettings()
                    .rarity(Rarity.EPIC)
                    .maxCount(1)
                    .maxDamage(SculkHornMod.CONFIG.AREA_DURABILITY())//350
            ));

    public static final Item SCULKHORN_SONICBOOM = registerItem("sculkhorn_sonicboom", new SculkHornDistance(
            new FabricItemSettings()
                    .rarity(Rarity.EPIC)
                    .maxCount(1)
                    .maxDamage(SculkHornMod.CONFIG.DISTANCE_DURABILITY())//500
            ));

    public static Item registerItem(String name, Item item){
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.SCULKHORNGROUP).register(entries -> entries.add(SCULKHORN));
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.SCULKHORNGROUP).register(entries -> entries.add(SCULKHORN_SONICBOOM));
        return Registry.register(Registries.ITEM, new Identifier(SculkHornMod.MOD_ID, name), item);
    }

    public static void registerModItems(){
        System.out.println("Registering Mod Items for " + SculkHornMod.MOD_ID);
    }
}
