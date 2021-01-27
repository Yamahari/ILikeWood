package yamahari.ilikewood.provider;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.block.WoodenBedBlock;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.data.tag.ILikeWoodItemTags;
import yamahari.ilikewood.item.tiered.IWoodenTieredItem;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodenItemTiers;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.registry.WoodenItems;
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

public final class ILikeWoodRecipeProvider extends RecipeProvider {
    public ILikeWoodRecipeProvider(final DataGenerator generator) {
        super(generator);
    }

    private InventoryChangeTrigger.Instance hasItem(final Ingredient ingredientIn) {
        return InventoryChangeTrigger.Instance.forItems(Arrays.stream(ingredientIn.getMatchingStacks()).map(ItemStack::getItem).toArray(Item[]::new));
    }

    @Override
    protected void registerRecipes(@SuppressWarnings("NullableProblems") final Consumer<IFinishedRecipe> consumer) {
        Util.getBlocksWith(WoodenObjectType.PANELS, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider planks = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getResource());
            final IItemProvider slab = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getSlab(woodType).getResource());

            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('#', Objects.requireNonNull(slab))
                    .patternLine("#")
                    .patternLine("#")
                    .addCriterion("has_slab", hasItem(slab))
                    .setGroup(ILikeWoodBlockTags.PANELS.getName().getPath())
                    .build(consumer);

            new SingleItemRecipeBuilder(WoodenRecipeSerializers.SAWMILLING.get(), Ingredient.fromItems(planks), block, 1)
                    .addCriterion("has_planks", hasItem(planks))
                    .build(consumer, new ResourceLocation(Constants.MOD_ID,
                            Util.toRegistryName(block.getRegistryName().getPath(), "from",
                                    planks.asItem().getRegistryName().getPath(),
                                    WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));

            if (Util.HAS_LOG.test(woodType)) {
                final IItemProvider log = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType).getResource());
                new SingleItemRecipeBuilder(WoodenRecipeSerializers.SAWMILLING.get(), Ingredient.fromItems(log), block, 4)
                        .addCriterion("has_log", hasItem(log))
                        .build(consumer, new ResourceLocation(Constants.MOD_ID,
                                Util.toRegistryName(block.getRegistryName().getPath(), "from",
                                        log.asItem().getRegistryName().getPath(),
                                        WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
            }
            if (Util.HAS_STRIPPED_LOG.test(woodType)) {
                final IItemProvider stripped_log = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType).getResource());
                new SingleItemRecipeBuilder(WoodenRecipeSerializers.SAWMILLING.get(), Ingredient.fromItems(stripped_log), block, 4)
                        .addCriterion("has_stripped_log", hasItem(stripped_log))
                        .build(consumer, new ResourceLocation(Constants.MOD_ID,
                                Util.toRegistryName(block.getRegistryName().getPath(), "from",
                                        stripped_log.asItem().getRegistryName().getPath(),
                                        WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
            }
        });

        Util.getBlocksWith(WoodenObjectType.STAIRS, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, ((IWooden) block).getWoodType());
            final IItemProvider planks = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getResource());

            ShapedRecipeBuilder.shapedRecipe(block, 4)
                    .key('#', panels)
                    .patternLine("#  ")
                    .patternLine("## ")
                    .patternLine("###")
                    .addCriterion("has_panels", hasItem(panels))
                    .setGroup(ILikeWoodBlockTags.PANELS.getName().getPath())
                    .build(consumer);

