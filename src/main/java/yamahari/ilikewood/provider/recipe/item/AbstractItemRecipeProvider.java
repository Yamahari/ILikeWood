package yamahari.ilikewood.provider.recipe.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public abstract class AbstractItemRecipeProvider extends RecipeProvider {
    private final WoodenItemType itemType;

    public AbstractItemRecipeProvider(final DataGenerator generator, final WoodenItemType itemType) {
        super(generator.getPackOutput());
        this.itemType = itemType;
    }

    @Override
    protected final void buildRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
        ILikeWood.ITEM_REGISTRY
            .getObjects(this.itemType)
            .forEach(item -> this.registerRecipes(consumer, ((IWooden) item).getWoodType(), item));

    }

    @Nonnull
    @Override
    public final String getName() {
        return String.format("%s - item recipes - %s", Constants.MOD_ID, itemType.getName());
    }

    protected abstract void registerRecipes(@Nonnull Consumer<FinishedRecipe> consumer, IWoodType woodType, Item item);
}
