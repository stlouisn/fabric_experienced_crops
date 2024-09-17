import dev.experiencedcrops.config.ConfigHandler;
import dev.experiencedcrops.utils.ModConstants;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TileEntity;
import net.neoforge.api.event.entity.player.PlayerInteractEvent;
import net.neoforge.api.event.registry.RegistryEvent;
import net.neoforge.api.registry.Registry;

@EventBusSubscriber
@Mod(ModConstants.MOD_ID)
public class BlockHarvestedEvent extends Mod {
  
    @SubscribeEvent
    public static void onBlockHarvested(PlayerInteractEvent.HarvestEvent event) {

        if (event.getPlayer().getGameMode().isCreative()) {
            return; // Return if player is in creative mode
        }

        BlockState state = event.getState();

        if (state.getBlock() == Blocks.PUMPKIN || state.getBlock() == Blocks.MELON) {
            ServerLevel world = event.getWorld();
            BlockEntity tileEntity = world.getBlockEntity(event.getPos());
            if (tileEntity instanceof TileEntity) {
                if (!hasDroppedXP(tileEntity)) {
                    world.addEntity(new ExperienceOrbEntity(world, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), ConfigHandler.experienceDropAmount.get()));
                    markAsDroppedXP(tileEntity);
                }
            }
        }
    }

    private static boolean hasDroppedXP(TileEntity tileEntity) {
        return tileEntity.getPersistentData().contains("hasDroppedXP");
    }
}

    private static void markAsDroppedXP(TileEntity tileEntity) {

        tileEntity.getPersistentData().putBoolean("hasDroppedXP", true);

        tileEntity.setChanged();

    }

}
