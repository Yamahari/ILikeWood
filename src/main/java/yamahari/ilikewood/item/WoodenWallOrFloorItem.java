package yamahari.ilikewood.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
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
    protected BlockState getPlacementState(@Nonnull final BlockPlaceContext context) {
        final BlockState state = this.wallBlock.getStateForPlacement(context);
        final LevelReader world = context.getLevel();
        final BlockPos pos = context.getClickedPos();

        return Arrays
            .stream(context.getNearestLookingDirections())
            .map(direction -> direction == Direction.DOWN ? this.getBlock().getStateForPlacement(context) : state)
            .filter(s -> s != null && s.canSurvive(world, pos))
            .findFirst()
            .filter(s -> world.isUnobstructed(s, pos, CollisionContext.empty()))
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
