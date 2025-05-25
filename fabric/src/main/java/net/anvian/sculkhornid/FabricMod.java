package net.anvian.sculkhornid;

import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.anvian.sculkhornid.core.ModTab;
import net.anvian.sculkhornid.core.config.ModConfigs;
import net.anvian.sculkhornid.core.registry.ModItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.neoforged.fml.config.ModConfig;

public class FabricMod implements ModInitializer {
    @Override
    public void onInitialize() {
        Constants.LOG.info("Hello from " + Constants.MOD_ID + " (Fabric)");
        CommonMod.init();

        Constants.LOG.info("Registering config for " + Constants.MOD_NAME + "...");
        ConfigRegistry.INSTANCE.register(Constants.MOD_ID, ModConfig.Type.SERVER, ModConfigs.SPEC, Constants.MOD_ID + "/" + Constants.MOD_ID + "-config.toml");
        ModConfigs.loadConfig(ModConfigs.SPEC, FabricLoader.getInstance().getConfigDir().resolve(Constants.MOD_ID).resolve(Constants.MOD_ID + "-config.toml"));

        Constants.LOG.info("Registering items for " + Constants.MOD_NAME + "...");
        ModItemRegistry.registerItems();

        Constants.LOG.info("Registering creative tab for " + Constants.MOD_ID);
        ModTab.registerTab();
    }
}
