package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class CraftingTableRecipeProvider extends AbstractBlockItemRecipeProvider {
    public CraftingTableRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.CRAFTING_TABLE);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final IItemProvider panels = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS);

        ShapedRecipeBuilder
            .shaped(block)
            .define('#', panels)
            .pattern("##")
            .pattern("##")
            .unlockedBy("has_panels", has(panels))
            .group(ILikeWoodBlockTags.CRAFTING_TABLES.getName().getPath())
            .save(consumer);
    }
}
