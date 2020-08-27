package yamahari.ilikewood.item;

import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

public final class WoodenBowItem extends BowItem implements IWooden {
    private final WoodType woodType;

    public WoodenBowItem(final WoodType woodType) {
        super(new Item.Properties().maxDamage(384).group(ItemGroup.COMBAT));
        this.woodType = woodType;
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public int getBurnTime(final ItemStack itemStack) {
        return this.getWoodType().getProperties(WoodenObjectType.BOW).getBurnTime();
    }
}
