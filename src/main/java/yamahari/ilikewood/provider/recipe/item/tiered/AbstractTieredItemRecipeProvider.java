package yamahari.ilikewood.provider.recipe.item.tiered;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodenItemTiers;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectType;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.function.Consumer;

public abstract class AbstractTieredItemRecipeProvider extends RecipeProvider {
    protected final WoodenTieredObjectType tieredObjectType;

    public AbstractTieredItemRecipeProvider(final DataGenerator generator,
                                            final WoodenTieredObjectType tieredObjectType) {
        super(generator);
        this.tieredObjectType = tieredObjectType;
    }

    protected static InventoryChangeTrigger.Instance hasItem(final Ingredient ingredientIn) {
        return InventoryChangeTrigger.Instance.forItems(Arrays
            .stream(ingredientIn.getMatchingStacks())
            .map(ItemStack::getItem)
            .toArray(Item[]::new));
    }

    @Override
    protected void registerRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        WoodenItems
            .getTieredItems(this.tieredObjectType)
            .filter(item -> !((IWoodenTieredItem) item).getWoodenItemTier().equals(VanillaWoodenItemTiers.NETHERITE))
            .forEach(item -> this.registerRecipe(item, consumer));
    }

    @Nonnull
    @Override
    public final String getName() {
        return String.format("%s - tiered item recipes - %s", Constants.MOD_ID, tieredObjectType.getName());
    }

    protected abstract void registerRecipe(Item item, @Nonnull Consumer<IFinishedRecipe> consumer);
}
