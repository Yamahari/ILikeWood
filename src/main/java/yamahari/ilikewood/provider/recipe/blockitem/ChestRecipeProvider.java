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

public final class ChestRecipeProvider extends AbstractBlockItemRecipeProvider {
    public ChestRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.CHEST);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IItemProvider panels = ILikeWood.getBlock(((IWooden) block).getWoodType(), WoodenBlockType.PANELS);

        ShapedRecipeBuilder
            .shaped(block)
            .define('#', panels)
            .pattern("###")
            .pattern("# #")
            .pattern("###")
            .unlockedBy("has_panels", has(panels))
            .group(ILikeWoodBlockTags.CHESTS.getName().getPath())
            .save(consumer);
    }
}
