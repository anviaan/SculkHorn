package net.anvian.sculkhornid.item;

import net.anvian.sculkhornid.SculkHornMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SculkHornMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemGroup {
    public static CreativeModeTab SCULKHORN;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        SCULKHORN = event.registerCreativeModeTab(new ResourceLocation(SculkHornMod.MOD_ID, "sculkhorngroup"),
                builder -> builder
                        .icon(()-> new ItemStack(ModItems.SCULKHORN.get()))
                        .title(Component.translatable("itemGroup.sculkhorngroup"))
                        .build());
    }
}
