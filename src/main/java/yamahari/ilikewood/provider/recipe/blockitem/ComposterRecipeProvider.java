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

public final class ComposterRecipeProvider extends AbstractBlockItemRecipeProvider {
    public ComposterRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.COMPOSTER);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<FinishedRecipe> consumer) {
        final ItemLike slab = ILikeWood.getBlock(((IWooden) block).getWoodType(), WoodenBlockType.PANELS_SLAB);
        ShapedRecipeBuilder
            .shaped(block)
            .define('#', slab)
            .pattern("# #")
            .pattern("# #")
            .pattern("###")
            .unlockedBy("has_panels", has(slab))
            .group(ILikeWoodBlockTags.COMPOSTER.getName().getPath())
            .save(consumer);
    }
}
