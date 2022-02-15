package yamahari.ilikewood.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;

public final class WoodenBedItem extends WoodenBlockItem {
    public WoodenBedItem(final WoodenBlockType bedBlockType, final Block block) {
        super(bedBlockType, block, (new Item.Properties()).tab(CreativeModeTab.TAB_DECORATIONS));
    }

    @Override
    protected boolean placeBlock(final BlockPlaceContext context, @Nonnull final BlockState state) {
        return context
            .getLevel()
            .setBlock(context.getClickedPos(),
                state,
                Block.UPDATE_KNOWN_SHAPE | Block.UPDATE_IMMEDIATE | Block.UPDATE_CLIENTS);
    }
}
