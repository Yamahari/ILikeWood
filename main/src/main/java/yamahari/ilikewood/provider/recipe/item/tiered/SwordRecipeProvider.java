package yamahari.ilikewood.provider.recipe.item.tiered;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class SwordRecipeProvider extends AbstractTieredItemRecipeProvider {
    public SwordRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenTieredItemType.SWORD);
    }

    @Override
    protected void registerRecipes(@Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType,
                                   final Item tieredItem) {
        final Ingredient repair = ((IWoodenTieredItem) tieredItem).getWoodenItemTier().getRepairIngredient();
        final ItemLike stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);

        final RecipeBuilder builder = ShapedRecipeBuilder
            .shaped(tieredItem)
            .define('I', stick)
            .define('#', repair)
            .pattern("#")
            .pattern("#")
            .pattern("I")
            .unlockedBy("has_material", has(repair))
            .group(ILikeWoodItemTags.SWORDS.getName().getPath());

        ConditionalRecipe
            .builder()
            .addCondition(new ModLoadedCondition(woodType.getModId()))
            .addRecipe(builder::save)
            .build(consumer, Objects.requireNonNull(tieredItem.getRegistryName()));
    }
}
