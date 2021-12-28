package yamahari.ilikewood.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public final class WoodenFishingRodItem extends FishingRodItem implements IWooden {
    private final IWoodType woodType;

    public WoodenFishingRodItem(final IWoodType woodType) {
        super((new Item.Properties()).durability(64).tab(CreativeModeTab.TAB_TOOLS));
        this.woodType = woodType;
    }

    @Override
    public IWoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public int getBurnTime(final ItemStack itemStack, @Nullable final RecipeType<?> recipeType) {
        return this.getWoodType().getProperties(WoodenItemType.FISHING_ROD).getBurnTime();
    }
}
