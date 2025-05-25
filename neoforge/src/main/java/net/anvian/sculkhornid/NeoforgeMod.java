package net.anvian.sculkhornid;

import fuzs.forgeconfigapiport.neoforge.api.v5.ForgeConfigRegistry;
import net.anvian.sculkhornid.core.ModTab;
import net.anvian.sculkhornid.core.config.ModConfigs;
import net.anvian.sculkhornid.core.registry.ModItemRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLPaths;

@Mod(Constants.MOD_ID)
public class NeoforgeMod {

    public NeoforgeMod(IEventBus eventBus) {
        Constants.LOG.info("Hello from " + Constants.MOD_ID + " (Neoforge)");
        CommonMod.init();

        Constants.LOG.info("Registering config for " + Constants.MOD_NAME + "...");
        ForgeConfigRegistry.INSTANCE.register(Constants.MOD_ID, ModConfig.Type.SERVER, ModConfigs.SPEC, Constants.MOD_ID + "/" + Constants.MOD_ID + "-config.toml");
        ModConfigs.loadConfig(ModConfigs.SPEC, FMLPaths.CONFIGDIR.get().resolve(Constants.MOD_ID).resolve(Constants.MOD_ID + "-config.toml"));

        Constants.LOG.info("Registering items for " + Constants.MOD_NAME + "...");
        ModItemRegistry.ITEMS.register(eventBus);

        Constants.LOG.info("Registering creative tab for " + Constants.MOD_ID);
        ModTab.TABS.register(eventBus);
    }
}