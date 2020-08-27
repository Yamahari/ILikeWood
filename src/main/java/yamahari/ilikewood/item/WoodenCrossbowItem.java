package yamahari.ilikewood.item;

import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

public class WoodenCrossbowItem extends CrossbowItem implements IWooden {
    private final WoodType woodType;

    public WoodenCrossbowItem(final WoodType woodType) {
        super(new Item.Properties().maxStackSize(1).group(ItemGroup.COMBAT).maxDamage(326));
        this.woodType = woodType;
    }

    @Override
    public boolean isCrossbow(final ItemStack stack) {
        return stack.getItem() instanceof CrossbowItem;
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public int getBurnTime(final ItemStack itemStack) {
        return this.getWoodType().getProperties(WoodenObjectType.CROSSBOW).getBurnTime();
    }
}
