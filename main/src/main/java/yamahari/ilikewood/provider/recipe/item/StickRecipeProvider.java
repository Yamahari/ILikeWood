package yamahari.ilikewood.provider.recipe.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class StickRecipeProvider extends AbstractItemRecipeProvider {
    public StickRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenItemType.STICK);
    }

    @Override
    protected void registerRecipes(
        @Nonnull final Consumer<FinishedRecipe> consumer,
        final IWoodType woodType,
        final Item item
    )
    {
        final ItemLike panels = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS);

        ShapedRecipeBuilder
            .shaped(item, 4)
            .define('#', panels)
            .pattern("#")
            .pattern("#")
            .unlockedBy("has_panels", has(panels))
            .group(String.format("%s:%s", Constants.MOD_ID, Constants.ITEM_FRAME_PLURAL))
            .save(consumer, Objects.requireNonNull(item.getRegistryName()));
    }
}