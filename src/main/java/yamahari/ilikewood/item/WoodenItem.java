package yamahari.ilikewood.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.objecttype.WoodenItemType;

public class WoodenItem extends Item implements IWooden {
    private final IWoodType woodType;
    private final WoodenItemType itemType;

    public WoodenItem(final IWoodType woodType, final WoodenItemType itemType, final Properties properties) {
        super(properties);
        this.woodType = woodType;
        this.itemType = itemType;
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return this.getWoodType().getProperties(this.itemType).getBurnTime();
    }
}
