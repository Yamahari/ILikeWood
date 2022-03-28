package yamahari.ilikewood.provider.recipe.item.tiered;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodenItemTiers;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Consumer;

public final class NetheriteTieredItemRecipeProvider extends RecipeProvider {
    private final WoodenTieredItemType tieredItemType;

    public NetheriteTieredItemRecipeProvider(final DataGenerator generator, final WoodenTieredItemType tieredItemType) {
        super(generator);
        this.tieredItemType = tieredItemType;
    }

    @Override
    protected void buildCraftingRecipes(@Nonnull final Consumer<FinishedRecipe> consumer) {
        ILikeWood.TIERED_ITEM_REGISTRY.getObjects(tieredItemType)
            .filter(tieredItem -> ((IWoodenTieredItem) tieredItem)
                .getWoodenItemTier()
                .equals(VanillaWoodenItemTiers.DIAMOND))
            .forEach(tieredItem -> this.registerRecipes(consumer, tieredItem));
    }

    private void registerRecipes(@Nonnull final Consumer<FinishedRecipe> consumer, final Item item) {
        final IWoodenTieredItem tieredItem = ((IWoodenTieredItem) item);
        final IWoodType woodType = ((IWooden) item).getWoodType();
        try {
            final Item output = ILikeWood.TIERED_ITEM_REGISTRY.getObject(
                VanillaWoodenItemTiers.NETHERITE,
                woodType,
                tieredItem.getTieredItemType()
            );

            UpgradeRecipeBuilder
                .smithing(Ingredient.of(item), Ingredient.of(Items.NETHERITE_INGOT), output)
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(
                    consumer,
                    new ResourceLocation(
                        Constants.MOD_ID,
                        Util.toRegistryName(Objects.requireNonNull(output.getRegistryName()).getPath(), "smithing")
                    )
                );

        }
        catch (final IllegalArgumentException ignored) {
            ILikeWood.LOGGER.info("No netherite tiered item found for diamond<->netherite smithing recipe!");
        }
    }

    @Nonnull
    @Override
    public String getName() {
        return String.format("%s - netherite %s", Constants.MOD_ID, this.tieredItemType.getName());
    }
}