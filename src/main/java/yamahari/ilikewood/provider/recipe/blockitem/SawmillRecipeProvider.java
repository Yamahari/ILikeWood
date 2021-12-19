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
