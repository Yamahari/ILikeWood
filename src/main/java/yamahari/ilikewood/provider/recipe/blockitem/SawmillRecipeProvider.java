package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
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
    protected void registerRecipe(final Block block, @Nonnull final Consumer<FinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final ItemLike panels = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS);

        ShapedRecipeBuilder
            .shaped(block)
            .define('I', Items.IRON_INGOT)
            .define('#', panels)
            .pattern("II")
            .pattern("##")
            .pattern("##")
            .group(ILikeWoodBlockTags.SAWMILLS.getName().getPath())
            .unlockedBy("has_panels", has(panels))
            .save(consumer);
    }
}
