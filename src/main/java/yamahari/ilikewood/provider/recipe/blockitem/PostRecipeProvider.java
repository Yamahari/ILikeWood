package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class PostRecipeProvider extends AbstractBlockItemRecipeProvider {
    public PostRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.POST);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<FinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasLog(woodType)) {
            final ItemLike log =
                ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType).getResource());

            ShapedRecipeBuilder.shaped(block, 6).define('#', Objects.requireNonNull(log)).pattern("#")
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_log", has(log))
                .group(ILikeWoodBlockTags.POSTS.getName().getPath())
                .save(consumer);

            sawmillingRecipe(Ingredient.of(log), block, 2).unlockedBy("has_log", has(log))
                .save(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(block.getRegistryName().getPath(),
                            "from",
                            log.asItem().getRegistryName().getPath(),
                            WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));

            if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasStrippedLog(woodType)) {
                final ItemLike strippedLog = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY
                    .getStrippedLog(woodType)
                    .getResource());

                try {
                    final Block strippedPost = ILikeWood.getBlock(woodType, WoodenBlockType.STRIPPED_POST);

                    sawmillingRecipe(Ingredient.of(strippedLog), strippedPost, 2)
                        .unlockedBy("has_stripped_log", has(strippedLog))
                        .save(consumer,
                            new ResourceLocation(Constants.MOD_ID,
                                Util.toRegistryName(strippedPost.getRegistryName().getPath(),
                                    "from",
                                    strippedLog.asItem().getRegistryName().getPath(),
                                    WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
                } catch (final IllegalArgumentException ignored) {
                    ILikeWood.LOGGER.info("No stripped post was found for post<->stripped sawmilling recipe!");
                }
            }
        }
    }
}
