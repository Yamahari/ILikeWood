package yamahari.ilikewood.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

import javax.annotation.Nonnull;

public final class WoodenBedItem extends WoodenBlockItem {
    public WoodenBedItem(final Block block) {
        super(WoodenObjectTypes.BED, block, (new Item.Properties()).group(ItemGroup.DECORATIONS));
    }

    @Override
    protected boolean placeBlock(final BlockItemUseContext context, @Nonnull final BlockState state) {
        return context.getWorld().setBlockState(context.getPos(), state, 26);
    }
}
