package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.block.Block;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public abstract class AbstractBlockItemRecipeProvider extends RecipeProvider {
    private final WoodenBlockType blockType;

    public AbstractBlockItemRecipeProvider(final DataGenerator generator, final WoodenBlockType blockType) {
        super(generator.getPackOutput());
        this.blockType = blockType;
    }

    @Override
    protected final void buildRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
        if (this.blockType == WoodenBlockType.WHITE_BED) {
            ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.getBeds()).forEach(block -> this.registerRecipes(consumer, ((IWooden) block).getWoodType(), block));
        }
        else {
            ILikeWood.BLOCK_REGISTRY.getObjects(this.blockType).forEach(block -> this.registerRecipes(consumer, ((IWooden) block).getWoodType(), block));
        }
    }

    @Nonnull
    @Override
    public final String getName() {
        return String.format("%s - block item recipes - %s", Constants.MOD_ID, blockType.getName());
    }

    protected abstract void registerRecipes(@Nonnull Consumer<FinishedRecipe> consumer, IWoodType woodType, Block block);
}
