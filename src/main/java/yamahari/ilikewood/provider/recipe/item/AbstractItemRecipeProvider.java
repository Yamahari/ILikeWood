package yamahari.ilikewood.provider.recipe.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.SingleItemRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public abstract class AbstractItemRecipeProvider extends RecipeProvider {
    private final WoodenItemType itemType;

    public AbstractItemRecipeProvider(final DataGenerator generator, final WoodenItemType itemType) {
        super(generator);
        this.itemType = itemType;
    }

    protected static SingleItemRecipeBuilder sawmillingRecipe(final Ingredient ingredient, final IItemProvider result) {
        return sawmillingRecipe(ingredient, result, 1);
    }

    protected static SingleItemRecipeBuilder sawmillingRecipe(final Ingredient ingredient, final IItemProvider result,
                                                              int count) {
        return new SingleItemRecipeBuilder(WoodenRecipeSerializers.SAWMILLING.get(), ingredient, result, count);
    }

    @Override
    protected final void buildShapelessRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        ILikeWood.ITEM_REGISTRY.getObjects(this.itemType).forEach(item -> this.registerRecipe(item, consumer));

    }

    @Nonnull
    @Override
    public final String getName() {
        return String.format("%s - item recipes - %s", Constants.MOD_ID, itemType.getName());
    }

    protected abstract void registerRecipe(Item item, @Nonnull Consumer<IFinishedRecipe> consumer);
}
