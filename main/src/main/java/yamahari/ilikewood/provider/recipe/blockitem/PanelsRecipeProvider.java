package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class PanelsRecipeProvider extends AbstractBlockItemRecipeProvider {
    public PanelsRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.PANELS);
    }

    @Override
    protected void registerRecipes(
        @Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType,
        final Block block
    )
    {
        final ItemLike slab = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getSlab(woodType)
            .getResource());

        ShapedRecipeBuilder
            .shaped(block)
            .define('#', Objects.requireNonNull(slab))
            .pattern("#")
            .pattern("#")
            .unlockedBy("has_slab", has(slab))
            .group(String.format("%s:%s", Constants.MOD_ID, Constants.PANELS_PLURAL))
            .save(consumer, Objects.requireNonNull(block.getRegistryName()));
    }
}