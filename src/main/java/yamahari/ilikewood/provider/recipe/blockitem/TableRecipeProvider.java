package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class TableRecipeProvider extends AbstractBlockItemRecipeProvider {
    public TableRecipeProvider(DataGenerator generator) {
        super(generator, WoodenBlockType.TABLE);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<FinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();

        final ItemLike log =
            ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType).getResource());
        final ItemLike strippedPost = ILikeWood.getBlock(woodType, WoodenBlockType.STRIPPED_POST);

        ShapedRecipeBuilder
            .shaped(block, 3)
            .define('#', Objects.requireNonNull(log))
            .define('I', strippedPost)
            .pattern("###")
            .pattern("I I")
            .pattern("I I")
            .group(ILikeWoodBlockTags.TABLES.getName().getPath())
            .unlockedBy("has_stripped_post", has(strippedPost))
            .save(consumer);
    }
}
