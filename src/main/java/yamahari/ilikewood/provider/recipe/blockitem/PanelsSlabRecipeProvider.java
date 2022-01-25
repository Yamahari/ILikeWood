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

public final class PanelsSlabRecipeProvider extends AbstractBlockItemRecipeProvider {
    public PanelsSlabRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.PANELS_SLAB);
    }

    @Override
    protected void registerRecipes(@Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType,
                                   final Block block) {
        final ItemLike panels = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS);

        RecipeBuilder builder = ShapedRecipeBuilder
            .shaped(block, 6)
            .define('#', panels)
            .pattern("###")
            .unlockedBy("has_panels", has(panels))
            .group(ILikeWoodBlockTags.PANELS_SLABS.getName().getPath());

        ConditionalRecipe
            .builder()
            .addCondition(new ModLoadedCondition(woodType.getModId()))
            .addRecipe(builder::save)
            .build(consumer, Objects.requireNonNull(block.getRegistryName()));

        builder = sawmillingRecipe(Ingredient.of(panels), block, 2).unlockedBy("has_panels", has(panels));

        ConditionalRecipe
            .builder()
            .addCondition(new ModLoadedCondition(woodType.getModId()))
            .addRecipe(builder::save)
            .build(consumer,
                new ResourceLocation(Constants.MOD_ID,
                    Util.toRegistryName(block.getRegistryName().getPath(),
                        "from",
                        Objects.requireNonNull(panels.asItem().getRegistryName()).getPath(),
                        Objects.requireNonNull(WoodenRecipeSerializers.SAWMILLING.get().getRegistryName()).getPath())));

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasPlanks(woodType)) {
            final ItemLike planks =
                ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getResource());

            builder = ShapedRecipeBuilder
                .shaped(Objects.requireNonNull(planks))
                .define('S', block)
                .pattern("S")
                .pattern("S")
                .unlockedBy("has_panels_slab", has(block))
                .group("ilikewood:planks");

            ConditionalRecipe
                .builder()
                .addCondition(new ModLoadedCondition(woodType.getModId()))
                .addRecipe(builder::save)
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(Objects.requireNonNull(planks.asItem().getRegistryName()).getPath(),
                            "from",
                            block.getRegistryName().getPath())));

            builder = sawmillingRecipe(Ingredient.of(planks), block, 2).unlockedBy("has_planks", has(planks));

            ConditionalRecipe
                .builder()
                .addCondition(new ModLoadedCondition(woodType.getModId()))
                .addRecipe(builder::save)
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(block.getRegistryName().getPath(),
                            "from",
                            Objects.requireNonNull(planks.asItem().getRegistryName()).getPath(),
                            Objects
                                .requireNonNull(WoodenRecipeSerializers.SAWMILLING.get().getRegistryName())
                                .getPath())));
        }
    }
}
