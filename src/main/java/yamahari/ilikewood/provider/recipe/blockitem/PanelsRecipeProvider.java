package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class PanelsRecipeProvider extends AbstractBlockItemRecipeProvider {
    public PanelsRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.PANELS);
    }

    @Override
    protected void registerRecipes(@Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType,
                                   final Block block) {
        RecipeBuilder builder;

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasPlanks(woodType)) {
            final ItemLike planks =
                Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY
                    .getPlanks(woodType)
                    .getResource()));

            builder = sawmillingRecipe(Ingredient.of(planks), block).unlockedBy("has_planks", has(planks));

            ConditionalRecipe
                .builder()
                .addCondition(new ModLoadedCondition(woodType.getModId()))
                .addRecipe(builder::save)
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(Objects.requireNonNull(block.getRegistryName()).getPath(),
                            "from",
                            Objects.requireNonNull(planks.asItem().getRegistryName()).getPath(),
                            Objects
                                .requireNonNull(WoodenRecipeSerializers.SAWMILLING.get().getRegistryName())
                                .getPath())));
        }

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasSlab(woodType)) {
            final ItemLike slab =
                ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getSlab(woodType).getResource());

            builder = ShapedRecipeBuilder
                .shaped(block)
                .define('#', Objects.requireNonNull(slab))
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_slab", has(slab))
                .group(ILikeWoodBlockTags.PANELS.getName().getPath());

            ConditionalRecipe
                .builder()
                .addCondition(new ModLoadedCondition(woodType.getModId()))
                .addRecipe(builder::save)
                .build(consumer, Objects.requireNonNull(block.getRegistryName()));
        }

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasLog(woodType)) {
            final ItemLike log =
                Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY
                    .getLog(woodType)
                    .getResource()));

            builder = sawmillingRecipe(Ingredient.of(log), block, 4).unlockedBy("has_log", has(log));

            ConditionalRecipe
                .builder()
                .addCondition(new ModLoadedCondition(woodType.getModId()))
                .addRecipe(builder::save)
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(Objects.requireNonNull(block.getRegistryName()).getPath(),
                            "from",
                            Objects.requireNonNull(log.asItem().getRegistryName()).getPath(),
                            Objects
                                .requireNonNull(WoodenRecipeSerializers.SAWMILLING.get().getRegistryName())
                                .getPath())));
        }

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasStrippedLog(woodType)) {
            final ItemLike stripped_log =
                Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY
                    .getStrippedLog(woodType)
                    .getResource()));

            builder = sawmillingRecipe(Ingredient.of(stripped_log), block, 4).unlockedBy("has_stripped_log",
                has(stripped_log));

            ConditionalRecipe
                .builder()
                .addCondition(new ModLoadedCondition(woodType.getModId()))
                .addRecipe(builder::save)
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(Objects.requireNonNull(block.getRegistryName()).getPath(),
                            "from",
                            Objects.requireNonNull(stripped_log.asItem().getRegistryName()).getPath(),
                            Objects
                                .requireNonNull(WoodenRecipeSerializers.SAWMILLING.get().getRegistryName())
                                .getPath())));
        }
    }
}
