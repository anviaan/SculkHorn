package net.anvian.sculkhornid.config;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.SectionHeader;
import io.wispforest.owo.config.annotation.Sync;
import net.anvian.sculkhornid.SculkHornMod;

@Config(name = "sculk-config", wrapperName = "SculkConfig")
@Modmenu(modId = SculkHornMod.MOD_ID)
@Sync(Option.SyncMode.OVERRIDE_CLIENT)
public class ModConfigs{
    //sculk horn area
    @SectionHeader("area")
    public double AREA_RADIUS = 3.5;
    public int AREA_COOLDOWN = 300;
    public double AREA_DAMAGE_EASY = 9;
    public double AREA_DAMAGE_NORMAL = 15;
    public double AREA_DAMAGE_HARD = 22.5;
    public int AREA_EXPERIENCE_LEVEL = 5;
    public int AREA_REMOVE_EXPERIENCE = -55;
    public int AREA_DURABILITY = 350;

    //sculk horn range
    @SectionHeader("range")
    public double RANGE_DAMAGE = 12;
    public int RANGE_COOLDOWN = 200;
    public int RANGE_EXPERIENCE_LEVEL = 5;
    public int RANGE_REMOVE_EXPERIENCE = -55;
    public int RANGE_DISTANCE = 16;
    public int RANGE_DURABILITY = 500;
}
