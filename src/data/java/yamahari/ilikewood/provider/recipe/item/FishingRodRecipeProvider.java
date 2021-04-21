package yamahari.ilikewood.provider.recipe.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.objecttype.WoodenItemType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class FishingRodRecipeProvider extends AbstractItemRecipeProvider {

    public FishingRodRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenItemType.FISHING_ROD);
    }

    @Override
    protected void registerRecipe(Item item, @Nonnull Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) item).getWoodType();
        final IItemProvider stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);

        ShapedRecipeBuilder
            .shapedRecipe(item)
            .key('#', stick)
            .key('X', Items.STRING)
            .patternLine("  #")
            .patternLine(" #X")
            .patternLine("# X")
            .addCriterion("has_string", hasItem(Items.STRING))
            .setGroup(ILikeWoodItemTags.FISHING_POLES.getName().getPath())
            .build(consumer);
    }
}
