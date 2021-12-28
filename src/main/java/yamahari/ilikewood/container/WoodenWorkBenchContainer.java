package yamahari.ilikewood.container;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.state.BlockState;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.WoodenContainerTypes;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;

public final class WoodenWorkBenchContainer extends CraftingMenu {
    public WoodenWorkBenchContainer(final int windowId, final Inventory inventory) {
        this(windowId, inventory, ContainerLevelAccess.NULL);
    }

    public WoodenWorkBenchContainer(final int windowId, final Inventory inventory,
                                    final ContainerLevelAccess callable) {
        super(windowId, inventory, callable);
    }

    @Override
    public boolean stillValid(@Nonnull final Player player) {
        return this.access.evaluate((world, blockPos) -> {
            final BlockState blockState = world.getBlockState(blockPos);
            return ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.CRAFTING_TABLE).anyMatch(blockState::is) &&
                   player.distanceToSqr((double) blockPos.getX() + 0.5D,
                       (double) blockPos.getY() + 0.5D,
                       (double) blockPos.getZ() + 0.5D) <= 64.0D;
        }, true);
    }

    @Override
    public MenuType<?> getType() {
        return WoodenContainerTypes.WOODEN_WORK_BENCH.get();
    }
}
