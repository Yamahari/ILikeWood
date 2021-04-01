package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class ComposterRecipeProvider extends AbstractBlockItemRecipeProvider {
    public ComposterRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenObjectTypes.COMPOSTER);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IItemProvider slab = WoodenBlocks.getBlock(WoodenObjectTypes.SLAB, ((IWooden) block).getWoodType());
        ShapedRecipeBuilder
            .shapedRecipe(block)
            .key('#', slab)
            .patternLine("# #")
            .patternLine("# #")
            .patternLine("###")
            .addCriterion("has_panels", hasItem(slab))
            .setGroup(ILikeWoodBlockTags.COMPOSTER.getName().getPath())
            .build(consumer);
    }
}
