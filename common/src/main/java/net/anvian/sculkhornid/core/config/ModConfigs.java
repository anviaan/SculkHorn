package net.anvian.sculkhornid.core.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class ModConfigs {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.DoubleValue DISTANCE_DAMAGE = BUILDER.defineInRange("distance_DAMAGE", 8.0, 1.0, 10000.0);
    private static final ForgeConfigSpec.IntValue DISTANCE_DISTANCE = BUILDER.defineInRange("distance_DISTANCE", 16, 0, 10000);
    private static final ForgeConfigSpec.DoubleValue DISTANCE_COOLDOWN = BUILDER.defineInRange("distance_COOLDOWN", 10.0, 0, 10000);
    private static final ForgeConfigSpec.IntValue DISTANCE_EXPERIENCE_LEVEL = BUILDER.defineInRange("distance_EXPERIENCE_LEVEL", 5, 0, 10000);
    private static final ForgeConfigSpec.IntValue DISTANCE_REMOVE_EXPERIENCE = BUILDER.defineInRange("distance_REMOVE_EXPERIENCE", -55, -10000, 10000);
    private static final ForgeConfigSpec.IntValue DISTANCE_DURABILITY = BUILDER.defineInRange("distance_DURABILITY", 500, 0, 10000);
    private static final ForgeConfigSpec.IntValue DISTANCE_USE_TIME = BUILDER.defineInRange("distance_USE_TIME", 10, 1, 10000);

    private static final ForgeConfigSpec.DoubleValue AREA_DAMAGE = BUILDER.defineInRange("area_Damage", 12.0, 1.0, 10000.0);
    private static final ForgeConfigSpec.DoubleValue AREA_RADIUS = BUILDER.defineInRange("area_RADIUS", 3.5, 1.0, 10000.0);
    private static final ForgeConfigSpec.DoubleValue AREA_COOLDOWN = BUILDER.defineInRange("area_COOLDOWN", 15.0, 0, 10000);
    private static final ForgeConfigSpec.IntValue AREA_EXPERIENCE_LEVEL = BUILDER.defineInRange("area_EXPERIENCE_LEVEL", 5, 0, 10000);
    private static final ForgeConfigSpec.IntValue AREA_REMOVE_EXPERIENCE = BUILDER.defineInRange("area_REMOVE_EXPERIENCE", -55, -10000, 10000);
    private static final ForgeConfigSpec.IntValue AREA_DURABILITY = BUILDER.defineInRange("area_DURABILITY", 350, 0, 10000);
    private static final ForgeConfigSpec.IntValue AREA_SPEED_DURATION = BUILDER.defineInRange("area_SPEED_DURATION", 30, 0, 10000);
    private static final ForgeConfigSpec.IntValue AREA_SPEED_AMPLIFIER = BUILDER.defineInRange("area_SPEED_AMPLIFIER", 0, 0, 10000);

    private static final ForgeConfigSpec.BooleanValue BOTH_IN_COOLDOWN = BUILDER.define("both_in_cooldown", true);

    public static double distanceDamage;
    public static int distanceDistance;
    public static double distanceCooldown;
    public static int distanceExperienceLevel;
    public static int distanceRemoveExperience;
    public static int distanceDurability;
    public static int distanceUseTime;

    public static double areaDamage;
    public static double areaRadius;
    public static double areaCooldown;
    public static int areaExperienceLevel;
    public static int areaRemoveExperience;
    public static int areaDurability;
    public static int areaSpeedDuration;
    public static int areaSpeedAmplifier;

    public static boolean bothInCooldown;

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        spec.setConfig(configData);

        distanceDamage = DISTANCE_DAMAGE.get();
        distanceDistance = DISTANCE_DISTANCE.get();
        distanceCooldown = DISTANCE_COOLDOWN.get();
        distanceExperienceLevel = DISTANCE_EXPERIENCE_LEVEL.get();
        distanceRemoveExperience = DISTANCE_REMOVE_EXPERIENCE.get();
        distanceDurability = DISTANCE_DURABILITY.get();
        distanceUseTime = DISTANCE_USE_TIME.get();

        areaDamage = AREA_DAMAGE.get();
        areaRadius = AREA_RADIUS.get();
        areaCooldown = AREA_COOLDOWN.get();
        areaExperienceLevel = AREA_EXPERIENCE_LEVEL.get();
        areaRemoveExperience = AREA_REMOVE_EXPERIENCE.get();
        areaDurability = AREA_DURABILITY.get();
        areaSpeedDuration = AREA_SPEED_DURATION.get();
        areaSpeedAmplifier = AREA_SPEED_AMPLIFIER.get();

        bothInCooldown = BOTH_IN_COOLDOWN.get();
    }
}
