package yamahari.ilikewood.provider.recipe.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class CrossbowRecipeProvider extends AbstractItemRecipeProvider {
    public CrossbowRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenItemType.CROSSBOW);
    }

    @Override
    protected void registerRecipes(@Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType,
                                   final Item item) {
        final ItemLike stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);

        final RecipeBuilder builder = ShapedRecipeBuilder
            .shaped(item)
            .define('#', stick)
            .define('~', Items.STRING)
            .define('\u0026', Items.IRON_INGOT)
            .define('$', Items.TRIPWIRE_HOOK)
            .pattern("#\u0026#")
            .pattern("~$~")
            .pattern(" # ")
            .unlockedBy("has_string", has(Items.STRING))
            .group(ILikeWoodItemTags.BOWS.getName().getPath());

        ConditionalRecipe
            .builder()
            .addCondition(new ModLoadedCondition(woodType.getModId()))
            .addRecipe(builder::save)
            .build(consumer, Objects.requireNonNull(item.getRegistryName()));
    }
}
