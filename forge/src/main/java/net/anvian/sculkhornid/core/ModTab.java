package net.anvian.sculkhornid.core;

import net.anvian.sculkhornid.Constants;
import net.anvian.sculkhornid.core.item.ModItems;
import net.anvian.sculkhornid.core.registry.ModItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTab {
    private ModTab() {}

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    public static RegistryObject<CreativeModeTab> BEDROCKPLUS = CREATIVE_MODE_TAB.register(
            "impure_bedrock",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.SCULKHORN))
                    .title(Component.translatable("itemGroup.sculkhorngroup"))
                    .build());

    @SubscribeEvent
    public static void onRegisterCreativeModeTabEvent(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() != ModTab.BEDROCKPLUS.get()) return;

        ModItemRegistry.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(event::accept);
    }
}
