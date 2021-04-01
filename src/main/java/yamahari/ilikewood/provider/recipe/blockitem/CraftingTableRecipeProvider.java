package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class CraftingTableRecipeProvider extends AbstractBlockItemRecipeProvider {
    public CraftingTableRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenObjectTypes.CRAFTING_TABLE);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectTypes.PANELS, woodType);

        ShapedRecipeBuilder
            .shapedRecipe(block)
            .key('#', panels)
            .patternLine("##")
            .patternLine("##")
            .addCriterion("has_panels", hasItem(panels))
            .setGroup(ILikeWoodBlockTags.CRAFTING_TABLES.getName().getPath())
            .build(consumer);
    }
}
