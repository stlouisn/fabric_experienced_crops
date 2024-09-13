package dev.experiencedcrops.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public final class ConfigHandler {

  public static final ModConfigSpec configSpec;

  public static ConfigValue<Integer> experienceDropChance, experienceDropAmount;

  static {

    ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

    experienceDropChance = builder
        .defineInRange("experienceDropChance", 50, 0, 100);

    experienceDropAmount = builder
        .define("experienceDropAmount", 1);

    configSpec = builder.build();

  }
}
