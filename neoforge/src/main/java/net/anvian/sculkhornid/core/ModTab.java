package net.anvian.sculkhornid.core;

import net.anvian.sculkhornid.Constants;
import net.anvian.sculkhornid.core.item.ModItems;
import net.anvian.sculkhornid.core.registry.ModItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class ModTab {
    private ModTab() {
    }

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register("tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.sculkhorngroup"))
            .icon(ModItems.SCULKHORN::getDefaultInstance)
            .build());

    @SubscribeEvent
    public static void onRegisterCreativeModeTabEvent(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() != ModTab.TAB.get())
            return;

        ModItemRegistry.ITEMS.getEntries().stream().map(DeferredHolder::get).forEach(event::accept);
    }
}
