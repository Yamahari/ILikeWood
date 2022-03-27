package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public class LogPileRecipeProvider extends AbstractBlockItemRecipeProvider {
    public LogPileRecipeProvider(DataGenerator generator) {
        super(generator, WoodenBlockType.LOG_PILE);
    }

    @Override
    protected void registerRecipes(
        @Nonnull final Consumer<FinishedRecipe> consumer,
        final IWoodType woodType,
        final Block block
    ) {
        final ItemLike post = ILikeWood.getBlock(woodType, WoodenBlockType.POST);

        final RecipeBuilder builder = ShapedRecipeBuilder
            .shaped(block, 2)
            .define('I', post)
            .pattern("II")
            .pattern("II")
            .group(Constants.LOG_PILE_PLURAL)
            .unlockedBy("has_post", has(post));

        builder.save(consumer, Objects.requireNonNull(block.getRegistryName()));
    }
}