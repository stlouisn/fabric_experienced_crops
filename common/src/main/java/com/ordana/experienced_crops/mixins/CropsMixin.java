package com.ordana.experienced_crops.mixins;

import com.ordana.experienced_crops.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CropBlock.class)
public abstract class CropsMixin extends Block {

    public CropsMixin(Properties properties) {
        super(properties);
    }

    @Shadow public abstract boolean isMaxAge(BlockState state);


    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        super.playerWillDestroy(level, pos, state, player);
        if (!this.isMaxAge(state) || player.isCreative() || state.is(ModTags.CROPS_WITHOUT_EXP)) return;
        if (level instanceof ServerLevel serverLevel)
            this.popExperience(serverLevel, pos, level.random.nextIntBetweenInclusive(4, 7));
    }

}
