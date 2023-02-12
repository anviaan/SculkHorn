package net.anvian.sculkhornid.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.io.File;

public class ModConfigDistance {
    public static ForgeConfigSpec.DoubleValue DISTANCE_DAMAGE; //8;
    public static ForgeConfigSpec.IntValue DISTANCE_DISTANCE; //16;
    public static ForgeConfigSpec.IntValue DISTANCE_COOLDOWN; //200;
    public static ForgeConfigSpec.IntValue DISTANCE_EXPERIENCE_LEVEL; //5;
    public static ForgeConfigSpec.IntValue DISTANCE_REMOVE_EXPERIENCE; //-55;
    public static ForgeConfigSpec.IntValue DISTANCE_DURABILITY; //500;

    private static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec config;

    static {
        ModConfigDistance.init(builder);

        config = builder.build();
    }
    public static void init(ForgeConfigSpec.Builder config){
        config.push("Sculk Horn Mod");

        DISTANCE_DAMAGE = config.defineInRange("distance_DAMAGE", 8.0, 1.0, 10000.0);
        DISTANCE_DISTANCE = config.defineInRange("distance_DISTANCE", 16, 0, 10000);
        DISTANCE_COOLDOWN = config.defineInRange("distance_COOLDOWN", 200, 0, 10000);
        DISTANCE_EXPERIENCE_LEVEL = config.defineInRange("distance_EXPERIENCE_LEVEL", 5, 0, 10000);
        DISTANCE_REMOVE_EXPERIENCE = config.defineInRange("distance_REMOVE_EXPERIENCE", -55, -10000, 10000);
        DISTANCE_DURABILITY = config.defineInRange("distance_DURABILITY", 500, 0, 10000);
    }
    public static void loadConfig(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }
}
