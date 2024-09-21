package dev.experiencedcrops.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public final class ConfigHandler {

  public static final ModConfigSpec configSpec;

  public static ConfigValue<Integer> experienceDropChance, experienceDropAmount;

  static {

    ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

    experienceDropChance = builder
        .comment("The chance that breaking crops will drop experience.\nDefault: 50")
        .defineInRange("experienceDropChance", 50, 0, 100);

    experienceDropAmount = builder
        .comment("The amount of experience dropped when breaking crops.\nDefault: 1")
        .defineInRange("experienceDropAmount", 1,1,10);

    configSpec = builder.build();

  }
}
