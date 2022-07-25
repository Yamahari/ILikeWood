package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
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

public final class LecternRecipeProvider extends AbstractBlockItemRecipeProvider
{
    public LecternRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.LECTERN);
    }

    @Override
    protected void registerRecipes(
        @Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Block block
    )
    {
        final ItemLike slab = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS_SLAB);
        final ItemLike bookshelf = ILikeWood.getBlock(woodType, WoodenBlockType.BOOKSHELF);

        ShapedRecipeBuilder.shaped(block)
            .define('S', slab)
            .define('B', bookshelf)
            .pattern("SSS")
            .pattern(" B ")
            .pattern(" S ")
            .unlockedBy("has_book", has(Items.BOOK))
            .group(String.format("%s:%s", Constants.MOD_ID, Constants.LECTERN_PLURAL))
            .save(consumer, Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));
    }
}