package net.anvian.sculkhornid;

import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class ForgeMod {
    public ForgeMod() {
        Constants.LOG.info("Hello from " + Constants.MOD_ID + " (Forge)");
        CommonMod.init();
    }
}