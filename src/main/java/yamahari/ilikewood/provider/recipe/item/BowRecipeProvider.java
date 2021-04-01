package yamahari.ilikewood.provider.recipe.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class BowRecipeProvider extends AbstractItemRecipeProvider {
    public BowRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenObjectTypes.BOW);
    }

    @Override
    protected void registerRecipe(final Item item, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) item).getWoodType();
        final IItemProvider stick = WoodenItems.getItem(WoodenObjectTypes.STICK, woodType);

        ShapedRecipeBuilder
            .shapedRecipe(item)
            .key('#', stick)
            .key('X', Items.STRING)
            .patternLine(" #X")
            .patternLine("# X")
            .patternLine(" #X")
            .addCriterion("has_string", hasItem(Items.STRING))
            .setGroup(ILikeWoodItemTags.BOWS.getName().getPath())
            .build(consumer);
    }
}
