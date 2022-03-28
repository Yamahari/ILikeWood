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

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class BarrelRecipeProvider extends AbstractBlockItemRecipeProvider {
    public BarrelRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.BARREL);
    }

    @Override
    protected void registerRecipes(
        @Nonnull final Consumer<FinishedRecipe> consumer,
        final IWoodType woodType,
        final Block block
    )
    {
        final ItemLike panels = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS);
        final ItemLike slab = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS_SLAB);

        ShapedRecipeBuilder
            .shaped(block)
            .define('P', panels)
            .define('S', slab)
            .pattern("PSP")
            .pattern("P P")
            .pattern("PSP")
            .unlockedBy("has_panels", has(panels))
            .group(String.format("%s:%s", Constants.MOD_ID, Constants.BARREL_PLURAL))
            .save(consumer, Objects.requireNonNull(block.getRegistryName()));
    }
}