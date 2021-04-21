package yamahari.ilikewood.provider.recipe.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class ItemFrameRecipeProvider extends AbstractItemRecipeProvider {
    public ItemFrameRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenItemType.ITEM_FRAME);
    }

    @Override
    protected void registerRecipe(final Item item, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) item).getWoodType();
        final IItemProvider stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);

        ShapedRecipeBuilder
            .shapedRecipe(item)
            .key('#', stick)
            .key('X', Items.LEATHER)
            .patternLine("###")
            .patternLine("#X#")
            .patternLine("###")
            .addCriterion("has_leather", hasItem(Items.LEATHER))
            .setGroup(ILikeWoodItemTags.ITEM_FRAMES.getName().getPath())
            .build(consumer);
    }
}
