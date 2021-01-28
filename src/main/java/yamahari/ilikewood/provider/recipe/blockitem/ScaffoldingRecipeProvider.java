package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.provider.recipe.AbstractBlockItemRecipeProvider;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodenObjectType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class ScaffoldingRecipeProvider extends AbstractBlockItemRecipeProvider {
    public ScaffoldingRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenObjectType.SCAFFOLDING);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final IItemProvider stick = WoodenItems.getItem(WoodenObjectType.STICK, woodType);

        ShapedRecipeBuilder
            .shapedRecipe(block)
            .key('I', stick)
            .key('~', Items.STRING)
            .patternLine("I~I")
            .patternLine("I I")
            .patternLine("I I")
            .addCriterion("has_stick", hasItem(stick))
            .setGroup(ILikeWoodBlockTags.SCAFFOLDINGS.getName().getPath())
            .build(consumer);
    }
}
