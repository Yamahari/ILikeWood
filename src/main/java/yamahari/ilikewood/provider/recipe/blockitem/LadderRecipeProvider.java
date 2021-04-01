package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class LadderRecipeProvider extends AbstractBlockItemRecipeProvider {
    public LadderRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenObjectTypes.LADDER);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final IItemProvider stick = WoodenItems.getItem(WoodenObjectTypes.STICK, woodType);

        ShapedRecipeBuilder
            .shapedRecipe(block, 3)
            .key('I', stick)
            .patternLine("I I")
            .patternLine("III")
            .patternLine("I I")
            .addCriterion("has_stick", hasItem(stick))
            .setGroup(ILikeWoodBlockTags.LADDERS.getName().getPath())
            .build(consumer);
    }
}
