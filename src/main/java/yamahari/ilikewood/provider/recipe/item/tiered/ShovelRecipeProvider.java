package yamahari.ilikewood.provider.recipe.item.tiered;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class ShovelRecipeProvider extends AbstractTieredItemRecipeProvider {
    public ShovelRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenTieredItemType.SHOVEL);
    }

    @Override
    protected void registerRecipe(final Item tieredItem, @Nonnull final Consumer<FinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) tieredItem).getWoodType();
        final Ingredient repair = ((IWoodenTieredItem) tieredItem).getWoodenItemTier().getRepairIngredient();
        final ItemLike stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);

        ShapedRecipeBuilder.shaped(tieredItem).define('I', stick).define('#', repair).pattern("#").pattern("I")
            .pattern("I")
            .unlockedBy("has_material", has(repair))
            .group(ILikeWoodItemTags.SHOVELS.getName().getPath())
            .save(consumer);
    }
}
