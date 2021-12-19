package yamahari.ilikewood.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IWorldReader;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Map;

public class WoodenWallOrFloorItem extends WoodenBlockItem {
    private final Block wallBlock;

    public WoodenWallOrFloorItem(final WoodenBlockType blockType, final Block floorBlock, final Block wallBlock,
                                 final Item.Properties properties) {
        super(blockType, floorBlock, properties);
        this.wallBlock = wallBlock;
    }

    @Override
    protected BlockState getPlacementState(@Nonnull final BlockItemUseContext context) {
        final BlockState state = this.wallBlock.getStateForPlacement(context);
        final IWorldReader world = context.getLevel();
        final BlockPos pos = context.getClickedPos();

        return Arrays
            .stream(context.getNearestLookingDirections())
            .map(direction -> direction == Direction.DOWN ? this.getBlock().getStateForPlacement(context) : state)
            .filter(s -> s != null && s.canSurvive(world, pos))
            .findFirst()
            .filter(s -> world.isUnobstructed(s, pos, ISelectionContext.empty()))
            .orElse(null);
    }

    @Override
    public void registerBlocks(@Nonnull final Map<Block, Item> blockToItemMap, @Nonnull final Item item) {
        super.registerBlocks(blockToItemMap, item);
        blockToItemMap.put(this.wallBlock, item);
    }

    @Override
    public void removeFromBlockToItemMap(@Nonnull final Map<Block, Item> blockToItemMap, @Nonnull final Item item) {
        super.removeFromBlockToItemMap(blockToItemMap, item);
        blockToItemMap.remove(this.wallBlock);
    }
}
