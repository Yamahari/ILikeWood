package yamahari.ilikewood.item;

import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public final class WoodenBowItem extends BowItem implements IWooden {
    private final IWoodType woodType;

    public WoodenBowItem(final IWoodType woodType) {
        super(new Item.Properties().durability(384).tab(CreativeModeTab.TAB_COMBAT));
        this.woodType = woodType;
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public int getBurnTime(final ItemStack itemStack, @Nullable final RecipeType<?> recipeType) {
        return this.getWoodType().getProperties(WoodenItemType.BOW).burnTime();
    }
}
