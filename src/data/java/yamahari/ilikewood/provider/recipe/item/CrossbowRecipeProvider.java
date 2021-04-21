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

public final class CrossbowRecipeProvider extends AbstractItemRecipeProvider {
    public CrossbowRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenItemType.CROSSBOW);
    }

    @Override
    protected void registerRecipe(final Item item, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodType woodType = ((IWooden) item).getWoodType();
        final IItemProvider stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);

        ShapedRecipeBuilder
            .shapedRecipe(item)
            .key('#', stick)
            .key('~', Items.STRING)
            .key('\u0026', Items.IRON_INGOT)
            .key('$', Items.TRIPWIRE_HOOK)
            .patternLine("#\u0026#")
            .patternLine("~$~")
            .patternLine(" # ")
            .addCriterion("has_string", hasItem(Items.STRING))
            .setGroup(ILikeWoodItemTags.BOWS.getName().getPath())
            .build(consumer);
    }
}
