package yamahari.ilikewood.provider.recipe.item;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class ItemFrameRecipeProvider extends AbstractItemRecipeProvider
{
    public ItemFrameRecipeProvider(final DataGenerator generator) {
        super(generator, WoodenItemType.ITEM_FRAME);
    }

    @Override
    protected void registerRecipes(
        @Nonnull final Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Item item
    )
    {
        final ItemLike stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);

        ShapedRecipeBuilder.shaped(item)
            .define('#', stick)
            .define('X', Items.LEATHER)
            .pattern("###")
            .pattern("#X#")
            .pattern("###")
            .unlockedBy("has_leather", has(Items.LEATHER))
            .group(String.format("%s:%s", Constants.MOD_ID, Constants.ITEM_FRAME_PLURAL))
            .save(consumer, Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)));
    }
}