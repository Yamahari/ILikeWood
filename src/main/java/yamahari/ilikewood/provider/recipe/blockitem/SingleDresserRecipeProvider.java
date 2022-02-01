package yamahari.ilikewood.provider.recipe.blockitem;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class SingleDresserRecipeProvider extends AbstractBlockItemRecipeProvider {
    public SingleDresserRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenBlockType.SINGLE_DRESSER);
    }

    @Override
    protected void registerRecipes(final @Nonnull Consumer<FinishedRecipe> consumer, final IWoodType woodType,
                                   final Block block) {
        final ItemLike strippedLog =
            ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType).getResource());

        final ItemLike log =
            ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType).getResource());

        final RecipeBuilder builder = ShapedRecipeBuilder
            .shaped(block, 3)
            .define('#', Objects.requireNonNull(log))
            .define('X', Objects.requireNonNull(strippedLog))
            .pattern("###")
            .pattern("XXX")
            .pattern("XXX")
            .group(ILikeWoodBlockTags.SINGLE_DRESSER.getName().getPath())
            .unlockedBy("has_stripped_log", has(strippedLog));

        ConditionalRecipe
            .builder()
            .addCondition(new ModLoadedCondition(woodType.getModId()))
            .addRecipe(builder::save)
            .build(consumer, Objects.requireNonNull(block.getRegistryName()));
    }
}