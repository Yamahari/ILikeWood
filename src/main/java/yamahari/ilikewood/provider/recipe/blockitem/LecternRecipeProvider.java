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
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodenObjectType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class LecternRecipeProvider extends AbstractBlockItemRecipeProvider {
    public LecternRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenObjectType.LECTERN);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final IItemProvider slab = WoodenBlocks.getBlock(WoodenObjectType.SLAB, woodType);
        final IItemProvider bookshelf = WoodenBlocks.getBlock(WoodenObjectType.BOOKSHELF, woodType);

        ShapedRecipeBuilder
            .shapedRecipe(block)
            .key('S', slab)
            .key('B', bookshelf)
            .patternLine("SSS")
            .patternLine(" B ")
            .patternLine(" S ")
            .addCriterion("has_book", hasItem(Items.BOOK))
            .setGroup(ILikeWoodBlockTags.LECTERNS.getName().getPath())
            .build(consumer);
    }
}
