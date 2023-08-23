package com.ordana.experienced_crops.forge;

import com.ordana.experienced_crops.ExperiencedCrops;
import net.minecraftforge.fml.common.Mod;

@Mod(ExperiencedCrops.MOD_ID)
public class ExperiencedCropsForge {

    public ExperiencedCropsForge() {
        ExperiencedCrops.commonInit();
        /*
        if (PlatformHelper.getEnv().isClient()) {
            ModidClient.init();
        }
        */
    }
}

