package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.provider.recipe.AbstractBlockItemRecipeProvider;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodenObjectType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class BookshelfRecipeProvider extends AbstractBlockItemRecipeProvider {
    public BookshelfRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenObjectType.BOOKSHELF);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, ((IWooden) block).getWoodType());

        ShapedRecipeBuilder
            .shapedRecipe(block)
            .key('#', panels)
            .key('X', Items.BOOK)
            .patternLine("###")
            .patternLine("XXX")
            .patternLine("###")
            .addCriterion("has_book", hasItem(Items.BOOK))
            .setGroup(ILikeWoodBlockTags.BOOKSHELFS.getName().getPath())
            .build(consumer);
    }
}
