package yamahari.ilikewood.provider.recipe.item.tiered;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodenItemTiers;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.function.Consumer;

public abstract class AbstractTieredItemRecipeProvider extends RecipeProvider {
    protected final WoodenTieredItemType tieredItemType;

    public AbstractTieredItemRecipeProvider(final DataGenerator generator, final WoodenTieredItemType tieredItemType) {
        super(generator);
        this.tieredItemType = tieredItemType;
    }

    protected static InventoryChangeTrigger.Instance has(final Ingredient ingredientIn) {
        return InventoryChangeTrigger.Instance.hasItems(Arrays
            .stream(ingredientIn.getItems())
            .map(ItemStack::getItem)
            .toArray(Item[]::new));
    }

    @Override
    protected void buildShapelessRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        ILikeWood.TIERED_ITEM_REGISTRY
            .getObjects(this.tieredItemType)
            .filter(tieredItem -> !((IWoodenTieredItem) tieredItem)
                .getWoodenItemTier()
                .equals(VanillaWoodenItemTiers.NETHERITE))
            .forEach(tieredItem -> this.registerRecipe(tieredItem, consumer));
    }

    @Nonnull
    @Override
    public final String getName() {
        return String.format("%s - tiered item recipes - %s", Constants.MOD_ID, tieredItemType.getName());
    }

    protected abstract void registerRecipe(Item tieredItem, @Nonnull Consumer<IFinishedRecipe> consumer);
}
