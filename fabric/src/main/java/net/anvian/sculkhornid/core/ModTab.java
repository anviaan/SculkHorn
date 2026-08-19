package net.anvian.sculkhornid.core;

import net.anvian.anvianslib.util.RegistryUtil;
import net.anvian.sculkhornid.Constants;
import net.anvian.sculkhornid.core.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class ModTab {
    public static void registerTab() {
        Constants.LOG.info("Registering creative tab for " + Constants.MOD_ID);

        ResourceKey<CreativeModeTab> tab =
                RegistryUtil.key(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID, "sculkhorngroup");
        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                tab,
                CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                        .icon(ModItems.SCULKHORN::getDefaultInstance)
                        .title(Component.translatable("itemGroup.sculkhorngroup"))
                        .displayItems((_, entries) -> {
                            entries.accept(ModItems.SCULKHORN);
                            entries.accept(ModItems.SCULKHORN_SONICBOOM);
                        })
                        .build());
    }
}
