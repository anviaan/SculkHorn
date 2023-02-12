package net.anvian.sculkhornid.config;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.loading.FileUtils;

public class ModConfigs {
    public static void  registerConfig(){
        String CONFIGDIRNAME= "SculkHornMod";
        //create config folder
        FileUtils.getOrCreateDirectory(FMLPaths.CONFIGDIR.get().resolve(CONFIGDIRNAME), CONFIGDIRNAME);

        //area
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigArea.config, CONFIGDIRNAME + "/sculkhorn-defaulconfig-area.toml");
        ModConfigArea.loadConfig(ModConfigArea.config, FMLPaths.CONFIGDIR.get().resolve(CONFIGDIRNAME + "/sculkhorn-config-area.toml").toString());

        //distance
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigDistance.config, CONFIGDIRNAME + "/sculkhorn-defaulconfig-distance.toml");
        ModConfigDistance.loadConfig(ModConfigDistance.config, FMLPaths.CONFIGDIR.get().resolve(CONFIGDIRNAME + "/sculkhorn-config-distance.toml").toString());
    }
}
