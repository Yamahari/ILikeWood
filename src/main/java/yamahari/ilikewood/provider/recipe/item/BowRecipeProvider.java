package yamahari.ilikewood.provider.recipe.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class BowRecipeProvider extends AbstractItemRecipeProvider {
    public BowRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenItemType.BOW);
    }

    @Override
    protected void registerRecipe(final Item item, @Nonnull final Consumer<FinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) item).getWoodType();
        final ItemLike stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);

        ShapedRecipeBuilder
            .shaped(item)
            .define('#', stick)
            .define('X', Items.STRING)
            .pattern(" #X")
            .pattern("# X")
            .pattern(" #X")
            .unlockedBy("has_string", has(Items.STRING))
            .group(ILikeWoodItemTags.BOWS.getName().getPath())
            .save(consumer);
    }
}
