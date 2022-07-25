package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class PanelsSlabRecipeProvider extends AbstractBlockItemRecipeProvider
{
    public PanelsSlabRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.PANELS_SLAB);
    }

    @Override
    protected void registerRecipes(
        @Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Block block
    )
    {
        final ItemLike panels = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS);
        ShapedRecipeBuilder.shaped(block, 6)
            .define('#', panels)
            .pattern("###")
            .unlockedBy("has_panels", has(panels))
            .group(String.format("%s:%s", Constants.MOD_ID, Constants.PANELS_SLAB_PLURAL))
            .save(consumer, Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)));

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasPlanks(woodType))
        {
            final ItemLike planks = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(
                woodType).getResource());

            ShapedRecipeBuilder.shaped(Objects.requireNonNull(planks))
                .define('S', block)
                .pattern("S")
                .pattern("S")
                .unlockedBy("has_panels_slab", has(block))
                .group(String.format("%s:planks_from_%s", Constants.MOD_ID, Constants.PANELS_SLAB_PLURAL))
                .save(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(planks.asItem()))
                                .getPath(),
                            "from",
                            ForgeRegistries.BLOCKS.getKey(block).getPath()
                        )
                    )
                );
        }
    }
}