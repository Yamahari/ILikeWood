package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.objecttype.WoodenBlockType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class BookshelfRecipeProvider extends AbstractBlockItemRecipeProvider {
    public BookshelfRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.BOOKSHELF);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IItemProvider panels = ILikeWood.getBlock(((IWooden) block).getWoodType(), WoodenBlockType.PANELS);

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
