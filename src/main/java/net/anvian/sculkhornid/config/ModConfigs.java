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
    }

    private static void assignConfigs() {
        RADIUS = CONFIG.getOrDefault("radius", 3.5);
        COOLDOWN = CONFIG.getOrDefault("cooldown", 300);
        DAMAGE_EASY = CONFIG.getOrDefault("damage_easy", 9);
        DAMAGE_NORMAL = CONFIG.getOrDefault("damage_normal", 15);
        DAMAGE_HARD = CONFIG.getOrDefault("damage_hard", 22.5);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
