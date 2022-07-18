package net.anvian.sculkhornid.config;

import com.mojang.datafixers.util.Pair;
import net.anvian.sculkhornid.SculkHornMod;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static double RADIUS;
    public static int COOLDOWN;
    public static double DAMAGE_EASY;
    public static double DAMAGE_NORMAL;
    public static double DAMAGE_HARD;

    public static int EXPERIENCE_LEVEL;
    public static int REMOVE_EXPERIENCE;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(SculkHornMod.MOD_ID + "config").provider(configs).request();//create config file

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("radius", 3.5),"double");
        configs.addKeyValuePair(new Pair<>("cooldown", 300),"int/ticks");
        configs.addKeyValuePair(new Pair<>("damage_easy", 9),"double");
        configs.addKeyValuePair(new Pair<>("damage_normal", 15),"double");
        configs.addKeyValuePair(new Pair<>("damage_hard", 22.5),"double");
        configs.addComment("Maximum experience to be able to use the SculkHorn");
        configs.addKeyValuePair(new Pair<>("experience_level",5),"int");
        configs.addComment("EXPERIENCE POINTS removed for using the SculkHorn");
        configs.addComment("Here you will be able to see how many experience points the levels are equivalent to:");
        configs.addComment("https://www.digminecraft.com/getting_started/experience.php");
        configs.addKeyValuePair(new Pair<>("remove_experience", -55),"int");
    }

    private static void assignConfigs() {
        RADIUS = CONFIG.getOrDefault("radius", 3.5);
        COOLDOWN = CONFIG.getOrDefault("cooldown", 300);
        DAMAGE_EASY = CONFIG.getOrDefault("damage_easy", 9);
        DAMAGE_NORMAL = CONFIG.getOrDefault("damage_normal", 15);
        DAMAGE_HARD = CONFIG.getOrDefault("damage_hard", 22.5);
        EXPERIENCE_LEVEL = CONFIG.getOrDefault("experience_level", 5);
        REMOVE_EXPERIENCE = CONFIG.getOrDefault("remove_experience", -55);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
