package net.anvian.sculkhornid;

import net.anvian.sculkhornid.core.ModTab;
import net.anvian.sculkhornid.core.config.ModConfigs;
import net.anvian.sculkhornid.core.registry.ModItemRegistry;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(Constants.MOD_ID)
public class ForgeMod {
    public ForgeMod(FMLJavaModLoadingContext context) {
        Constants.LOG.info("Hello from " + Constants.MOD_ID + " (Forge)");
        CommonMod.init();

        var modBusGroup = context.getModBusGroup();

        Constants.LOG.info("Registering config for " + Constants.MOD_NAME + "...");
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ModConfigs.SPEC, Constants.MOD_ID + "/" + Constants.MOD_ID + "-config.toml");
        ModConfigs.loadConfig(ModConfigs.SPEC, FMLPaths.CONFIGDIR.get().resolve(Constants.MOD_ID).resolve(Constants.MOD_ID + "-config.toml"));

        Constants.LOG.info("Registering creative tab for " + Constants.MOD_ID);
        ModTab.CREATIVE_MODE_TAB.register(modBusGroup);

        Constants.LOG.info("Registering items for " + Constants.MOD_NAME + "...");
        ModItemRegistry.ITEMS.register(modBusGroup);
    }
}