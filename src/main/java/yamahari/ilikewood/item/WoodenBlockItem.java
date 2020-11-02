package yamahari.ilikewood.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import yamahari.ilikewood.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodenObjectType;

public class WoodenBlockItem extends BlockItem implements IWooden {
    private final WoodenObjectType objectType;

    public WoodenBlockItem(final WoodenObjectType objectType, final Block block, final Item.Properties properties) {
        super(block, properties);
        this.objectType = objectType;
    }

    public WoodenObjectType getObjectType() {
        return this.objectType;
    }

    @Override
    public IWoodType getWoodType() {
        assert this.getBlock() instanceof IWooden;
        return ((IWooden) this.getBlock()).getWoodType();
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return this.getWoodType().getProperties(this.getObjectType()).getBurnTime();
    }
}
