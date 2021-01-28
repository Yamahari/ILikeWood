package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.provider.recipe.AbstractBlockItemRecipeProvider;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodenObjectType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class ChestRecipeProvider extends AbstractBlockItemRecipeProvider {
    public ChestRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenObjectType.CHEST);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, ((IWooden) block).getWoodType());

        ShapedRecipeBuilder
            .shapedRecipe(block)
            .key('#', panels)
            .patternLine("###")
            .patternLine("# #")
            .patternLine("###")
            .addCriterion("has_panels", hasItem(panels))
            .setGroup(ILikeWoodBlockTags.CHESTS.getName().getPath())
            .build(consumer);
    }
}
