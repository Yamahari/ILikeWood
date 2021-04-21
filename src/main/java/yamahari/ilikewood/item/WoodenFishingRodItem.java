package yamahari.ilikewood.item;

import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.objecttype.WoodenItemType;

public final class WoodenFishingRodItem extends FishingRodItem implements IWooden {
    private final IWoodType woodType;

    public WoodenFishingRodItem(final IWoodType woodType) {
        super((new Item.Properties()).maxDamage(64).group(ItemGroup.TOOLS));
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
