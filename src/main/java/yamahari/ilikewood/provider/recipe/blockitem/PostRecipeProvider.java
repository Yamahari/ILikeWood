package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.provider.recipe.AbstractBlockItemRecipeProvider;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.WoodenObjectType;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class PostRecipeProvider extends AbstractBlockItemRecipeProvider {
    public PostRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenObjectType.POST, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG));
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();
        final IItemProvider log =
            ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType).getResource());
        final IItemProvider strippedLog =
            ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType).getResource());
        final Block strippedPost = WoodenBlocks.getBlock(WoodenObjectType.STRIPPED_POST, woodType);

        ShapedRecipeBuilder
            .shapedRecipe(block, 6)
            .key('#', Objects.requireNonNull(log))
            .patternLine("#")
            .patternLine("#")
            .patternLine("#")
            .addCriterion("has_log", hasItem(log))
            .setGroup(ILikeWoodBlockTags.POSTS.getName().getPath())
            .build(consumer);

        sawmillingRecipe(Ingredient.fromItems(log), block, 2)
            .addCriterion("has_log", hasItem(log))
            .build(consumer,
                new ResourceLocation(Constants.MOD_ID,
                    Util.toRegistryName(block.getRegistryName().getPath(),
                        "from",
                        log.asItem().getRegistryName().getPath(),
                        WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));

        sawmillingRecipe(Ingredient.fromItems(strippedLog), strippedPost, 2)
            .addCriterion("has_stripped_log", hasItem(strippedLog))
            .build(consumer,
                new ResourceLocation(Constants.MOD_ID,
                    Util.toRegistryName(strippedPost.getRegistryName().getPath(),
                        "from",
                        strippedLog.asItem().getRegistryName().getPath(),
                        WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
    }
}
