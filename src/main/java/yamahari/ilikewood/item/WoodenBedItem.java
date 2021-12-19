package yamahari.ilikewood.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.util.Constants;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;

public final class WoodenBedItem extends WoodenBlockItem {
    public WoodenBedItem(final WoodenBlockType bedBlockType, final Block block) {
        super(bedBlockType, block, (new Item.Properties()).tab(ItemGroup.TAB_DECORATIONS));
    }

    @Override
    protected boolean placeBlock(final BlockItemUseContext context, @Nonnull final BlockState state) {
        return context
            .getLevel()
            .setBlock(context.getClickedPos(),
                state,
                Constants.BlockFlags.UPDATE_NEIGHBORS | Constants.BlockFlags.RERENDER_MAIN_THREAD |
                Constants.BlockFlags.BLOCK_UPDATE);
    }
}
