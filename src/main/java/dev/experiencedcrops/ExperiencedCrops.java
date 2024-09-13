package dev.experiencedcrops;

import dev.experiencedcrops.config.ConfigHandler;
import dev.experiencedcrops.utils.ModConstants;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@Mod(ModConstants.MOD_ID)
public final class ExperiencedCrops {

  public static final Logger LOGGER = LoggerFactory.getLogger(ModConstants.MOD_ID);

  public ExperiencedCrops(ModContainer container) {
    container.registerConfig(Type.COMMON, ConfigHandler.configSpec);
  }
}
