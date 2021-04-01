package yamahari.ilikewood.container;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.util.IWorldPosCallable;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenContainerTypes;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

import javax.annotation.Nonnull;

public final class WoodenWorkBenchContainer extends WorkbenchContainer {
    public WoodenWorkBenchContainer(final int windowId, final PlayerInventory inventory) {
        this(windowId, inventory, IWorldPosCallable.DUMMY);
    }

    public WoodenWorkBenchContainer(final int windowId, final PlayerInventory inventory,
                                    final IWorldPosCallable callable) {
        super(windowId, inventory, callable);
    }

    @Override
    public boolean canInteractWith(@Nonnull final PlayerEntity player) {
        return this.worldPosCallable.applyOrElse((world, blockPos) -> {
            final BlockState blockState = world.getBlockState(blockPos);
            return WoodenBlocks.getBlocks(WoodenObjectTypes.CRAFTING_TABLE).anyMatch(blockState::isIn) &&
                   player.getDistanceSq((double) blockPos.getX() + 0.5D,
                       (double) blockPos.getY() + 0.5D,
                       (double) blockPos.getZ() + 0.5D) <= 64.0D;
        }, true);
    }

    @Override
    public ContainerType<?> getType() {
        return WoodenContainerTypes.WOODEN_WORK_BENCH.get();
    }
}
