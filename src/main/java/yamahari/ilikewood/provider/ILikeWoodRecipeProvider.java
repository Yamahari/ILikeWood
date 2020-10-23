package yamahari.ilikewood.provider;

import biomesoplenty.api.block.BOPBlocks;
import deerangle.treemendous.api.TreemendousBlocks;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenBedBlock;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.util.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Consumer;

public final class ILikeWoodRecipeProvider extends RecipeProvider {
    public ILikeWoodRecipeProvider(final DataGenerator generator) {
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

    private static IItemProvider getIngredient(final WoodType woodType, final String name) {
        final IItemProvider ingredient;
        switch (woodType.getModId()) {
            case Constants.MOD_ID:
                ingredient = getIngredient(Util.toRegistryName(woodType.toString().toUpperCase(), name.toUpperCase()), Blocks.class);
                break;
            case Constants.BOP_MOD_ID:
                ingredient = getIngredient(Util.toRegistryName(woodType.toString(), name), BOPBlocks.class);
                break;
            case Constants.TRM_MOD_ID:
                ingredient = getIngredient(Util.toRegistryName(woodType.getName(), name), TreemendousBlocks.class);
                break;
            default:
                ingredient = null;
                break;
        }
        assert ingredient != null;
        return ingredient;
    }

    private static IItemProvider getLog(final WoodType woodType) {
        switch (woodType) {
            case CRIMSON:
            case WARPED:
                return getIngredient(woodType, "stem");
            default:
                return getIngredient(woodType, "log");
        }
    }

    private static IItemProvider getSlab(final WoodType woodType) {
        return getIngredient(woodType, "slab");
    }

    private static IItemProvider getPlanks(final WoodType woodType) {
        return getIngredient(woodType, "planks");
    }

    private static IItemProvider getFence(final WoodType woodType) {
        return getIngredient(woodType, "fence");
    }

    private InventoryChangeTrigger.Instance hasItem(final Ingredient ingredientIn) {
        return InventoryChangeTrigger.Instance.forItems(Arrays.stream(ingredientIn.getMatchingStacks()).map(ItemStack::getItem).toArray(Item[]::new));
    }

    @Override
    protected void registerRecipes(final Consumer<IFinishedRecipe> consumer) {
        WoodenBlocks.getBlocks(WoodenObjectType.PANELS).forEach(block -> {
            final IItemProvider slab = getSlab(((IWooden) block).getWoodType());

            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('#', slab)
                    .patternLine("#")
                    .patternLine("#")
                    .addCriterion("has_slab", hasItem(slab))
                    .setGroup(ILikeWoodBlockTags.PANELS.getName().getPath())
                    .build(consumer);
        });

        WoodenBlocks.getBlocks(WoodenObjectType.STAIRS).forEach(block -> {
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, ((IWooden) block).getWoodType());

            ShapedRecipeBuilder.shapedRecipe(block, 4)
                    .key('#', panels)
                    .patternLine("#  ")
                    .patternLine("## ")
                    .patternLine("###")
                    .addCriterion("has_panels", hasItem(panels))
                    .setGroup(ILikeWoodBlockTags.PANELS.getName().getPath())
                    .build(consumer);
        });

        WoodenBlocks.getBlocks(WoodenObjectType.SLAB).forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, woodType);
            final IItemProvider planks = getPlanks(woodType);

            ShapedRecipeBuilder.shapedRecipe(block, 6)
                    .key('#', panels)
                    .patternLine("###")
                    .addCriterion("has_panels", hasItem(panels))
                    .setGroup(ILikeWoodBlockTags.PANELS_SLABS.getName().getPath())
                    .build(consumer);

