package yamahari.ilikewood.provider.recipe.item.tiered;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.SmithingRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodenItemTiers;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;
import yamahari.ilikewood.util.objecttype.WoodenTieredItemType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class NetheriteTieredItemRecipeProvider extends RecipeProvider {
    public NetheriteTieredItemRecipeProvider(final DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void registerRecipes(@Nonnull final Consumer<IFinishedRecipe> consumer) {
        WoodenTieredItemType
            .getAll()
            .flatMap(ILikeWood.TIERED_ITEM_REGISTRY::getObjects)
            .filter(tieredItem -> ((IWoodenTieredItem) tieredItem)
                .getWoodenItemTier()
                .equals(VanillaWoodenItemTiers.DIAMOND))
            .forEach(tieredItem -> this.registerRecipe(tieredItem, consumer));
    }

    private void registerRecipe(final Item item, @Nonnull final Consumer<IFinishedRecipe> consumer) {
        final IWoodenTieredItem tieredItem = ((IWoodenTieredItem) item);
        final IWoodType woodType = ((IWooden) item).getWoodType();
        try {
            final Item output = ILikeWood.TIERED_ITEM_REGISTRY.getObject(VanillaWoodenItemTiers.NETHERITE,
                woodType,
                tieredItem.getTieredItemType());

            SmithingRecipeBuilder
                .smithingRecipe(Ingredient.fromItems(item), Ingredient.fromItems(Items.NETHERITE_INGOT), output)
                .addCriterion("has_netherite_ingot", hasItem(Items.NETHERITE_INGOT))
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(output.getRegistryName().getPath(), "smithing")));
        } catch (final IllegalArgumentException ignored) {
            ILikeWood.LOGGER.info("No netherite tiered item found for diamond<->netherite smithing recipe!");
        }
    }

    @Nonnull
    @Override
    public final String getName() {
        return String.format("%s - netherite tiered item recipes", Constants.MOD_ID);
    }
}
