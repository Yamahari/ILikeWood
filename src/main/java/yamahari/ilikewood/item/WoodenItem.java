package yamahari.ilikewood.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

public class WoodenItem extends Item implements IWooden {
    private final WoodType type;
    private final WoodenObjectType objectType;

    public WoodenItem(final WoodType type, final WoodenObjectType objectType, final Item.Properties properties) {
        super(properties);
        this.type = type;
        this.objectType = objectType;
    }

    public WoodenObjectType getObjectType() {
        return this.objectType;
    }

    @Override
    public WoodType getWoodType() {
        return this.type;
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return this.getWoodType().getProperties(this.getObjectType()).getBurnTime();
    }
}
