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
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class ScaffoldingRecipeProvider extends AbstractBlockItemRecipeProvider {
    public ScaffoldingRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.SCAFFOLDING);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final IItemProvider stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);

        ShapedRecipeBuilder
            .shaped(block)
            .define('I', stick)
            .define('~', Items.STRING)
            .pattern("I~I")
            .pattern("I I")
            .pattern("I I")
            .unlockedBy("has_stick", has(stick))
            .group(ILikeWoodBlockTags.SCAFFOLDINGS.getName().getPath())
            .save(consumer);
    }
}
