package yamahari.ilikewood.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public class WoodenBlockItem extends BlockItem implements IWooden {
    private final WoodenBlockType blockType;

    public WoodenBlockItem(final WoodenBlockType blockType, final Block block, final Item.Properties properties) {
        super(block, properties);
        this.blockType = blockType;
    }

    @Override
    public IWoodType getWoodType() {
        assert this.getBlock() instanceof IWooden;
        return ((IWooden) this.getBlock()).getWoodType();
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return this.getWoodType().getProperties(this.blockType).getBurnTime();
    }
}
