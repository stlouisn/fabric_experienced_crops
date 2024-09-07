package dev.experiencedcrops.mixins;

import static net.minecraft.world.level.block.NetherWartBlock.AGE;
import static net.minecraft.world.level.block.NetherWartBlock.MAX_AGE;

import dev.experiencedcrops.config.ModConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(NetherWartBlock.class)
public abstract class NetherWartBlockMixin extends Block {

  public NetherWartBlockMixin(Properties properties) {
    super(properties);
  }

  @Unique
  public int getMaxAge() {
    return MAX_AGE;
  }

  @Unique
  public int getAge(BlockState state) {
    return state.getValue(this.getAgeProperty());
  }

  @Unique
  protected IntegerProperty getAgeProperty() {
    return AGE;
  }

  @Unique
  public final boolean isMaxAge(BlockState state) {
    return this.getAge(state) >= this.getMaxAge();
  }

  @Override
  public @NotNull BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
    super.playerWillDestroy(level, pos, state, player);
    if (player.isCreative()) {
      return state;
    }
    if (!this.isMaxAge(state)) {
      return state;
    }
    if (level instanceof ServerLevel serverLevel && level.random.nextIntBetweenInclusive(1, 100) <= ModConfig.getInstance().experienceDropChance) {
      this.popExperience(serverLevel, pos, ModConfig.getInstance().experienceDropAmount);
    }
    return state;
  }
}
