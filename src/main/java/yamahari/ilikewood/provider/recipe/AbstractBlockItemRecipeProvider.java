package yamahari.ilikewood.provider.recipe;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.SingleItemRecipeBuilder;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class AbstractBlockItemRecipeProvider extends RecipeProvider {
    private final WoodenObjectType objectType;
    private final Predicate<IWoodType> predicate;

    public AbstractBlockItemRecipeProvider(final DataGenerator generator, final WoodenObjectType objectType) {
        this(generator, objectType, Util.HAS_PLANKS);
    }

    public AbstractBlockItemRecipeProvider(final DataGenerator generator, final WoodenObjectType objectType,
                                           final Predicate<IWoodType> predicate) {
        super(generator);
        this.objectType = objectType;
        this.predicate = predicate;
    }

    protected static SingleItemRecipeBuilder sawmillingRecipe(final Ingredient ingredient, final IItemProvider result) {
        return sawmillingRecipe(ingredient, result, 1);
    }

    protected static SingleItemRecipeBuilder sawmillingRecipe(final Ingredient ingredient, final IItemProvider result,
                                                              int count) {
        return new SingleItemRecipeBuilder(WoodenRecipeSerializers.SAWMILLING.get(), ingredient, result, count);
    }

    @Override
    protected final void registerRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        if (this.objectType == WoodenObjectType.BED) {
            Util.getBedBlocksWith(this.predicate).forEach(block -> this.registerRecipe(block, consumer));
        } else {
            Util.getBlocksWith(this.objectType, this.predicate).forEach(block -> this.registerRecipe(block, consumer));
        }
    }

    @Nonnull
    @Override
    public final String getName() {
        return String.format("%s - block item recipes - %s", Constants.MOD_ID, objectType.toString());
    }

    protected abstract void registerRecipe(Block block, @Nonnull Consumer<IFinishedRecipe> consumer);
}
