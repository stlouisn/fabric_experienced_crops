package dev.experiencedcrops.mixins;

import static net.minecraft.world.level.block.CocoaBlock.AGE;
import static net.minecraft.world.level.block.CocoaBlock.MAX_AGE;

import dev.experiencedcrops.config.ConfigHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(CocoaBlock.class)
public abstract class CocoaBlockMixin extends Block {

  public CocoaBlockMixin(Properties properties) {
    super(properties);
  }

  @Unique
  public int experiencedcrops$getMaxAge() {
    return MAX_AGE;
  }

  @Unique
  public int experiencedcrops$getAge(BlockState state) {
    return state.getValue(this.experiencedcrops$getAgeProperty());
  }

  @Unique
  protected IntegerProperty experiencedcrops$getAgeProperty() {
    return AGE;
  }

  @Unique
  public final boolean experiencedcrops$isMaxAge(BlockState state) {
    return this.experiencedcrops$getAge(state) >= this.experiencedcrops$getMaxAge();
  }

  @Override
  public @NotNull BlockState playerWillDestroy(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Player player) {
    super.playerWillDestroy(level, pos, state, player);
    if (player.isCreative()) {
      return state;
    }
    if (!this.experiencedcrops$isMaxAge(state)) {
      return state;
    }
    if (level instanceof ServerLevel serverLevel && level.random.nextIntBetweenInclusive(1, 100) <= ConfigHandler.experienceDropChance.get()) {
      this.popExperience(serverLevel, pos, ConfigHandler.experienceDropAmount.get());
    }
    return state;
  }
}
