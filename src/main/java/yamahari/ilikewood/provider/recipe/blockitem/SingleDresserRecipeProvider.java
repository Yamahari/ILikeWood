package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class SingleDresserRecipeProvider extends AbstractBlockItemRecipeProvider {
    public SingleDresserRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.SINGLE_DRESSER);
    }

    @Override
    protected void registerRecipes(final @Nonnull Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Block block) {
        final ItemLike strippedLog = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType).getResource());

        final ItemLike log = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType).getResource());

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block, 3)
            .define('#', Objects.requireNonNull(log))
            .define('X', Objects.requireNonNull(strippedLog))
            .pattern("###")
            .pattern("XXX")
            .pattern("XXX")
            .group(String.format("%s:%s", Constants.MOD_ID, Constants.SINGLE_DRESSER_PLURAL))
            .unlockedBy("has_stripped_log", has(strippedLog))
            .save(consumer, Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));
    }
}