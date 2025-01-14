package net.anvian.sculkhornid;

import net.anvian.sculkhornid.core.ModTab;
import net.anvian.sculkhornid.core.config.ModConfigs;
import net.anvian.sculkhornid.core.registry.ModItemRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(Constants.MOD_ID)
public class ForgeMod {
    public ForgeMod() {
        Constants.LOG.info("Hello from " + Constants.MOD_ID + " (Forge)");
        CommonMod.init();

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ModConfigs.SPEC, Constants.MOD_ID + "-config.toml");
        ModConfigs.loadConfig(ModConfigs.SPEC, FMLPaths.CONFIGDIR.get().resolve(Constants.MOD_ID + "-config.toml"));

        ModTab.CREATIVE_MODE_TAB.register(eventBus);
        ModItemRegistry.ITEMS.register(eventBus);
    }
}