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

public final class SawmillRecipeProvider extends AbstractBlockItemRecipeProvider {
    public SawmillRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.SAWMILL);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final IItemProvider panels = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS);

        ShapedRecipeBuilder
            .shapedRecipe(block)
            .key('I', Items.IRON_INGOT)
            .key('#', panels)
            .patternLine("II")
            .patternLine("##")
            .patternLine("##")
            .setGroup(ILikeWoodBlockTags.SAWMILLS.getName().getPath())
            .addCriterion("has_panels", hasItem(panels))
            .build(consumer);
    }
}
