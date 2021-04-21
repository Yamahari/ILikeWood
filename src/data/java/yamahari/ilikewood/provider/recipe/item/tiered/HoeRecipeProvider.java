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
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.objecttype.WoodenItemType;
import yamahari.ilikewood.util.objecttype.WoodenTieredItemType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class HoeRecipeProvider extends AbstractTieredItemRecipeProvider {
    public HoeRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenTieredItemType.HOE);
    }

    @Override
    protected void registerRecipe(final Item tieredItem, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) tieredItem).getWoodType();
        final Ingredient repair = ((IWoodenTieredItem) tieredItem).getWoodenItemTier().getRepairMaterial();
        final IItemProvider stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);

        ShapedRecipeBuilder
            .shapedRecipe(tieredItem)
            .key('I', stick)
            .key('#', repair)
            .patternLine("##")
            .patternLine(" I")
            .patternLine(" I")
            .addCriterion("has_material", hasItem(repair))
            .setGroup(ILikeWoodItemTags.HOES.getName().getPath())
            .build(consumer);
    }
}
