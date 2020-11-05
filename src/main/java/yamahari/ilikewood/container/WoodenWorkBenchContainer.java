package yamahari.ilikewood.container;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.LazyValue;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenContainerTypes;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodenObjectType;

public final class WoodenWorkBenchContainer extends WorkbenchContainer implements IWooden {
    private final IWoodType woodType;
    private final LazyValue<Block> targetBlock;
    private final LazyValue<ContainerType<?>> containerType;

    public WoodenWorkBenchContainer(final IWoodType woodType, final int windowId, final PlayerInventory inventory) {
        this(woodType, windowId, inventory, IWorldPosCallable.DUMMY);
    }

    public WoodenWorkBenchContainer(final IWoodType woodType, final int windowId, final PlayerInventory inventory, final IWorldPosCallable callable) {
        super(windowId, inventory, callable);
        this.woodType = woodType;
        this.targetBlock = new LazyValue<>(WoodenBlocks.getRegistryObject(WoodenObjectType.CRAFTING_TABLE, this.getWoodType()));
        this.containerType = new LazyValue<>(WoodenContainerTypes.getRegistryObject(WoodenObjectType.CRAFTING_TABLE, this.getWoodType()));
    }

    @Override
    public boolean canInteractWith(@SuppressWarnings("NullableProblems") final PlayerEntity player) {
        return isWithinUsableDistance(this.worldPosCallable, player, this.targetBlock.getValue());
    }

    @Override
    public ContainerType<?> getType() {
        return this.containerType.getValue();
    }


    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }
}
