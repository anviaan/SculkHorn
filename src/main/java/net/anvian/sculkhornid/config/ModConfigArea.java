package net.anvian.sculkhornid.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.io.File;

public class ModConfigArea {
    public static ForgeConfigSpec.DoubleValue AREA_DAMAGE; //12;
    public static ForgeConfigSpec.DoubleValue AREA_RADIUS; //3.5;
    public static ForgeConfigSpec.IntValue AREA_COOLDOWN; //300;
    public static ForgeConfigSpec.IntValue AREA_EXPERIENCE_LEVEL; //5;
    public static ForgeConfigSpec.IntValue AREA_REMOVE_EXPERIENCE; //-55;
    public static ForgeConfigSpec.IntValue AREA_DURABILITY; //350;
    public static ForgeConfigSpec.BooleanValue AREA_SPEED; //true;
    public static ForgeConfigSpec.IntValue AREA_SPEED_DURATION; //30;
    public static ForgeConfigSpec.IntValue AREA_SPEED_AMPLIFIER; //0;

    private static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec config;

    static {
        ModConfigArea.init(builder);

        config = builder.build();
    }
    public static void init(ForgeConfigSpec.Builder config){
        config.push("Sculk Horn Mod");

        AREA_DAMAGE = config.defineInRange("area_Damage", 12.0, 1.0, 10000.0);
        AREA_RADIUS = config.defineInRange("area_RADIUS", 3.5, 1.0, 10000.0);
        AREA_COOLDOWN = config.defineInRange("area_cooldown", 300, 0, 10000);
        AREA_EXPERIENCE_LEVEL = config.defineInRange("area_EXPERIENCE_LEVEL", 5, 0, 10000);
        AREA_REMOVE_EXPERIENCE = config.defineInRange("area_REMOVE_EXPERIENCE", -55, -10000, 10000);
        AREA_DURABILITY = config.defineInRange("area_DURABILITY", 350, 0, 10000);
        AREA_SPEED = config.comment("Disable or enable the speed given to the player after using the Area Sculk Horn")
                .define("area_SPEED", true);
        AREA_SPEED_DURATION = config.defineInRange("area_SPEED_DURATION", 30, 0, 10000);
        AREA_SPEED_AMPLIFIER = config.defineInRange("area_SPEED_AMPLIFIER", 0, 0, 10000);
    }
    public static void loadConfig(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }
}
