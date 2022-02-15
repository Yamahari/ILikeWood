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

public final class WallRecipeProvider extends AbstractBlockItemRecipeProvider {
    public WallRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.WALL);
    }

    @Override
    protected void registerRecipes(@Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType,
                                   final Block block) {
        final ItemLike log = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY
            .getLog(woodType)
            .getResource()));

        RecipeBuilder builder = ShapedRecipeBuilder
            .shaped(block, 6)
            .define('#', Objects.requireNonNull(log))
            .pattern("###")
            .pattern("###")
            .unlockedBy("has_log", has(log))
            .group(ILikeWoodBlockTags.WALLS.getName().getPath());

        ConditionalRecipe
            .builder()
            .addCondition(new ModLoadedCondition(woodType.getModId()))
            .addRecipe(builder::save)
            .build(consumer, Objects.requireNonNull(block.getRegistryName()));

        builder = sawmillingRecipe(Ingredient.of(log), block).unlockedBy("has_log", has(log));

        ConditionalRecipe
            .builder()
            .addCondition(new ModLoadedCondition(woodType.getModId()))
            .addRecipe(builder::save)
            .build(consumer,
                new ResourceLocation(Constants.MOD_ID,
                    Util.toRegistryName(block.getRegistryName().getPath(),
                        "from",
                        Objects.requireNonNull(log.asItem().getRegistryName()).getPath(),
                        Objects.requireNonNull(WoodenRecipeSerializers.SAWMILLING.get().getRegistryName()).getPath())));

    }
}
