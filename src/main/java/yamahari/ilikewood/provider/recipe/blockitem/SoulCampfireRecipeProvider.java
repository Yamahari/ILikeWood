package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class SoulCampfireRecipeProvider extends AbstractBlockItemRecipeProvider {
    public SoulCampfireRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.SOUL_CAMPFIRE);
    }

    @Override
    protected void registerRecipes(@Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Block block) {
        final ItemLike stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);
        final ItemLike logPile = ILikeWood.getBlock(woodType, WoodenBlockType.LOG_PILE);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
            .define('L', logPile)
            .define('S', stick)
            .define('C', ItemTags.SOUL_FIRE_BASE_BLOCKS)
            .pattern(" S ")
            .pattern("SCS")
            .pattern(" L ")
            .unlockedBy("has_stick", has(stick))
            .unlockedBy("has_soul_sand", has(ItemTags.SOUL_FIRE_BASE_BLOCKS))
            .save(consumer);
    }
}