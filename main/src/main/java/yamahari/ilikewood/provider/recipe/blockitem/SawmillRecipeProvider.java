package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
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

public final class SawmillRecipeProvider extends AbstractBlockItemRecipeProvider
{
    public SawmillRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.SAWMILL);
    }

    @Override
    protected void registerRecipes(
        @Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Block block
    )
    {
        final ItemLike panels = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS);

        ShapedRecipeBuilder.shaped(block)
            .define('I', Items.IRON_INGOT)
            .define('#', panels)
            .pattern("II")
            .pattern("##")
            .pattern("##")
            .group(String.format("%s:%s", Constants.MOD_ID, Constants.SAWMILL_PLURAL))
            .unlockedBy("has_panels", has(panels))
            .save(consumer, Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));
    }
}