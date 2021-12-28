package yamahari.ilikewood.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

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
    public int getBurnTime(final ItemStack itemStack, @Nullable final RecipeType<?> recipeType) {
        return this.getWoodType().getProperties(this.itemType).getBurnTime();
    }
}
