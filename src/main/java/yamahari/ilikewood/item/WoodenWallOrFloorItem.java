package yamahari.ilikewood.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IWorldReader;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Arrays;
import java.util.Map;

public class WoodenWallOrFloorItem extends WoodenBlockItem {
    private final Block wallBlock;

    public WoodenWallOrFloorItem(final WoodenObjectType objectType, final Block floorBlock, final Block wallBlock, final Item.Properties properties) {
        super(objectType, floorBlock, properties);
        this.wallBlock = wallBlock;
    }

    @Override
    protected BlockState getStateForPlacement(@SuppressWarnings("NullableProblems") final BlockItemUseContext context) {
        final BlockState state = this.wallBlock.getStateForPlacement(context);
        final IWorldReader world = context.getWorld();
        final BlockPos pos = context.getPos();

        return Arrays.stream(context.getNearestLookingDirections())
                .map(direction -> direction == Direction.DOWN ? this.getBlock().getStateForPlacement(context) : state)
                .filter(s -> s != null && s.isValidPosition(world, pos))
                .findFirst()
                .filter(s -> world.placedBlockCollides(s, pos, ISelectionContext.dummy()))
                .orElse(null);
    }

    @Override
    public void addToBlockToItemMap(@SuppressWarnings("NullableProblems") final Map<Block, Item> blockToItemMap, @SuppressWarnings("NullableProblems") final Item item) {
        super.addToBlockToItemMap(blockToItemMap, item);
        blockToItemMap.put(this.wallBlock, item);
    }

    @Override
    public void removeFromBlockToItemMap(@SuppressWarnings("NullableProblems") final Map<Block, Item> blockToItemMap, @SuppressWarnings("NullableProblems") final Item item) {
        super.removeFromBlockToItemMap(blockToItemMap, item);
        blockToItemMap.remove(this.wallBlock);
    }
}
