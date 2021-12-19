package yamahari.ilikewood.provider.recipe.item.tiered;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class PickaxeRecipeProvider extends AbstractTieredItemRecipeProvider {
    public PickaxeRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenTieredItemType.PICKAXE);
    }

    @Override
    protected void registerRecipe(final Item tieredItem, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) tieredItem).getWoodType();
        final Ingredient repair = ((IWoodenTieredItem) tieredItem).getWoodenItemTier().getRepairIngredient();
        final IItemProvider stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);

        ShapedRecipeBuilder
            .shaped(tieredItem)
            .define('I', stick)
            .define('#', repair)
            .pattern("###")
            .pattern(" I ")
            .pattern(" I ")
            .unlockedBy("has_material", has(repair))
            .group(ILikeWoodItemTags.PICKAXES.getName().getPath())
            .save(consumer);
    }
}
