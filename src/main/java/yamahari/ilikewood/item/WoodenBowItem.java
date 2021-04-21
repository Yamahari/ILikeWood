package yamahari.ilikewood.item;

import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public final class WoodenBowItem extends BowItem implements IWooden {
    private final IWoodType woodType;

    public WoodenBowItem(final IWoodType woodType) {
        super(new Item.Properties().maxDamage(384).group(ItemGroup.COMBAT));
        this.woodType = woodType;
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public int getBurnTime(final ItemStack itemStack) {
        return this.getWoodType().getProperties(WoodenItemType.BOW).getBurnTime();
    }
}
