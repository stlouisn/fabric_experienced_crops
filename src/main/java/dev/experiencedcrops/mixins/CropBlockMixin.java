package dev.experiencedcrops.mixins;

import dev.experiencedcrops.config.ModConfig;
import dev.experiencedcrops.config.ModConfigData;
import dev.experiencedcrops.utils.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin extends Block {

  public CropBlockMixin(Properties properties) {
    super(properties);
  }

  @Shadow
  public abstract boolean isMaxAge(BlockState state);

  @Override
  public @NotNull BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
    super.playerWillDestroy(level, pos, state, player);
    if (player.isCreative()) {
      return state;
    }
    if (state.is(ModTags.CROPS_WITHOUT_EXP)) {
      return state;
    }
    if (!this.isMaxAge(state)) {
      return state;
    }
    if (level instanceof ServerLevel serverLevel && level.random.nextIntBetweenInclusive(1, 100) <= ModConfig.CONFIG.experienceDropChance) {
      this.popExperience(serverLevel, pos, ModConfig.CONFIG.experienceDropAmount);
    }
    return state;
  }
}
