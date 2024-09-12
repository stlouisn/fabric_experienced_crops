package dev.experiencedcrops.config;

import dev.experiencedcrops.utils.ModConstants;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.BoundedDiscrete;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;

@Config(name = ModConstants.MOD_ID)
public class ModConfigData implements ConfigData {

  @BoundedDiscrete(min = 0, max = 100)
  @Tooltip
  public int experienceDropChance = 50;

  @Tooltip
  public int experienceDropAmount = 1;
  
}
