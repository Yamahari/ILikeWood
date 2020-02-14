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
import yamahari.ilikewood.data.tag.BlockTags;
import yamahari.ilikewood.data.tag.ItemTags;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

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
        WoodenBlocks.getBlocks(WoodenObjectType.PANELS).forEach(block -> {
            final IItemProvider slab = getIngredient(((IWooden) block).getWoodType().toString().toUpperCase() + "_SLAB", Blocks.class);
            assert slab != null;

            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('#', slab)
                    .patternLine("#")
                    .patternLine("#")
                    .addCriterion("has_slab", this.hasItem(slab))
                    .setGroup(BlockTags.PANELS.getId().toString())
                    .build(consumer);
        });
        WoodenBlocks.getBlocks(WoodenObjectType.STAIRS).forEach(block -> {
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, ((IWooden) block).getWoodType());

            ShapedRecipeBuilder.shapedRecipe(block, 4)
                    .key('#', panels)
                    .patternLine("#  ")
                    .patternLine("## ")
                    .patternLine("###")
                    .addCriterion("has_panels", this.hasItem(panels))
                    .setGroup(BlockTags.PANELS_STAIRS.getId().toString())
                    .build(consumer);
        });
        WoodenBlocks.getBlocks(WoodenObjectType.SLAB).forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, woodType);
            final IItemProvider planks = getIngredient(woodType.toString().toUpperCase() + "_PLANKS", Blocks.class);
            assert planks != null;

            ShapedRecipeBuilder.shapedRecipe(block, 6)
                    .key('#', panels)
                    .patternLine("###")
                    .addCriterion("has_panels", this.hasItem(panels))
                    .setGroup(BlockTags.PANELS_SLABS.getId().toString())
                    .build(consumer);

            ShapedRecipeBuilder.shapedRecipe(planks)
                    .key('S', block)
                    .patternLine("S")
                    .patternLine("S")
                    .addCriterion("has_panels_slab", this.hasItem(block))
                    .setGroup("ilikewood:planks")
                    .build(consumer, Constants.MOD_ID + ":" + planks.asItem().getRegistryName().getPath() + "_from_" + block.getRegistryName().getPath());
        });
        WoodenBlocks.getBlocks(WoodenObjectType.BARREL).forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, woodType);
            final IItemProvider slab = WoodenBlocks.getBlock(WoodenObjectType.SLAB, woodType);

            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('P', panels)
                    .key('S', slab)
                    .patternLine("PSP")
                    .patternLine("P P")
                    .patternLine("PSP")
                    .addCriterion("has_panels", this.hasItem(panels))
                    .setGroup(BlockTags.BARRELS.getId().toString())
                    .build(consumer);
        });
        WoodenBlocks.getBlocks(WoodenObjectType.BOOKSHELF).forEach(block -> {
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, ((IWooden) block).getWoodType());

            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('#', panels)
                    .key('X', Items.BOOK)
                    .patternLine("###")
                    .patternLine("XXX")
                    .patternLine("###")
                    .addCriterion("has_book", this.hasItem(Items.BOOK))
                    .setGroup(BlockTags.BOOKSHELFS.getId().toString())
                    .build(consumer);
        });
        WoodenBlocks.getBlocks(WoodenObjectType.CHEST).forEach(block -> {
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, ((IWooden) block).getWoodType());

            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('#', panels)
                    .patternLine("###")
                    .patternLine("# #")
                    .patternLine("###")
                    .addCriterion("has_panels", this.hasItem(panels))
                    .setGroup(BlockTags.CHESTS.getId().toString())
                    .build(consumer);
        });
        WoodenBlocks.getBlocks(WoodenObjectType.COMPOSTER).forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, woodType);
            final IItemProvider fence = getIngredient(woodType.toString().toUpperCase() + "_FENCE", Blocks.class);
            assert fence != null;

            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('#', panels)
                    .key('F', fence)
                    .patternLine("F F")
                    .patternLine("F F")
                    .patternLine("###")
                    .addCriterion("has_panels", this.hasItem(panels))
                    .setGroup(BlockTags.COMPOSTER.getId().toString())
                    .build(consumer);
        });

        WoodenBlocks.getBlocks(WoodenObjectType.WALL).forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider log = getIngredient(woodType.toString().toUpperCase() + "_LOG", Blocks.class);
            assert log != null;

            ShapedRecipeBuilder.shapedRecipe(block, 6)
                    .key('#', log)
                    .patternLine("###")
                    .patternLine("###")
                    .addCriterion("has_log", this.hasItem(log))
                    .setGroup(BlockTags.WALLS.getId().toString())
                    .build(consumer);
        });

        WoodenBlocks.getBlocks(WoodenObjectType.LADDER).forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider stick = WoodenItems.getItem(WoodenObjectType.STICK, woodType);

            ShapedRecipeBuilder.shapedRecipe(block, 3)
                    .key('I', stick)
                    .patternLine("I I")
                    .patternLine("III")
                    .patternLine("I I")
                    .addCriterion("has_stick", this.hasItem(stick))
                    .setGroup(BlockTags.LADDERS.getId().toString())
                    .build(consumer);
        });

        WoodenBlocks.getBlocks(WoodenObjectType.TORCH).forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider stick = WoodenItems.getItem(WoodenObjectType.STICK, woodType);
            final Ingredient coals = Ingredient.fromTag(net.minecraft.tags.ItemTags.COALS);
            ShapedRecipeBuilder.shapedRecipe(block, 4)
                    .key('I', stick)
                    .key('#', coals)
                    .patternLine("#")
                    .patternLine("I")
                    .addCriterion("has_coal", this.hasItem(coals))
                    .setGroup(BlockTags.TORCHES.getId().toString())
                    .build(consumer);
        });

        WoodenItems.getItems(WoodenObjectType.STICK).forEach(item -> {
            final WoodType woodType = ((IWooden) item).getWoodType();
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, woodType);

            ShapedRecipeBuilder.shapedRecipe(item, 4)
                    .key('#', panels)
                    .patternLine("#")
                    .patternLine("#")
                    .addCriterion("has_panels", this.hasItem(panels))
                    .setGroup(ItemTags.STICKS.getId().toString())
                    .build(consumer);
        });
    }

    @Override
    public String getName() {
        return "I Like Wood - Recipe Provider";
    }
}
