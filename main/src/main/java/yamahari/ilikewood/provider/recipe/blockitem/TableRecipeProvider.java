package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
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

public final class TableRecipeProvider extends AbstractBlockItemRecipeProvider
{
    public TableRecipeProvider(DataGenerator generator) {
        super(generator, WoodenBlockType.TABLE);
    }

    @Override
    protected void registerRecipes(
        @Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Block block
    )
    {
        final ItemLike log =
            Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType)
                .getResource()));
        final ItemLike strippedPost = ILikeWood.getBlock(woodType, WoodenBlockType.STRIPPED_POST);

        ShapedRecipeBuilder.shaped(block, 3)
            .define('#', Objects.requireNonNull(log))
            .define('I', strippedPost)
            .pattern("###")
            .pattern("I I")
            .pattern("I I")
            .group(String.format("%s:%s", Constants.MOD_ID, Constants.TABLE_PLURAL))
            .unlockedBy("has_stripped_post", has(strippedPost))
            .save(consumer, Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));
    }
}