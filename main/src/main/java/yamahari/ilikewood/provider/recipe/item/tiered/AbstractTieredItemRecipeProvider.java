package yamahari.ilikewood.provider.recipe.item.tiered;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodenItemTiers;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.function.Consumer;

public abstract class AbstractTieredItemRecipeProvider extends RecipeProvider {
    protected final WoodenTieredItemType tieredItemType;

    public AbstractTieredItemRecipeProvider(final DataGenerator generator, final WoodenTieredItemType tieredItemType) {
        super(generator);
        this.tieredItemType = tieredItemType;
    }

    protected static InventoryChangeTrigger.TriggerInstance has(final Ingredient ingredientIn) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(Arrays
            .stream(ingredientIn.getItems())
            .map(ItemStack::getItem)
            .toArray(Item[]::new));
    }

    @Override
    protected void buildCraftingRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
        ILikeWood.TIERED_ITEM_REGISTRY
            .getObjects(this.tieredItemType)
            .filter(tieredItem -> !((IWoodenTieredItem) tieredItem)
                .getWoodenItemTier()
                .equals(VanillaWoodenItemTiers.NETHERITE))
            .forEach(tieredItem -> this.registerRecipes(consumer, ((IWooden) tieredItem).getWoodType(), tieredItem));
    }

    @Nonnull
    @Override
    public final String getName() {
        return String.format("%s - tiered item recipes - %s", Constants.MOD_ID, tieredItemType.getName());
    }

    protected abstract void registerRecipes(@Nonnull Consumer<FinishedRecipe> consumer, IWoodType woodType,
                                            Item tieredItem);
}
