package yamahari.ilikewood.provider;

import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.objectholder.panels.WoodenPanelsBlocks;
import yamahari.ilikewood.objectholder.panels.slab.WoodenPanelsSlabBlocks;
import yamahari.ilikewood.objectholder.panels.stairs.WoodenPanelsStairsBlocks;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.Util;

import java.lang.reflect.Field;
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

    @Override
    protected void registerRecipes(final Consumer<IFinishedRecipe> consumer) {
        Util.getBlocks(WoodenPanelsBlocks.class)
                .forEach(block -> {
                    final IItemProvider ingredient = getIngredient(((IWooden) block).getType().toString().toUpperCase() + "_SLAB", Blocks.class);
                    ShapedRecipeBuilder.shapedRecipe(block).key('#', ingredient).patternLine("#").patternLine("#").addCriterion("has_planks", this.hasItem(ingredient)).build(consumer);
                });

        Util.getBlocks(WoodenPanelsStairsBlocks.class)
                .forEach(block -> {
                    final IItemProvider ingredient = getIngredient(((IWooden) block).getType().toString().toUpperCase(), WoodenPanelsBlocks.class);
                    ShapedRecipeBuilder.shapedRecipe(block, 4).key('#', ingredient).patternLine("#  ").patternLine("## ").patternLine("###").addCriterion("has_panels", this.hasItem(ingredient)).build(consumer);
                });

        Util.getBlocks(WoodenPanelsSlabBlocks.class)
                .forEach(block -> {
                    final IItemProvider ingredient = getIngredient(((IWooden) block).getType().toString().toUpperCase(), WoodenPanelsBlocks.class);
                    final IItemProvider planks = getIngredient(((IWooden) block).getType().toString().toUpperCase() + "_PLANKS", Blocks.class);
                    ShapedRecipeBuilder.shapedRecipe(block, 6).key('#', ingredient).patternLine("###").addCriterion("has_panels", this.hasItem(ingredient)).build(consumer);
                    ShapedRecipeBuilder.shapedRecipe(planks, 1).key('S', block).patternLine("S").patternLine("S").addCriterion("has_panels_slab", this.hasItem(block)).build(consumer, Constants.MOD_ID + ":" + planks.asItem().getRegistryName().getPath() + "_from_" + block.getRegistryName().getPath());
                });
    }

    @Override
    public String getName() {
        return "I Like Wood - Recipe Provider";
    }
}
