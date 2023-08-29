package yamahari.ilikewood.item;

import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public final class WoodenCrossbowItem extends CrossbowItem implements IWooden {
    private final IWoodType woodType;

    public WoodenCrossbowItem(final IWoodType woodType) {
        super(new Item.Properties().stacksTo(1).durability(326));
        this.woodType = woodType;
    }

    @Override
    public boolean useOnRelease(final ItemStack stack) {
        return stack.getItem() instanceof CrossbowItem;
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public int getBurnTime(final ItemStack itemStack, @Nullable final RecipeType<?> recipeType) {
        return this.getWoodType().getProperties(WoodenItemType.CROSSBOW).burnTime();
    }
}
