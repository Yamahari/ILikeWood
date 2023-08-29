package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class CrateRecipeProvider extends AbstractBlockItemRecipeProvider {
    public CrateRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.CRATE);
    }

    @Override
    protected void registerRecipes(@Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Block block) {
        final ItemLike stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);
        final ItemLike barrel = ILikeWood.getBlock(woodType, WoodenBlockType.BARREL);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
            .define('B', barrel)
            .define('S', stick)
            .pattern("SSS")
            .pattern("SBS")
            .pattern("SSS")
            .unlockedBy("has_stick", has(stick))
            .group(String.format("%s:%s", Constants.MOD_ID, Constants.CRATE_PLURAL))
            .save(consumer);
    }
}
