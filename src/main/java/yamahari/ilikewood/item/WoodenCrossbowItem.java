package yamahari.ilikewood.item;

import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public final class WoodenCrossbowItem extends CrossbowItem implements IWooden {
    private final IWoodType woodType;

    public WoodenCrossbowItem(final IWoodType woodType) {
        super(new Item.Properties().maxStackSize(1).group(ItemGroup.COMBAT).maxDamage(326));
        this.woodType = woodType;
    }

    @Override
    public boolean isCrossbow(final ItemStack stack) {
        return stack.getItem() instanceof CrossbowItem;
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public int getBurnTime(final ItemStack itemStack) {
        return this.getWoodType().getProperties(WoodenItemType.CROSSBOW).getBurnTime();
    }
}
