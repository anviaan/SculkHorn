package net.anvian.sculkhornid.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfigs extends MidnightConfig {
    @Comment(centered = true) public static Comment warning;
    //sculk horn area
    @Entry public static double AREA_RADIUS = 3.5;
    @Entry public static int AREA_COOLDOWN = 300;
    @Entry public static double AREA_DAMAGE_EASY = 9;
    @Entry public static double AREA_DAMAGE_NORMAL = 15;
    @Entry public static double AREA_DAMAGE_HARD = 22.5;
    @Entry public static int AREA_EXPERIENCE_LEVEL = 5;
    @Entry public static int AREA_REMOVE_EXPERIENCE = -55;
    @Entry public static int AREA_DURABILITY = 350;

    //sculk horn range
    @Entry public static double RANGE_DAMAGE = 12;
    @Entry public static int RANGE_COOLDOWN = 200;
    @Entry public static int RANGE_EXPERIENCE_LEVEL = 5;
    @Entry public static int RANGE_REMOVE_EXPERIENCE = -55;
    @Entry public static int RANGE_DISTANCE = 16;
    @Entry public static int RANGE_DURABILITY = 500;
}
