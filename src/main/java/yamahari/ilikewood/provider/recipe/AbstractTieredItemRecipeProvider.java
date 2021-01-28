package yamahari.ilikewood.provider.recipe;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodenItemTiers;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenTieredObjectType;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class AbstractTieredItemRecipeProvider extends RecipeProvider {
    protected final WoodenTieredObjectType tieredObjectType;
    protected final Predicate<IWoodType> predicate;

    public AbstractTieredItemRecipeProvider(final DataGenerator generator,
                                            final WoodenTieredObjectType tieredObjectType) {
        this(generator, tieredObjectType, Util.HAS_PLANKS);
    }

    public AbstractTieredItemRecipeProvider(final DataGenerator generator,
                                            final WoodenTieredObjectType tieredObjectType,
                                            final Predicate<IWoodType> predicate) {
        super(generator);
        this.tieredObjectType = tieredObjectType;
        this.predicate = predicate;
    }

    protected static InventoryChangeTrigger.Instance hasItem(final Ingredient ingredientIn) {
        return InventoryChangeTrigger.Instance.forItems(Arrays
            .stream(ingredientIn.getMatchingStacks())
            .map(ItemStack::getItem)
            .toArray(Item[]::new));
    }

    @Override
    protected void registerRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        Util
            .getTieredItemsWith(this.tieredObjectType, this.predicate)
            .filter(item -> !((IWoodenTieredItem) item).getWoodenItemTier().equals(VanillaWoodenItemTiers.NETHERITE))
            .forEach(item -> this.registerRecipe(item, consumer));
    }

    @Nonnull
    @Override
    public final String getName() {
        return String.format("%s - tiered item recipes - %s", Constants.MOD_ID, tieredObjectType.toString());
    }

    protected abstract void registerRecipe(Item item, @Nonnull Consumer<IFinishedRecipe> consumer);
}
