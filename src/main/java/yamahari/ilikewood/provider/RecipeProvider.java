package yamahari.ilikewood.provider;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.objectholder.barrel.WoodenBarrelBlocks;
import yamahari.ilikewood.objectholder.bookshelf.WoodenBookshelfBlocks;
import yamahari.ilikewood.objectholder.panels.WoodenPanelsBlocks;
import yamahari.ilikewood.objectholder.panels.slab.WoodenPanelsSlabBlocks;
import yamahari.ilikewood.objectholder.panels.stairs.WoodenPanelsStairsBlocks;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Consumer;

public final class RecipeProvider extends net.minecraft.data.RecipeProvider {
    public RecipeProvider(DataGenerator generator) {
        super(generator);
    }

    private static IItemProvider getIngredient(final String name, final Class<?> objectHolder) {
        try {
            final Field block = objectHolder.getDeclaredField(name);
            return (IItemProvider) block.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            ILikeWood.LOGGER.error(e.getMessage());
        }
        return null;
    }

    private InventoryChangeTrigger.Instance hasItem(Ingredient ingredientIn) {
        return InventoryChangeTrigger.Instance.forItems(Arrays.stream(ingredientIn.getMatchingStacks()).map(ItemStack::getItem).toArray(Item[]::new));
    }

    @Override
    protected void registerRecipes(final Consumer<IFinishedRecipe> consumer) {
        Util.getBlocks(WoodenPanelsBlocks.class).forEach(
                block -> {
                    final IItemProvider slab = getIngredient(((IWooden) block).getWoodType().toString().toUpperCase() + "_SLAB", Blocks.class);
                    assert slab != null;
                    ShapedRecipeBuilder.shapedRecipe(block)
                            .key('#', slab)
                            .patternLine("#")
                            .patternLine("#")
                            .addCriterion("has_slab", this.hasItem(slab))
                            .build(consumer);
                });

        Util.getBlocks(WoodenPanelsStairsBlocks.class).forEach(
                block -> {
                    final IItemProvider panels = getIngredient(((IWooden) block).getWoodType().toString().toUpperCase(), WoodenPanelsBlocks.class);
                    assert panels != null;
                    ShapedRecipeBuilder.shapedRecipe(block, 4)
                            .key('#', panels)
                            .patternLine("#  ")
                            .patternLine("## ")
                            .patternLine("###")
                            .addCriterion("has_panels", this.hasItem(panels))
                            .build(consumer);
                });

        Util.getBlocks(WoodenPanelsSlabBlocks.class).forEach(
                block -> {
                    final IItemProvider ingredient = getIngredient(((IWooden) block).getWoodType().toString().toUpperCase(), WoodenPanelsBlocks.class);
                    final IItemProvider planks = getIngredient(((IWooden) block).getWoodType().toString().toUpperCase() + "_PLANKS", Blocks.class);
                    assert ingredient != null;
                    ShapedRecipeBuilder.shapedRecipe(block, 6)
                            .key('#', ingredient)
                            .patternLine("###")
                            .addCriterion("has_panels", this.hasItem(ingredient))
                            .build(consumer);

                    assert planks != null;
                    ShapedRecipeBuilder.shapedRecipe(planks)
                            .key('S', block)
                            .patternLine("S")
                            .patternLine("S")
                            .addCriterion("has_panels_slab", this.hasItem(block))
                            .build(consumer, Constants.MOD_ID + ":" + planks.asItem().getRegistryName().getPath() + "_from_" + block.getRegistryName().getPath());
                });

        Util.getBlocks(WoodenBarrelBlocks.class).forEach(
                block -> {
                    final IItemProvider panels = getIngredient(((IWooden) block).getWoodType().toString().toUpperCase(), WoodenPanelsBlocks.class);
                    final IItemProvider slab = getIngredient(((IWooden) block).getWoodType().toString().toUpperCase(), WoodenPanelsSlabBlocks.class);
                    assert panels != null;
                    assert slab != null;
                    ShapedRecipeBuilder.shapedRecipe(block)
                            .key('P', panels)
                            .key('S', slab)
                            .patternLine("PSP")
                            .patternLine("P P")
                            .patternLine("PSP")
                            .addCriterion("has_panels", this.hasItem(panels))
                            .build(consumer);
                });

        Util.getBlocks(WoodenBookshelfBlocks.class).forEach(
                block -> {
                    final IItemProvider panels = getIngredient(((IWooden) block).getWoodType().toString().toUpperCase(), WoodenPanelsBlocks.class);
                    assert panels != null;
                    ShapedRecipeBuilder.shapedRecipe(block)
                            .key('#', panels)
                            .key('X', Items.BOOK)
                            .patternLine("###")
                            .patternLine("XXX")
                            .patternLine("###")
                            .addCriterion("has_book", this.hasItem(Items.BOOK))
                            .build(consumer);
                });

    }

    @Override
    public String getName() {
        return "I Like Wood - Recipe Provider";
    }
}
