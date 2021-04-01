package yamahari.ilikewood.provider.recipe.item.tiered;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;
import yamahari.ilikewood.util.objecttype.tiered.WoodenTieredObjectTypes;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class AxeRecipeProvider extends AbstractTieredItemRecipeProvider {
    public AxeRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenTieredObjectTypes.AXE);
    }

    @Override
    protected void registerRecipe(final Item item, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) item).getWoodType();
        final Ingredient repair = ((IWoodenTieredItem) item).getWoodenItemTier().getRepairMaterial();
        final IItemProvider stick = WoodenItems.getItem(WoodenObjectTypes.STICK, woodType);

        ShapedRecipeBuilder
            .shapedRecipe(item)
            .key('I', stick)
            .key('#', repair)
            .patternLine("##")
            .patternLine("#I")
            .patternLine(" I")
            .addCriterion("has_material", hasItem(repair))
            .setGroup(ILikeWoodItemTags.AXES.getName().getPath())
            .build(consumer);
    }
}
