package dev.experiencedcrops.config;

import dev.experiencedcrops.Constants;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.BoundedDiscrete;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Constants.MOD_ID)
public class ModConfig implements ConfigData {

  @BoundedDiscrete(min = 0, max = 100)
  @Tooltip
  public int experienceDropChance = 50;

  @Comment("Experience Amount")
  @Tooltip
  public int experienceDropAmount = 50;

  public static void init() {
    AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
  }

  public static ModConfig getInstance() {
    return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
  }
}