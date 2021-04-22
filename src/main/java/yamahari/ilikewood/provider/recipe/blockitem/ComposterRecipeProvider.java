package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class ComposterRecipeProvider extends AbstractBlockItemRecipeProvider {
    public ComposterRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.COMPOSTER);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IItemProvider slab = ILikeWood.getBlock(((IWooden) block).getWoodType(), WoodenBlockType.PANELS_SLAB);
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
