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

public final class CrossbowRecipeProvider extends AbstractItemRecipeProvider {
    public CrossbowRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenItemType.CROSSBOW);
    }

    @Override
    protected void registerRecipe(final Item item, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) item).getWoodType();
        final IItemProvider stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);

        ShapedRecipeBuilder
            .shaped(item)
            .define('#', stick)
            .define('~', Items.STRING)
            .define('\u0026', Items.IRON_INGOT)
            .define('$', Items.TRIPWIRE_HOOK)
            .pattern("#\u0026#")
            .pattern("~$~")
            .pattern(" # ")
            .unlockedBy("has_string", has(Items.STRING))
            .group(ILikeWoodItemTags.BOWS.getName().getPath())
            .save(consumer);
    }
}
