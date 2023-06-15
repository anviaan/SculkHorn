package net.anvian.sculkhornid;

import com.mojang.logging.LogUtils;
import net.anvian.sculkhornid.config.ModConfigs;
import net.anvian.sculkhornid.item.ItemGroup;
import net.anvian.sculkhornid.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.slf4j.Logger;

@Mod(SculkHornMod.MOD_ID)
public class SculkHornMod {
    public static final String MOD_ID = "sculkhornid";
    private static final Logger LOGGER = LogUtils.getLogger();
    public SculkHornMod(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //config
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ModConfigs.SERVER_CONFIG);
        ModConfigs.loadConfig(ModConfigs.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-config.toml"));


        ItemGroup.register(modEventBus);

        ModItems.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Hello from SculkHorn Mod");
    }
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == ItemGroup.SCULKHORN.get()) {
            event.accept(ModItems.SCULKHORN);
            event.accept(ModItems.SCULKHORN_SONICBOOM);
        }
    }
}