            new SingleItemRecipeBuilder(WoodenRecipeSerializers.SAWMILLING.get(), Ingredient.fromItems(planks), block, 1)
                    .addCriterion("has_planks", hasItem(planks))
                    .build(consumer, new ResourceLocation(Constants.MOD_ID,
                            Util.toRegistryName(block.getRegistryName().getPath(), "from",
                                    planks.asItem().getRegistryName().getPath(),
                                    WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));

            new SingleItemRecipeBuilder(WoodenRecipeSerializers.SAWMILLING.get(), Ingredient.fromItems(panels), block, 1)
                    .addCriterion("has_panels", hasItem(panels))
                    .build(consumer, new ResourceLocation(Constants.MOD_ID,
                            Util.toRegistryName(block.getRegistryName().getPath(), "from",
                                    panels.asItem().getRegistryName().getPath(),
                                    WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
        });

        Util.getBlocksWith(WoodenObjectType.SLAB, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, woodType);
            final IItemProvider planks = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getResource());

            ShapedRecipeBuilder.shapedRecipe(block, 6)
                    .key('#', panels)
                    .patternLine("###")
                    .addCriterion("has_panels", hasItem(panels))
                    .setGroup(ILikeWoodBlockTags.PANELS_SLABS.getName().getPath())
                    .build(consumer);

            ShapedRecipeBuilder.shapedRecipe(Objects.requireNonNull(planks))
                    .key('S', block)
                    .patternLine("S")
                    .patternLine("S")
                    .addCriterion("has_panels_slab", hasItem(block))
                    .setGroup("ilikewood:planks")
                    .build(consumer, Constants.MOD_ID + ":" + planks.asItem().getRegistryName().getPath() + "_from_" + block.getRegistryName().getPath());

            new SingleItemRecipeBuilder(WoodenRecipeSerializers.SAWMILLING.get(), Ingredient.fromItems(planks), block, 2)
                    .addCriterion("has_planks", hasItem(planks))
                    .build(consumer, new ResourceLocation(Constants.MOD_ID,
                            Util.toRegistryName(block.getRegistryName().getPath(), "from",
                                    planks.asItem().getRegistryName().getPath(),
                                    WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));

            new SingleItemRecipeBuilder(WoodenRecipeSerializers.SAWMILLING.get(), Ingredient.fromItems(panels), block, 2)
                    .addCriterion("has_panels", hasItem(panels))
                    .build(consumer, new ResourceLocation(Constants.MOD_ID,
                            Util.toRegistryName(block.getRegistryName().getPath(), "from",
                                    panels.asItem().getRegistryName().getPath(),
                                    WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
        });

        Util.getBlocksWith(WoodenObjectType.BARREL, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
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

        Util.getBlocksWith(WoodenObjectType.BOOKSHELF, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
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

        Util.getBlocksWith(WoodenObjectType.CHEST, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
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

        Util.getBlocksWith(WoodenObjectType.COMPOSTER, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IItemProvider slab = WoodenBlocks.getBlock(WoodenObjectType.SLAB, ((IWooden) block).getWoodType());
            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('#', slab)
                    .patternLine("# #")
                    .patternLine("# #")
                    .patternLine("###")
                    .addCriterion("has_panels", hasItem(slab))
                    .setGroup(ILikeWoodBlockTags.COMPOSTER.getName().getPath())
                    .build(consumer);

        });

        Util.getBlocksWith(WoodenObjectType.WALL, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG)).forEach(block -> {
            final IItemProvider log = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(((IWooden) block).getWoodType()).getResource());
            ShapedRecipeBuilder.shapedRecipe(block, 6)
                    .key('#', Objects.requireNonNull(log))
                    .patternLine("###")
                    .patternLine("###")
                    .addCriterion("has_log", hasItem(log))
                    .setGroup(ILikeWoodBlockTags.WALLS.getName().getPath())
                    .build(consumer);

            new SingleItemRecipeBuilder(WoodenRecipeSerializers.SAWMILLING.get(), Ingredient.fromItems(log), block, 1)
                    .addCriterion("has_log", hasItem(log))
                    .build(consumer, new ResourceLocation(Constants.MOD_ID,
                            Util.toRegistryName(block.getRegistryName().getPath(), "from",
                                    log.asItem().getRegistryName().getPath(),
                                    WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));

        });

        Util.getBlocksWith(WoodenObjectType.LADDER, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
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

        Util.getBlocksWith(WoodenObjectType.TORCH, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
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

        Util.getBlocksWith(WoodenObjectType.CRAFTING_TABLE, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, woodType);

            ShapedRecipeBuilder.shapedRecipe(block)
                    .key('#', panels)
                    .patternLine("##")
                    .patternLine("##")
                    .addCriterion("has_panels", hasItem(panels))
                    .setGroup(ILikeWoodBlockTags.CRAFTING_TABLES.getName().getPath())
                    .build(consumer);
        });

        Util.getBlocksWith(WoodenObjectType.SCAFFOLDING, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
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

        Util.getBlocksWith(WoodenObjectType.LECTERN, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
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

        Util.getBlocksWith(WoodenObjectType.POST, Util.HAS_LOG.and(Util.HAS_STRIPPED_LOG)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
            final IItemProvider log = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(woodType).getResource());
            final IItemProvider strippedLog = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(woodType).getResource());
            final Block strippedPost = WoodenBlocks.getBlock(WoodenObjectType.STRIPPED_POST, woodType);
            ShapedRecipeBuilder.shapedRecipe(block, 6)
                    .key('#', Objects.requireNonNull(log))
                    .patternLine("#")
                    .patternLine("#")
                    .patternLine("#")
                    .addCriterion("has_log", hasItem(log))
                    .setGroup(ILikeWoodBlockTags.POSTS.getName().getPath())
                    .build(consumer);

            new SingleItemRecipeBuilder(WoodenRecipeSerializers.SAWMILLING.get(), Ingredient.fromItems(log), block, 2)
                    .addCriterion("has_log", hasItem(log))
                    .build(consumer, new ResourceLocation(Constants.MOD_ID,
                            Util.toRegistryName(block.getRegistryName().getPath(), "from",
                                    log.asItem().getRegistryName().getPath(),
                                    WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));

            new SingleItemRecipeBuilder(WoodenRecipeSerializers.SAWMILLING.get(), Ingredient.fromItems(strippedLog), strippedPost, 2)
                    .addCriterion("has_stripped_log", hasItem(strippedLog))
                    .build(consumer, new ResourceLocation(Constants.MOD_ID,
                            Util.toRegistryName(strippedPost.getRegistryName().getPath(), "from",
                                    strippedLog.asItem().getRegistryName().getPath(),
                                    WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
        });

        Util.getItemsWith(WoodenObjectType.STICK, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(item -> {
            final IWoodType woodType = ((IWooden) item).getWoodType();
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, woodType);
            final IItemProvider planks = ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(woodType).getResource());

            ShapedRecipeBuilder.shapedRecipe(item, 4)
                    .key('#', panels)
                    .patternLine("#")
                    .patternLine("#")
                    .addCriterion("has_panels", hasItem(panels))
                    .setGroup(ILikeWoodItemTags.STICKS.getName().getPath())
                    .build(consumer);

            new SingleItemRecipeBuilder(WoodenRecipeSerializers.SAWMILLING.get(), Ingredient.fromItems(panels), item, 2)
                    .addCriterion("has_panels", hasItem(panels))
                    .build(consumer, new ResourceLocation(Constants.MOD_ID,
                            Util.toRegistryName(item.getRegistryName().getPath(), "from",
                                    panels.asItem().getRegistryName().getPath(),
                                    WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));

            new SingleItemRecipeBuilder(WoodenRecipeSerializers.SAWMILLING.get(), Ingredient.fromItems(planks), item, 2)
                    .addCriterion("has_planks", hasItem(planks))
                    .build(consumer, new ResourceLocation(Constants.MOD_ID,
                            Util.toRegistryName(item.getRegistryName().getPath(), "from",
                                    planks.asItem().getRegistryName().getPath(),
                                    WoodenRecipeSerializers.SAWMILLING.get().getRegistryName().getPath())));
        });

        Util.getTieredItemsWith(WoodenTieredObjectType.AXE, Util.HAS_PLANKS.and(Util.HAS_SLAB))
                .filter(item -> !((IWoodenTieredItem) item).getWoodenItemTier().equals(VanillaWoodenItemTiers.NETHERITE))
                .forEach(item -> {
                    final IWoodType woodType = ((IWooden) item).getWoodType();
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

        Util.getTieredItemsWith(WoodenTieredObjectType.HOE, Util.HAS_PLANKS.and(Util.HAS_SLAB))
                .filter(item -> !((IWoodenTieredItem) item).getWoodenItemTier().equals(VanillaWoodenItemTiers.NETHERITE))
                .forEach(item -> {
                    final IWoodType woodType = ((IWooden) item).getWoodType();
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

        Util.getTieredItemsWith(WoodenTieredObjectType.PICKAXE, Util.HAS_PLANKS.and(Util.HAS_SLAB))
                .filter(item -> !((IWoodenTieredItem) item).getWoodenItemTier().equals(VanillaWoodenItemTiers.NETHERITE))
                .forEach(item -> {
                    final IWoodType woodType = ((IWooden) item).getWoodType();
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

        Util.getTieredItemsWith(WoodenTieredObjectType.SHOVEL, Util.HAS_PLANKS.and(Util.HAS_SLAB))
                .filter(item -> !((IWoodenTieredItem) item).getWoodenItemTier().equals(VanillaWoodenItemTiers.NETHERITE))
                .forEach(item -> {
                    final IWoodType woodType = ((IWooden) item).getWoodType();
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

        Util.getTieredItemsWith(WoodenTieredObjectType.SWORD, Util.HAS_PLANKS.and(Util.HAS_SLAB))
                .filter(item -> !((IWoodenTieredItem) item).getWoodenItemTier().equals(VanillaWoodenItemTiers.NETHERITE))
                .forEach(item -> {
                    final IWoodType woodType = ((IWooden) item).getWoodType();
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

        Util.getItemsWith(WoodenObjectType.BOW, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(item -> {
            final IWoodType woodType = ((IWooden) item).getWoodType();
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

        Util.getItemsWith(WoodenObjectType.CROSSBOW, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(item -> {
            final IWoodType woodType = ((IWooden) item).getWoodType();
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

        Util.getItemsWith(WoodenObjectType.ITEM_FRAME, Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(item -> {
            final IWoodType woodType = ((IWooden) item).getWoodType();
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

        Util.getTieredItemsWith(Util.HAS_PLANKS.and(Util.HAS_SLAB))
                .filter(item -> ((IWoodenTieredItem) item).getWoodenItemTier().equals(VanillaWoodenItemTiers.DIAMOND))
                .forEach(item -> {
                    final IWoodenTieredItem tieredItem = ((IWoodenTieredItem) item);
                    final IWoodType woodType = ((IWooden) item).getWoodType();
                    final Item output = WoodenItems.getTieredItem(tieredItem.getWoodenTieredObjectType(), woodType, VanillaWoodenItemTiers.NETHERITE);
                    SmithingRecipeBuilder.smithingRecipe(Ingredient.fromItems(item), Ingredient.fromItems(Items.NETHERITE_INGOT), output)
                            .addCriterion("has_netherite_ingot", hasItem(Items.NETHERITE_INGOT))
                            .build(consumer, new ResourceLocation(Constants.MOD_ID, Util.toRegistryName(output.getRegistryName().getPath(), "smithing")));
                });

        Util.getBedBlocksWith(Util.HAS_PLANKS.and(Util.HAS_SLAB)).forEach(block -> {
            final IWoodType woodType = ((IWooden) block).getWoodType();
            final DyeColor color = ((WoodenBedBlock) block).getDyeColor();
            final IItemProvider wool = Util.getIngredient(Util.toRegistryName(color.toString().toUpperCase(), "WOOL"), Blocks.class);
            final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, woodType);
            final IItemProvider dye = Util.getIngredient(Util.toRegistryName(color.toString().toUpperCase(), "DYE"), Items.class);
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

        Util.getBlocksWith(WoodenObjectType.SAWMILL, Util.HAS_PLANKS.and(Util.HAS_SLAB).and(Util.HAS_LOG).and(Util.HAS_STRIPPED_LOG))
                .forEach(block -> {
                    final IWoodType woodType = ((IWooden) block).getWoodType();
                    final IItemProvider panels = WoodenBlocks.getBlock(WoodenObjectType.PANELS, woodType);

                    ShapedRecipeBuilder.shapedRecipe(block)
                            .key('I', Items.IRON_INGOT)
                            .key('#', panels)
                            .patternLine("II")
                            .patternLine("##")
                            .patternLine("##")
                            .setGroup(ILikeWoodBlockTags.SAWMILLS.getName().getPath())
                            .addCriterion("has_panels", hasItem(panels))
                            .build(consumer);
                });
    }

    @Override
    public String getName() {
        return "I Like Wood - Recipe Provider";
    }
}
