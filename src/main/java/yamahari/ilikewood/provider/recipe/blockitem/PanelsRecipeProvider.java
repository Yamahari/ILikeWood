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
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class PanelsRecipeProvider extends AbstractBlockItemRecipeProvider {
    public PanelsRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.PANELS);
    }

    @Override
    protected void registerRecipe(final Block block, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) block).getWoodType();

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasPlanks(woodType)) {
            final IItemProvider planks =
                ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getResource());

            sawmillingRecipe(Ingredient.fromItems(planks), block)
                .addCriterion("has_planks", hasItem(planks))
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(block.getRegistryName().getPath(),
                            "from",
                            planks.asItem().getRegistryName().getPath(),
                            WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
        }

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasSlab(woodType)) {
            final IItemProvider slab =
                ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getSlab(woodType).getResource());

            ShapedRecipeBuilder
                .shapedRecipe(block)
                .key('#', Objects.requireNonNull(slab))
                .patternLine("#")
                .patternLine("#")
                .addCriterion("has_slab", hasItem(slab))
                .setGroup(ILikeWoodBlockTags.PANELS.getName().getPath())
                .build(consumer);
        }

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasLog(woodType)) {
            final IItemProvider log =
                ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType).getResource());

            sawmillingRecipe(Ingredient.fromItems(log), block, 4)
                .addCriterion("has_log", hasItem(log))
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(block.getRegistryName().getPath(),
                            "from",
                            log.asItem().getRegistryName().getPath(),
                            WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
        }

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasStrippedLog(woodType)) {
            final IItemProvider stripped_log = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY
                .getStrippedLog(woodType)
                .getResource());

            sawmillingRecipe(Ingredient.fromItems(stripped_log), block, 4)
                .addCriterion("has_stripped_log", hasItem(stripped_log))
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(block.getRegistryName().getPath(),
                            "from",
                            stripped_log.asItem().getRegistryName().getPath(),
                            WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
        }
    }
}
