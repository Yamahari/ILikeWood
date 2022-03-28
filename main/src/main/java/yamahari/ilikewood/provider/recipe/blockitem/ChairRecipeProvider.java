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

public final class ChairRecipeProvider extends AbstractBlockItemRecipeProvider {
    public ChairRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.CHAIR);
    }

    @Override
    protected void registerRecipes(
        final @Nonnull Consumer<FinishedRecipe> consumer,
        final IWoodType woodType,
        final Block block
    )
    {
        final ItemLike strippedPost = ILikeWood.getBlock(woodType, WoodenBlockType.STRIPPED_POST);
        final ItemLike strippedLog =
            ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType).getResource());

        ShapedRecipeBuilder
            .shaped(block, 3)
            .define('#', Objects.requireNonNull(strippedLog))
            .define('I', strippedPost)
            .pattern("I I")
            .pattern("I#I")
            .pattern("I I")
            .group(String.format("%s:%s", Constants.MOD_ID, Constants.CHAIR_PLURAL))
            .unlockedBy("has_stripped_post", has(strippedPost))
            .save(consumer, Objects.requireNonNull(block.getRegistryName()));
    }
}