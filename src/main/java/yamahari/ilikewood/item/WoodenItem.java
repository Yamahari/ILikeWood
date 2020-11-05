package yamahari.ilikewood.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodenObjectType;

public class WoodenItem extends Item implements IWooden {
    private final IWoodType type;
    private final WoodenObjectType objectType;

    public WoodenItem(final IWoodType type, final WoodenObjectType objectType, final Item.Properties properties) {
        super(properties);
        this.type = type;
        this.objectType = objectType;
    }

    public WoodenObjectType getObjectType() {
        return this.objectType;
    }

    @Override
    public IWoodType getWoodType() {
        return this.type;
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return this.getWoodType().getProperties(this.getObjectType()).getBurnTime();
    }
}
