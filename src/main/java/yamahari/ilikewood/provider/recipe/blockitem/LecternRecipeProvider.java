package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class LecternRecipeProvider extends AbstractBlockItemRecipeProvider {
    public LecternRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.LECTERN);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final IItemProvider slab = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS_SLAB);
        final IItemProvider bookshelf = ILikeWood.getBlock(woodType, WoodenBlockType.BOOKSHELF);

        ShapedRecipeBuilder
            .shaped(block)
            .define('S', slab)
            .define('B', bookshelf)
            .pattern("SSS")
            .pattern(" B ")
            .pattern(" S ")
            .unlockedBy("has_book", has(Items.BOOK))
            .group(ILikeWoodBlockTags.LECTERNS.getName().getPath())
            .save(consumer);
    }
}
