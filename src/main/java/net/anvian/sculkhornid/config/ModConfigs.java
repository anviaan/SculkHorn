package net.anvian.sculkhornid.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class ModConfigs {
    public static ForgeConfigSpec SERVER_CONFIG;

    public static ForgeConfigSpec.DoubleValue DISTANCE_DAMAGE; //8;
    public static ForgeConfigSpec.IntValue DISTANCE_DISTANCE; //16;
    public static ForgeConfigSpec.IntValue DISTANCE_COOLDOWN; //200;
    public static ForgeConfigSpec.IntValue DISTANCE_EXPERIENCE_LEVEL; //5;
    public static ForgeConfigSpec.IntValue DISTANCE_REMOVE_EXPERIENCE; //-55;
    public static ForgeConfigSpec.IntValue DISTANCE_DURABILITY; //500;
    public static ForgeConfigSpec.IntValue DISTANCE_USE_TIME;//10

    public static ForgeConfigSpec.DoubleValue AREA_DAMAGE; //12;
    public static ForgeConfigSpec.DoubleValue AREA_RADIUS; //3.5;
    public static ForgeConfigSpec.IntValue AREA_COOLDOWN; //300;
    public static ForgeConfigSpec.IntValue AREA_EXPERIENCE_LEVEL; //5;
    public static ForgeConfigSpec.IntValue AREA_REMOVE_EXPERIENCE; //-55;
    public static ForgeConfigSpec.IntValue AREA_DURABILITY; //350;
    public static ForgeConfigSpec.BooleanValue AREA_SPEED; //true;
    public static ForgeConfigSpec.IntValue AREA_SPEED_DURATION; //30;
    public static ForgeConfigSpec.IntValue AREA_SPEED_AMPLIFIER; //0;

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("SculkHornMod");

        DISTANCE_DAMAGE = builder.defineInRange("distance_DAMAGE", 8.0, 1.0, 10000.0);
        DISTANCE_DISTANCE = builder.defineInRange("distance_DISTANCE", 16, 0, 10000);
        DISTANCE_COOLDOWN = builder.defineInRange("distance_COOLDOWN", 200, 0, 10000);
        DISTANCE_EXPERIENCE_LEVEL = builder.defineInRange("distance_EXPERIENCE_LEVEL", 5, 0, 10000);
        DISTANCE_REMOVE_EXPERIENCE = builder.defineInRange("distance_REMOVE_EXPERIENCE", -55, -10000, 10000);
        DISTANCE_DURABILITY = builder.defineInRange("distance_DURABILITY", 500, 0, 10000);
        DISTANCE_USE_TIME = builder.defineInRange("distance_USE_TIME", 10,1, 10000);

        AREA_DAMAGE = builder.defineInRange("area_Damage", 12.0, 1.0, 10000.0);
        AREA_RADIUS = builder.defineInRange("area_RADIUS", 3.5, 1.0, 10000.0);
        AREA_COOLDOWN = builder.defineInRange("area_cooldown", 300, 0, 10000);
        AREA_EXPERIENCE_LEVEL = builder.defineInRange("area_EXPERIENCE_LEVEL", 5, 0, 10000);
        AREA_REMOVE_EXPERIENCE = builder.defineInRange("area_REMOVE_EXPERIENCE", -55, -10000, 10000);
        AREA_DURABILITY = builder.defineInRange("area_DURABILITY", 350, 0, 10000);
        AREA_SPEED = builder.comment("Disable or enable the speed given to the player after using the Area Sculk Horn")
                .define("area_SPEED", true);
        AREA_SPEED_DURATION = builder.defineInRange("area_SPEED_DURATION", 30, 0, 10000);
        AREA_SPEED_AMPLIFIER = builder.defineInRange("area_SPEED_AMPLIFIER", 0, 0, 10000);

        builder.pop();

        SERVER_CONFIG = builder.build();
    }
    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig config = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        config.load();
        spec.setConfig(config);
    }
}
