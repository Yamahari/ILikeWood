package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class ComposterRecipeProvider extends AbstractBlockItemRecipeProvider {
    public ComposterRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.COMPOSTER);
    }

    @Override
    protected void registerRecipes(
        @Nonnull final Consumer<FinishedRecipe> consumer,
        final IWoodType woodType,
        final Block block
    )
    {
        final ItemLike slab = ILikeWood.getBlock(((IWooden) block).getWoodType(), WoodenBlockType.PANELS_SLAB);

        ShapedRecipeBuilder
            .shaped(block)
            .define('#', slab)
            .pattern("# #")
            .pattern("# #")
            .pattern("###")
            .unlockedBy("has_panels", has(slab))
            .group(String.format("%s:%s", Constants.MOD_ID, Constants.COMPOSTER_PLURAL))
            .save(consumer, Objects.requireNonNull(block.getRegistryName()));
    }
}