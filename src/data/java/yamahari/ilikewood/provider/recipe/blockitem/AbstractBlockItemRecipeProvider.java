package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.SingleItemRecipeBuilder;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public abstract class AbstractBlockItemRecipeProvider extends RecipeProvider {
    private final WoodenBlockType blockType;

    public AbstractBlockItemRecipeProvider(final DataGenerator generator, final WoodenBlockType blockType) {
        super(generator);
        this.blockType = blockType;
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
        if (this.blockType == WoodenBlockType.WHITE_BED) {
            ILikeWood.BLOCK_REGISTRY
                .getObjects(WoodenBlockType.getBeds())
                .forEach(block -> this.registerRecipe(block, consumer));
        } else {
            ILikeWood.BLOCK_REGISTRY.getObjects(this.blockType).forEach(block -> this.registerRecipe(block, consumer));
        }
    }

    @Nonnull
    @Override
    public final String getName() {
        return String.format("%s - block item recipes - %s", Constants.MOD_ID, blockType.getName());
    }

    protected abstract void registerRecipe(Block block, @Nonnull Consumer<IFinishedRecipe> consumer);
}
