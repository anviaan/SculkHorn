package net.anvian.sculkhornid.core.config;

import net.anvian.anvianslib.config.Config;
import org.slf4j.Logger;

public class ModConfigs extends Config<ModConfigs.SculkHornConfig> {

    public ModConfigs(Class<SculkHornConfig> configClass, Logger logger) {
        super(configClass, logger);
    }

    @Override
    protected SculkHornConfig createDefaultConfig() {
        return new SculkHornConfig();
    }

    public static class SculkHornConfig {
        public double distanceDamage;
        public int distanceDistance;
        public double distanceCooldown;
        public int distanceExperienceLevel;
        public int distanceRemoveExperience;
        public int distanceDurability;
        public int distanceUseTime;

        public double areaDamage;
        public double areaRadius;
        public double areaCooldown;
        public int areaExperienceLevel;
        public int areaRemoveExperience;
        public int areaDurability;
        public int areaSpeedDuration;
        public int areaSpeedAmplifier;

        public boolean bothInCooldown;

        public SculkHornConfig(){
            distanceDamage = 8.0;
            distanceDistance = 16;
            distanceCooldown = 10.0;
            distanceExperienceLevel = 5;
            distanceRemoveExperience = -55;
            distanceDurability = 500;
            distanceUseTime = 10;

            areaDamage = 12.0;
            areaRadius = 3.5;
            areaCooldown = 15.0;
            areaExperienceLevel = 5;
            areaRemoveExperience = -55;
            areaDurability = 350;
            areaSpeedDuration = 30;
            areaSpeedAmplifier = 0;

            bothInCooldown = true;
        }
    }
}
