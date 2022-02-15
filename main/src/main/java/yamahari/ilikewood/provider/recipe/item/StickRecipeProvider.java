package yamahari.ilikewood.provider.recipe.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class StickRecipeProvider extends AbstractItemRecipeProvider {
    public StickRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenItemType.STICK);
    }

    @Override
    protected void registerRecipes(@Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType,
                                   final Item item) {
        final ItemLike panels = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS);

        RecipeBuilder builder = ShapedRecipeBuilder
            .shaped(item, 4)
            .define('#', panels)
            .pattern("#")
            .pattern("#")
            .unlockedBy("has_panels", has(panels))
            .group(ILikeWoodItemTags.STICKS.getName().getPath());

        ConditionalRecipe
            .builder()
            .addCondition(new ModLoadedCondition(woodType.getModId()))
            .addRecipe(builder::save)
            .build(consumer, Objects.requireNonNull(item.getRegistryName()));

        builder = sawmillingRecipe(Ingredient.of(panels), item, 2).unlockedBy("has_panels", has(panels));

        ConditionalRecipe
            .builder()
            .addCondition(new ModLoadedCondition(woodType.getModId()))
            .addRecipe(builder::save)
            .build(consumer,
                new ResourceLocation(Constants.MOD_ID,
                    Util.toRegistryName(item.getRegistryName().getPath(),
                        "from",
                        Objects.requireNonNull(panels.asItem().getRegistryName()).getPath(),
                        Objects.requireNonNull(WoodenRecipeSerializers.SAWMILLING.get().getRegistryName()).getPath())));

        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasPlanks(woodType)) {
            final ItemLike planks =
                Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY
                    .getPlanks(woodType)
                    .getResource()));

            builder = sawmillingRecipe(Ingredient.of(planks), item, 2).unlockedBy("has_planks", has(planks));

            ConditionalRecipe
                .builder()
                .addCondition(new ModLoadedCondition(woodType.getModId()))
                .addRecipe(builder::save)
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(item.getRegistryName().getPath(),
                            "from",
                            Objects.requireNonNull(planks.asItem().getRegistryName()).getPath(),
                            Objects
                                .requireNonNull(WoodenRecipeSerializers.SAWMILLING.get().getRegistryName())
                                .getPath())));
        }
    }
}
