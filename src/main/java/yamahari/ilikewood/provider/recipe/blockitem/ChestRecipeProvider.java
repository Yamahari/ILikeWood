package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
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
    protected void registerRecipe(final Block block, @Nonnull final Consumer<FinishedRecipe> consumer) {
        final ItemLike panels = ILikeWood.getBlock(((IWooden) block).getWoodType(), WoodenBlockType.PANELS);

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
