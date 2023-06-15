package net.anvian.sculkhornid.item;

import net.anvian.sculkhornid.SculkHornMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ItemGroup {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
            SculkHornMod.MOD_ID);

    public static RegistryObject<CreativeModeTab> SCULKHORN = CREATIVE_MODE_TAB.register("sculkhorngroup", () ->
            CreativeModeTab.builder()
                    .icon(()-> new ItemStack(ModItems.SCULKHORN.get()))
                    .title(Component.translatable("itemGroup.sculkhorngroup"))
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
