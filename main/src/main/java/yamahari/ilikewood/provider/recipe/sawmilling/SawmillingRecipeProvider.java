package yamahari.ilikewood.provider.recipe.sawmilling;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.config.ILikeWoodConfig;
import yamahari.ilikewood.data.condition.ConfigCondition;
import yamahari.ilikewood.registry.WoodenRecipeSerializers;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

public final class SawmillingRecipeProvider extends RecipeProvider
{
    public SawmillingRecipeProvider(final DataGenerator generator) {
        super(generator);
    }

    private static SingleItemRecipeBuilder sawmillingRecipe(final Ingredient ingredient, final ItemLike result) {
        return sawmillingRecipe(ingredient, result, 1);
    }

    private static SingleItemRecipeBuilder sawmillingRecipe(
        final Ingredient ingredient, final ItemLike result, int count
    )
    {
        return new SingleItemRecipeBuilder(WoodenRecipeSerializers.SAWMILLING.get(), ingredient, result, count);
    }

    private static void panelsRecipe(
        final Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Block block, final int count
    )
    {
        if (woodType.getBlockTypes().contains(WoodenBlockType.PANELS) || woodType.getBuiltinBlockTypes().contains(
            WoodenBlockType.PANELS))
        {
            final var blockPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
            final var panels = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS);
            final var panelsPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(panels)).getPath();
            final RecipeBuilder builder = sawmillingRecipe(Ingredient.of(block),
                panels,
                count
            ).unlockedBy(String.format("has_%s", blockPath), has(block));
            ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(ILikeWoodConfig.PANELS_CONFIG.name()))
                .addRecipe(builder::save)
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(panelsPath, "from", blockPath, "sawmilling")
                    )
                );
        }
    }

    private static void panelsSlabRecipe(
        final Consumer<FinishedRecipe> consumer,
        final IWoodType woodType,
        final Block block,
        final int count,
        @Nullable final ICondition condition
    )
    {
        if (woodType.getBlockTypes().contains(WoodenBlockType.PANELS_SLAB) || woodType.getBuiltinBlockTypes().contains(
            WoodenBlockType.PANELS_SLAB))
        {
            final var blockPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
            final var panelsSlab = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS_SLAB);
            final var panelsSlabPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(panelsSlab)).getPath();
            final RecipeBuilder builder = sawmillingRecipe(Ingredient.of(block),
                panelsSlab,
                count
            ).unlockedBy(String.format("has_%s", blockPath), has(block));
            final var conditionalBuilder = ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(ILikeWoodConfig.PANELS_SLABS_CONFIG.name()));
            if (condition != null)
            {
                conditionalBuilder.addCondition(condition);
            }
            conditionalBuilder.addRecipe(builder::save).build(consumer,
                new ResourceLocation(Constants.MOD_ID,
                    Util.toRegistryName(panelsSlabPath, "from", blockPath, "sawmilling")
                )
            );
        }
    }

    private static void panelsStairsRecipe(
        final Consumer<FinishedRecipe> consumer,
        final IWoodType woodType,
        final Block block,
        final int count,
        @Nullable final ICondition condition
    )
    {
        if (woodType.getBlockTypes().contains(WoodenBlockType.PANELS_STAIRS) || woodType.getBuiltinBlockTypes()
            .contains(WoodenBlockType.PANELS_STAIRS))
        {
            final var blockPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
            final var panelsStairs = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS_STAIRS);
            final var panelsStairsPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(panelsStairs)).getPath();
            final RecipeBuilder builder = sawmillingRecipe(Ingredient.of(block),
                panelsStairs,
                count
            ).unlockedBy(String.format("has_%s", blockPath), has(block));
            final var conditionalBuilder = ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(ILikeWoodConfig.PANELS_STAIRS_CONFIG.name()));
            if (condition != null)
            {
                conditionalBuilder.addCondition(condition);
            }
            conditionalBuilder.addRecipe(builder::save).build(consumer,
                new ResourceLocation(Constants.MOD_ID,
                    Util.toRegistryName(panelsStairsPath, "from", blockPath, "sawmilling")
                )
            );
        }
    }

    private static void stickRecipe(
        final Consumer<FinishedRecipe> consumer,
        final IWoodType woodType,
        final Block block,
        final int count,
        @Nullable final ICondition condition
    )
    {
        if (woodType.getItemTypes().contains(WoodenItemType.STICK) || woodType.getBuiltinItemTypes().contains(
            WoodenItemType.STICK))
        {
            final var blockPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
            final var stick = ILikeWood.getItem(woodType, WoodenItemType.STICK);
            final var stickPath = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(stick)).getPath();
            final var builder = sawmillingRecipe(Ingredient.of(block), stick, count).unlockedBy(String.format("has_%s",
                blockPath
            ), has(block));
            final var conditionalBuilder =
                ConditionalRecipe.builder().addCondition(new ConfigCondition(ILikeWoodConfig.STICKS_CONFIG.name()));
            if (condition != null)
            {
                conditionalBuilder.addCondition(condition);
            }
            conditionalBuilder.addRecipe(builder::save).build(consumer,
                new ResourceLocation(Constants.MOD_ID, Util.toRegistryName(stickPath, "from", blockPath, "sawmilling"))
            );
        }
    }

    private static void wallRecipe(
        final Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Block block, final int count
    )
    {
        if (woodType.getBlockTypes().contains(WoodenBlockType.WALL) || woodType.getBuiltinBlockTypes().contains(
            WoodenBlockType.WALL))
        {
            final var blockPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
            final var wall = ILikeWood.getBlock(woodType, WoodenBlockType.WALL);
            final var wallPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(wall)).getPath();
            final RecipeBuilder builder =
                sawmillingRecipe(Ingredient.of(block), wall, count).unlockedBy(String.format("has_%s",
                blockPath
            ), has(block));
            ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(ILikeWoodConfig.WALLS_CONFIG.name()))
                .addRecipe(builder::save)
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(wallPath, "from", blockPath, "sawmilling")
                    )
                );
        }
    }

    private static void postRecipe(
        final Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Block block, final int count
    )
    {
        if (woodType.getBlockTypes().contains(WoodenBlockType.POST) || woodType.getBuiltinBlockTypes().contains(
            WoodenBlockType.POST))
        {
            final var blockPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
            final var post = ILikeWood.getBlock(woodType, WoodenBlockType.POST);
            final var postPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(post)).getPath();
            final RecipeBuilder builder =
                sawmillingRecipe(Ingredient.of(block), post, count).unlockedBy(String.format("has_%s",
                blockPath
            ), has(block));
            ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(ILikeWoodConfig.POSTS_CONFIG.name()))
                .addRecipe(builder::save)
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(postPath, "from", blockPath, "sawmilling")
                    )
                );
        }
    }

    private static void strippedPostRecipe(
        final Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Block block, final int count
    )
    {
        if (woodType.getBlockTypes().contains(WoodenBlockType.STRIPPED_POST) || woodType.getBuiltinBlockTypes()
            .contains(WoodenBlockType.STRIPPED_POST))
        {
            final var blockPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
            final var strippedPost = ILikeWood.getBlock(woodType, WoodenBlockType.STRIPPED_POST);
            final var strippedPostPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(strippedPost)).getPath();
            final RecipeBuilder builder = sawmillingRecipe(Ingredient.of(block),
                strippedPost,
                count
            ).unlockedBy(String.format("has_%s", blockPath), has(block));
            ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(ILikeWoodConfig.POSTS_CONFIG.name()))
                .addRecipe(builder::save)
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(strippedPostPath, "from", blockPath, "sawmilling")
                    )
                );
        }
    }

    private static void logPileRecipe(
        final Consumer<FinishedRecipe> consumer, final IWoodType woodType, final Block block, final int count
    )
    {
        if (woodType.getBlockTypes().contains(WoodenBlockType.LOG_PILE) || woodType.getBuiltinBlockTypes().contains(
            WoodenBlockType.LOG_PILE))
        {
            final var blockPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
            final var logPile = ILikeWood.getBlock(woodType, WoodenBlockType.LOG_PILE);
            final var logPilePath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(logPile)).getPath();
            final RecipeBuilder builder = sawmillingRecipe(Ingredient.of(block),
                logPile,
                count
            ).unlockedBy(String.format("has_%s", blockPath), has(block));
            ConditionalRecipe.builder()
                .addCondition(new ConfigCondition(ILikeWoodConfig.LOG_PILE_CONFIG.name()))
                .addRecipe(builder::save)
                .build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(logPilePath, "from", blockPath, "sawmilling")
                    )
                );
        }
    }

    private static void planksRecipe(
        final Consumer<FinishedRecipe> consumer,
        final IWoodType woodType,
        final Block block,
        final int count,
        @Nullable final ICondition condition
    )
    {
        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasPlanks(woodType))
        {
            final var blockPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
            final var planks =
                Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(
                    woodType).getResource()));
            final var planksPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(planks)).getPath();
            final var builder = sawmillingRecipe(Ingredient.of(block), planks, count).unlockedBy(String.format("has_%s",
                blockPath
            ), has(block));
            if (condition == null)
            {
                builder.save(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(planksPath, "from", blockPath, "sawmilling")
                    )
                );
            }
            else
            {
                ConditionalRecipe.builder().addCondition(condition).addRecipe(builder::save).build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(planksPath, "from", blockPath, "sawmilling")
                    )
                );
            }
        }
    }

    private static void slabRecipe(
        final Consumer<FinishedRecipe> consumer,
        final IWoodType woodType,
        final Block block,
        final int count,
        @Nullable final ICondition condition
    )
    {
        if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasSlab(woodType))
        {
            final var blockPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
            final var slab =
                Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getSlab(
                    woodType).getResource()));
            final var slabPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(slab)).getPath();
            final var builder = sawmillingRecipe(Ingredient.of(block), slab, count).unlockedBy(String.format("has_%s",
                blockPath
            ), has(block));
            if (condition == null)
            {
                builder.save(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(slabPath, "from", blockPath, "sawmilling")
                    )
                );
            }
            else
            {
                ConditionalRecipe.builder().addCondition(condition).addRecipe(builder::save).build(consumer,
                    new ResourceLocation(Constants.MOD_ID,
                        Util.toRegistryName(slabPath, "from", blockPath, "sawmilling")
                    )
                );
            }
        }
    }

    private static void vanillaStickRecipe(
        final Consumer<FinishedRecipe> consumer,
        final Block block,
        final int count,
        @Nullable final ICondition condition
    )
    {
        final var blockPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
        final var stickPath = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(Items.STICK)).getPath();
        final RecipeBuilder builder = sawmillingRecipe(Ingredient.of(block),
            Items.STICK,
            count
        ).unlockedBy(String.format("has_%s", blockPath), has(block));
        if (condition == null)
        {
            builder.save(consumer,
                new ResourceLocation(Constants.MOD_ID, Util.toRegistryName(stickPath, "from", blockPath, "sawmilling"))
            );
        }
        else
        {
            ConditionalRecipe.builder().addCondition(condition).addRecipe(builder::save).build(consumer,
                new ResourceLocation(Constants.MOD_ID, Util.toRegistryName(stickPath, "from", blockPath, "sawmilling"))
            );
        }
    }

    @Override
    protected void buildCraftingRecipes(@NotNull final Consumer<FinishedRecipe> consumer) {
        ILikeWood.WOOD_TYPE_REGISTRY.getWoodTypes().forEach(woodType ->
        {
            if (woodType.getBlockTypes().contains(WoodenBlockType.PANELS) || woodType.getBuiltinBlockTypes().contains(
                WoodenBlockType.PANELS))
            {
                final var panels = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS);
                final var condition = new ConfigCondition(ILikeWoodConfig.PANELS_CONFIG.name());
                panelsSlabRecipe(consumer, woodType, panels, 2, condition);
                panelsStairsRecipe(consumer, woodType, panels, 1, condition);
                stickRecipe(consumer, woodType, panels, 2, condition);
                vanillaStickRecipe(consumer, panels, 2, condition);
                planksRecipe(consumer, woodType, panels, 1, condition);
                slabRecipe(consumer, woodType, panels, 2, condition);
            }
            if (woodType.getBlockTypes().contains(WoodenBlockType.PANELS_SLAB) || woodType.getBuiltinBlockTypes()
                .contains(WoodenBlockType.PANELS_SLAB))
            {
                final var panelsSlab = ILikeWood.getBlock(woodType, WoodenBlockType.PANELS_SLAB);
                final var condition = new ConfigCondition(ILikeWoodConfig.PANELS_SLABS_CONFIG.name());
                slabRecipe(consumer, woodType, panelsSlab, 1, condition);
            }
            if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasPlanks(woodType))
            {
                final var planks =
                    Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getPlanks(
                        woodType).getResource()));
                panelsRecipe(consumer, woodType, planks, 1);
                panelsSlabRecipe(consumer, woodType, planks, 2, null);
                panelsStairsRecipe(consumer, woodType, planks, 1, null);
                stickRecipe(consumer, woodType, planks, 2, null);
                slabRecipe(consumer, woodType, planks, 2, null);
                vanillaStickRecipe(consumer, planks, 2, null);
            }

            if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasSlab(woodType))
            {
                final var slab =
                    Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getSlab(
                        woodType).getResource()));

                panelsSlabRecipe(consumer, woodType, slab, 1, null);
            }

            if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasLog(woodType))
            {
                final var log =
                    Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getLog(
                        woodType).getResource()));
                final var logPath = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(log)).getPath();
                panelsRecipe(consumer, woodType, log, 4);
                panelsSlabRecipe(consumer, woodType, log, 8, null);
                panelsStairsRecipe(consumer, woodType, log, 4, null);
                stickRecipe(consumer, woodType, log, 8, null);
                planksRecipe(consumer, woodType, log, 4, null);
                slabRecipe(consumer, woodType, log, 8, null);
                vanillaStickRecipe(consumer, log, 8, null);
                wallRecipe(consumer, woodType, log, 1);
                postRecipe(consumer, woodType, log, 2);
                strippedPostRecipe(consumer, woodType, log, 2);
                logPileRecipe(consumer, woodType, log, 1);
                if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasStrippedLog(woodType))
                {
                    final var strippedLog =
                        Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(
                            woodType).getResource()));
                    sawmillingRecipe(Ingredient.of(log), strippedLog).unlockedBy(String.format("has_%s", logPath),
                        has(log)
                    ).save(consumer,
                        new ResourceLocation(Constants.MOD_ID,
                            Util.toRegistryName(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(strippedLog))
                                    .getPath(),
                                "from",
                                logPath,
                                "sawmilling"
                            )
                        )
                    );
                }
            }
            if (ILikeWood.WOODEN_RESOURCE_REGISTRY.hasStrippedLog(woodType))
            {
                final var strippedLog =
                    Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(ILikeWood.WOODEN_RESOURCE_REGISTRY.getStrippedLog(
                        woodType).getResource()));
                panelsRecipe(consumer, woodType, strippedLog, 4);
                panelsSlabRecipe(consumer, woodType, strippedLog, 8, null);
                panelsStairsRecipe(consumer, woodType, strippedLog, 4, null);
                stickRecipe(consumer, woodType, strippedLog, 8, null);
                planksRecipe(consumer, woodType, strippedLog, 4, null);
                slabRecipe(consumer, woodType, strippedLog, 8, null);
                vanillaStickRecipe(consumer, strippedLog, 8, null);
                strippedPostRecipe(consumer, woodType, strippedLog, 2);
            }
        });
    }
}