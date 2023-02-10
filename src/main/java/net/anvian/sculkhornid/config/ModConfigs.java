package net.anvian.sculkhornid.config;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Sync;

@Config(name = "sculk-config", wrapperName = "SculkConfig")
@Sync(Option.SyncMode.OVERRIDE_CLIENT)
public class ModConfigs{
    //sculk horn area
    public double AREA_RADIUS = 3.5;
    public int AREA_COOLDOWN = 300;
    public double AREA_DAMAGE_EASY = 8;
    public double AREA_DAMAGE_NORMAL = 15;
    public double AREA_DAMAGE_HARD = 22.5;
    public int AREA_EXPERIENCE_LEVEL = 5;
    public int AREA_REMOVE_EXPERIENCE = -55;
    public int AREA_DURABILITY = 350;

    //sculk horn distance
    public double DISTANCE_DAMAGE_EASY = 4;
    public double DISTANCE_DAMAGE_NORMAL = 7.5;
    public double DISTANCE_DAMAGE_HARD = 11.25;
    public int DISTANCE_COOLDOWN = 200;
    public int DISTANCE_EXPERIENCE_LEVEL = 5;
    public int DISTANCE_REMOVE_EXPERIENCE = -55;
    public int DISTANCE_DISTANCE = 16;
    public int DISTANCE_DURABILITY = 500;
}