            ShapedRecipeBuilder.shapedRecipe(planks)
                    .key('S', block)
                    .patternLine("S")
                    .patternLine("S")
                    .addCriterion("has_panels_slab", hasItem(block))
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
                    .addCriterion("has_panels", hasItem(panels))
                    .setGroup(ILikeWoodBlockTags.BARRELS.getName().getPath())
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
                    .addCriterion("has_book", hasItem(Items.BOOK))
                    .setGroup(ILikeWoodBlockTags.BOOKSHELFS.getName().getPath())
                    .build(consumer);
        });

        WoodenBlocks.getBlocks(WoodenObjectType.CHEST).forEach(block -> {
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, ((IWooden) block).getWoodType());

            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('#', panels)
                    .patternLine("###")
                    .patternLine("# #")
                    .patternLine("###")
                    .addCriterion("has_panels", hasItem(panels))
                    .setGroup(ILikeWoodBlockTags.CHESTS.getName().getPath())
                    .build(consumer);
        });

        WoodenBlocks.getBlocks(WoodenObjectType.COMPOSTER).forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, woodType);
            final IItemProvider fence = getFence(woodType);

            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('#', panels)
                    .key('F', fence)
                    .patternLine("F F")
                    .patternLine("F F")
                    .patternLine("###")
                    .addCriterion("has_panels", hasItem(panels))
                    .setGroup(ILikeWoodBlockTags.COMPOSTER.getName().getPath())
                    .build(consumer);
        });

        WoodenBlocks.getBlocks(WoodenObjectType.WALL).forEach(block -> {
            final IItemProvider log = getLog(((IWooden) block).getWoodType());

            ShapedRecipeBuilder.shapedRecipe(block, 6)
                    .key('#', log)
                    .patternLine("###")
                    .patternLine("###")
                    .addCriterion("has_log", hasItem(log))
                    .setGroup(ILikeWoodBlockTags.WALLS.getName().getPath())
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
                    .addCriterion("has_stick", hasItem(stick))
                    .setGroup(ILikeWoodBlockTags.LADDERS.getName().getPath())
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
                    .addCriterion("has_coal", hasItem(net.minecraft.tags.ItemTags.COALS))
                    .setGroup(ILikeWoodBlockTags.TORCHES.getName().getPath())
                    .build(consumer);
        });

        WoodenBlocks.getBlocks(WoodenObjectType.CRAFTING_TABLE).forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, woodType);

            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('#', panels)
                    .patternLine("##")
                    .patternLine("##")
                    .addCriterion("has_panels", hasItem(panels))
                    .setGroup(ILikeWoodBlockTags.CRAFTING_TABLES.getName().getPath())
                    .build(consumer);
        });

        WoodenBlocks.getBlocks(WoodenObjectType.SCAFFOLDING).forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider stick = WoodenItems.getItem(WoodenObjectType.STICK, woodType);

            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('I', stick)
                    .key('~', Items.STRING)
                    .patternLine("I~I")
                    .patternLine("I I")
                    .patternLine("I I")
                    .addCriterion("has_stick", hasItem(stick))
                    .setGroup(ILikeWoodBlockTags.SCAFFOLDINGS.getName().getPath())
                    .build(consumer);
        });

        WoodenBlocks.getBlocks(WoodenObjectType.LECTERN).forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider slab = WoodenBlocks.getBlock(WoodenObjectType.SLAB, woodType);
            final IItemProvider bookshelf = WoodenBlocks.getBlock(WoodenObjectType.BOOKSHELF, woodType);

            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('S', slab)
                    .key('B', bookshelf)
                    .patternLine("SSS")
                    .patternLine(" B ")
                    .patternLine(" S ")
                    .addCriterion("has_book", hasItem(Items.BOOK))
                    .setGroup(ILikeWoodBlockTags.LECTERNS.getName().getPath())
                    .build(consumer);
        });

        WoodenBlocks.getBlocks(WoodenObjectType.POST).forEach(block -> {
            final IItemProvider log = getLog(((IWooden) block).getWoodType());

            ShapedRecipeBuilder.shapedRecipe(block, 6)
                    .key('#', log)
                    .patternLine("#")
                    .patternLine("#")
                    .patternLine("#")
                    .addCriterion("has_log", hasItem(log))
                    .setGroup(ILikeWoodBlockTags.POSTS.getName().getPath())
                    .build(consumer);
        });

        WoodenItems.getItems(WoodenObjectType.STICK).forEach(item -> {
            final WoodType woodType = ((IWooden) item).getWoodType();
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, woodType);

            ShapedRecipeBuilder.shapedRecipe(item, 4)
                    .key('#', panels)
                    .patternLine("#")
                    .patternLine("#")
                    .addCriterion("has_panels", hasItem(panels))
                    .setGroup(ILikeWoodItemTags.STICKS.getName().getPath())
                    .build(consumer);
        });

        WoodenItems.getTieredItems(WoodenTieredObjectType.AXE)
                .filter(item -> !((IWoodenTieredItem) item).getWoodenItemTier().equals(WoodenItemTier.NETHERITE))
                .forEach(item -> {
                    final WoodType woodType = ((IWooden) item).getWoodType();
                    final Ingredient repair = ((IWoodenTieredItem) item).getWoodenItemTier().getRepairMaterial();
                    final IItemProvider stick = WoodenItems.getItem(WoodenObjectType.STICK, woodType);

                    ShapedRecipeBuilder.shapedRecipe(item)
                            .key('I', stick)
                            .key('#', repair)
                            .patternLine("##")
                            .patternLine("#I")
                            .patternLine(" I")
                            .addCriterion("has_material", hasItem(repair))
                            .setGroup(ILikeWoodItemTags.AXES.getName().getPath())
                            .build(consumer);
                });

        WoodenItems.getTieredItems(WoodenTieredObjectType.HOE)
                .filter(item -> !((IWoodenTieredItem) item).getWoodenItemTier().equals(WoodenItemTier.NETHERITE))
                .forEach(item -> {
                    final WoodType woodType = ((IWooden) item).getWoodType();
                    final Ingredient repair = ((IWoodenTieredItem) item).getWoodenItemTier().getRepairMaterial();
                    final IItemProvider stick = WoodenItems.getItem(WoodenObjectType.STICK, woodType);

                    ShapedRecipeBuilder.shapedRecipe(item)
                            .key('I', stick)
                            .key('#', repair)
                            .patternLine("##")
                            .patternLine(" I")
                            .patternLine(" I")
                            .addCriterion("has_material", hasItem(repair))
                            .setGroup(ILikeWoodItemTags.HOES.getName().getPath())
                            .build(consumer);
                });

        WoodenItems.getTieredItems(WoodenTieredObjectType.PICKAXE)
                .filter(item -> !((IWoodenTieredItem) item).getWoodenItemTier().equals(WoodenItemTier.NETHERITE))
                .forEach(item -> {
                    final WoodType woodType = ((IWooden) item).getWoodType();
                    final Ingredient repair = ((IWoodenTieredItem) item).getWoodenItemTier().getRepairMaterial();
                    final IItemProvider stick = WoodenItems.getItem(WoodenObjectType.STICK, woodType);

                    ShapedRecipeBuilder.shapedRecipe(item)
                            .key('I', stick)
                            .key('#', repair)
                            .patternLine("###")
                            .patternLine(" I ")
                            .patternLine(" I ")
                            .addCriterion("has_material", hasItem(repair))
                            .setGroup(ILikeWoodItemTags.PICKAXES.getName().getPath())
                            .build(consumer);
                });

        WoodenItems.getTieredItems(WoodenTieredObjectType.SHOVEL)
                .filter(item -> !((IWoodenTieredItem) item).getWoodenItemTier().equals(WoodenItemTier.NETHERITE))
                .forEach(item -> {
                    final WoodType woodType = ((IWooden) item).getWoodType();
                    final Ingredient repair = ((IWoodenTieredItem) item).getWoodenItemTier().getRepairMaterial();
                    final IItemProvider stick = WoodenItems.getItem(WoodenObjectType.STICK, woodType);

                    ShapedRecipeBuilder.shapedRecipe(item)
                            .key('I', stick)
                            .key('#', repair)
                            .patternLine("#")
                            .patternLine("I")
                            .patternLine("I")
                            .addCriterion("has_material", hasItem(repair))
                            .setGroup(ILikeWoodItemTags.SHOVELS.getName().getPath())
                            .build(consumer);
                });

        WoodenItems.getTieredItems(WoodenTieredObjectType.SWORD)
                .filter(item -> !((IWoodenTieredItem) item).getWoodenItemTier().equals(WoodenItemTier.NETHERITE))
                .forEach(item -> {
                    final WoodType woodType = ((IWooden) item).getWoodType();
                    final Ingredient repair = ((IWoodenTieredItem) item).getWoodenItemTier().getRepairMaterial();
                    final IItemProvider stick = WoodenItems.getItem(WoodenObjectType.STICK, woodType);

                    ShapedRecipeBuilder.shapedRecipe(item)
                            .key('I', stick)
                            .key('#', repair)
                            .patternLine("#")
                            .patternLine("#")
                            .patternLine("I")
                            .addCriterion("has_material", hasItem(repair))
                            .setGroup(ILikeWoodItemTags.SWORDS.getName().getPath())
                            .build(consumer);
                });

        WoodenItems.getItems(WoodenObjectType.BOW).forEach(item -> {
            final WoodType woodType = ((IWooden) item).getWoodType();
            final IItemProvider stick = WoodenItems.getItem(WoodenObjectType.STICK, woodType);

            ShapedRecipeBuilder.shapedRecipe(item)
                    .key('#', stick)
                    .key('X', Items.STRING)
                    .patternLine(" #X")
                    .patternLine("# X")
                    .patternLine(" #X")
                    .addCriterion("has_string", hasItem(Items.STRING))
                    .setGroup(ILikeWoodItemTags.BOWS.getName().getPath())
                    .build(consumer);
        });

        WoodenItems.getItems(WoodenObjectType.CROSSBOW).forEach(item -> {
            final WoodType woodType = ((IWooden) item).getWoodType();
            final IItemProvider stick = WoodenItems.getItem(WoodenObjectType.STICK, woodType);

            ShapedRecipeBuilder.shapedRecipe(item)
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
        });

        WoodenItems.getItems(WoodenObjectType.ITEM_FRAME).forEach(item -> {
            final WoodType woodType = ((IWooden) item).getWoodType();
            final IItemProvider stick = WoodenItems.getItem(WoodenObjectType.STICK, woodType);

            ShapedRecipeBuilder.shapedRecipe(item)
                    .key('#', stick)
                    .key('X', Items.LEATHER)
                    .patternLine("###")
                    .patternLine("#X#")
                    .patternLine("###")
                    .addCriterion("has_leather", hasItem(Items.LEATHER))
                    .setGroup(ILikeWoodItemTags.ITEM_FRAMES.getName().getPath())
                    .build(consumer);
        });

        WoodenItems.getTieredItems(WoodenTieredObjectType.AXE, WoodenTieredObjectType.PICKAXE, WoodenTieredObjectType.SHOVEL, WoodenTieredObjectType.HOE, WoodenTieredObjectType.SWORD)
                .filter(item -> ((IWoodenTieredItem) item).getWoodenItemTier().equals(WoodenItemTier.DIAMOND))
                .forEach(item -> {
                    final IWoodenTieredItem tieredItem = ((IWoodenTieredItem) item);
                    final WoodType woodType = ((IWooden) item).getWoodType();
                    final Item output = WoodenItems.getTieredItem(tieredItem.getWoodenTieredObjectType(), woodType, WoodenItemTier.NETHERITE);
                    SmithingRecipeBuilder.smithingRecipe(Ingredient.fromItems(item), Ingredient.fromItems(Items.NETHERITE_INGOT), output)
                            .addCriterion("has_netherite_ingot", hasItem(Items.NETHERITE_INGOT))
                            .build(consumer, new ResourceLocation(Constants.MOD_ID, Util.toRegistryName(output.getRegistryName().getPath(), "smithing")));
                });

        WoodenBlocks.getBedBlocks().forEach(block -> {
            final WoodType woodType = ((IWooden) block).getWoodType();
            final DyeColor color = ((WoodenBedBlock) block).getDyeColor();
            final IItemProvider wool = getIngredient(Util.toRegistryName(color.toString().toUpperCase(), "WOOL"), Blocks.class);
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, woodType);
            final IItemProvider dye = getIngredient(Util.toRegistryName(color.toString().toUpperCase(), "DYE"), Items.class);
            final IItemProvider whiteBed = WoodenBlocks.getBedBlock(woodType, DyeColor.WHITE);

            assert wool != null;
            assert dye != null;

            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('#', wool)
                    .key('X', panels)
                    .patternLine("###")
                    .patternLine("XXX")
                    .addCriterion("has_wool", hasItem(wool))
                    .setGroup(ILikeWoodBlockTags.BEDS.getName().getPath())
                    .build(consumer);

            if (!color.equals(DyeColor.WHITE)) {
                ShapelessRecipeBuilder.shapelessRecipe(block)
                        .addIngredient(whiteBed)
                        .addIngredient(dye)
                        .addCriterion("has_dye", hasItem(dye))
                        .setGroup(ILikeWoodBlockTags.BEDS.getName().getPath())
                        .build(consumer, new ResourceLocation(Constants.MOD_ID,
                                Util.toRegistryName(block.getRegistryName().getPath(), "from", whiteBed.asItem().getRegistryName().getPath())));
            } else {
                Arrays.stream(DyeColor.values()).filter(c -> !c.equals(DyeColor.WHITE))
                        .forEach(c -> {
                            final IItemProvider coloredBed = WoodenBlocks.getBedBlock(woodType, c);
                            ShapelessRecipeBuilder.shapelessRecipe(block)
                                    .addIngredient(coloredBed)
                                    .addIngredient(dye)
                                    .addCriterion("has_dye", hasItem(dye))
                                    .setGroup(ILikeWoodBlockTags.BEDS.getName().getPath())
                                    .build(consumer, new ResourceLocation(Constants.MOD_ID,
                                            Util.toRegistryName(block.getRegistryName().getPath(), "from", coloredBed.asItem().getRegistryName().getPath())));
                        });
            }
        });
    }

    @Override
    public String getName() {
        return "I Like Wood - Recipe Provider";
    }
}
