package yamahari.ilikewood.item;

import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public final class WoodenFishingRodItem extends FishingRodItem implements IWooden {
    private final IWoodType woodType;

    public WoodenFishingRodItem(final IWoodType woodType) {
        super((new Item.Properties()).durability(64).tab(ItemGroup.TAB_TOOLS));
        this.woodType = woodType;
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public int getBurnTime(final ItemStack itemStack) {
        return this.getWoodType().getProperties(WoodenItemType.FISHING_ROD).getBurnTime();
    }
}
