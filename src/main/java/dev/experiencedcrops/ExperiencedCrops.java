package dev.experiencedcrops;

import dev.experiencedcrops.config.ModConfig;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class ExperiencedCrops implements ModInitializer {

  public static final Logger LOGGER = LoggerFactory.getLogger(Constants.MOD_ID);

  public void onInitialize() {
    ModConfig.init();
  }
}
