package yamahari.ilikewood.container;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.util.IWorldPosCallable;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.WoodenContainerTypes;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;

public final class WoodenWorkBenchContainer extends WorkbenchContainer {
    public WoodenWorkBenchContainer(final int windowId, final PlayerInventory inventory) {
        this(windowId, inventory, IWorldPosCallable.NULL);
    }

    public WoodenWorkBenchContainer(final int windowId, final PlayerInventory inventory,
                                    final IWorldPosCallable callable) {
        super(windowId, inventory, callable);
    }

    @Override
    public boolean stillValid(@Nonnull final PlayerEntity player) {
        return this.access.evaluate((world, blockPos) -> {
            final BlockState blockState = world.getBlockState(blockPos);
            return ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.CRAFTING_TABLE).anyMatch(blockState::is) &&
                   player.distanceToSqr((double) blockPos.getX() + 0.5D,
                       (double) blockPos.getY() + 0.5D,
                       (double) blockPos.getZ() + 0.5D) <= 64.0D;
        }, true);
    }

    @Override
    public ContainerType<?> getType() {
        return WoodenContainerTypes.WOODEN_WORK_BENCH.get();
    }
}
