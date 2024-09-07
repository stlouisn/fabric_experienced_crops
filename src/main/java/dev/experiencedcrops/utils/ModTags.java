package dev.experiencedcrops.utils;

import dev.experiencedcrops.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {

  public static final TagKey<Block> CROPS_WITHOUT_EXP = registerBlockTag("crops_without_exp");

  public static TagKey<Block> registerBlockTag(String path) {
    return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, path));
  }
}
