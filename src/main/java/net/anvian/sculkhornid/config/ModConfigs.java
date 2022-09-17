package net.anvian.sculkhornid.config;

import com.mojang.datafixers.util.Pair;
import net.anvian.sculkhornid.SculkHornMod;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;
    //sculk horn area
    public static double AREA_RADIUS;
    public static int AREA_COOLDOWN;
    public static double AREA_DAMAGE_EASY;
    public static double AREA_DAMAGE_NORMAL;
    public static double AREA_DAMAGE_HARD;
    public static int AREA_EXPERIENCE_LEVEL;
    public static int AREA_REMOVE_EXPERIENCE;
    public static double RANGE_DAMAGE;
    public static int RANGE_EXPERIENCE_LEVEL;
    public static int RANGE_REMOVE_EXPERIENCE;
    public static int RANGE_DISTANCE;
    public static int AREA_DURABILITY;
    public static int RANGE_DURABILITY;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(SculkHornMod.MOD_ID + "config").provider(configs).request();//create config file

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("area_radius", 3.5),"double");
        configs.addKeyValuePair(new Pair<>("area_cooldown", 300),"int/ticks");
        configs.addKeyValuePair(new Pair<>("area_damage_easy", 9),"double");
        configs.addKeyValuePair(new Pair<>("area_damage_normal", 15),"double");
        configs.addKeyValuePair(new Pair<>("area_damage_hard", 22.5),"double");
        configs.addKeyValuePair(new Pair<>("area_experience_level_required",5),"int");
        removeExperience();
        configs.addKeyValuePair(new Pair<>("area_remove_experience", -55),"int");
        configs.addKeyValuePair(new Pair<>("range_damage", 12.0),"double");
        configs.addKeyValuePair(new Pair<>("range_experience_level_required",5),"int");
        removeExperience();
        configs.addKeyValuePair(new Pair<>("range_remove_experience",-55),"int");
        configs.addKeyValuePair(new Pair<>("range_distance",16),"int");
        configs.addKeyValuePair(new Pair<>("area_durability",350),"int");
        configs.addKeyValuePair(new Pair<>("range_durability",350),"int");

    }

    private static void assignConfigs() {
        AREA_RADIUS = CONFIG.getOrDefault("area_radius", 3.5);
        AREA_COOLDOWN = CONFIG.getOrDefault("area_cooldown", 300);
        AREA_DAMAGE_EASY = CONFIG.getOrDefault("area_damage_easy", 9);
        AREA_DAMAGE_NORMAL = CONFIG.getOrDefault("area_damage_normal", 15);
        AREA_DAMAGE_HARD = CONFIG.getOrDefault("area_damage_hard", 22.5);
        AREA_EXPERIENCE_LEVEL = CONFIG.getOrDefault("area_experience_level", 5);
        AREA_REMOVE_EXPERIENCE = CONFIG.getOrDefault("area_remove_experience", -55);
        RANGE_DAMAGE = CONFIG.getOrDefault("range_damage", 12.0);
        RANGE_EXPERIENCE_LEVEL = CONFIG.getOrDefault("range_experience_level", 5);
        RANGE_REMOVE_EXPERIENCE = CONFIG.getOrDefault("range_remove_experience", -55);
        RANGE_DISTANCE = CONFIG.getOrDefault("range_distance", 16);
        AREA_DURABILITY = CONFIG.getOrDefault("area_durability",350);
        RANGE_DURABILITY = CONFIG.getOrDefault("range_durability",500);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }

    private static void removeExperience(){
        configs.addComment("EXPERIENCE POINTS removed for using the SculkHorn");
        configs.addComment("Here you will be able to see how many experience points the levels are equivalent to:");
        configs.addComment("https://www.digminecraft.com/getting_started/experience.php");
        configs.addComment("The minus sign must be present");
    }
}
